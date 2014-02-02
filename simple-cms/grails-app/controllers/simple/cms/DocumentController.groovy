package simple.cms

import grails.converters.JSON

import javax.servlet.http.HttpServletRequest

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.MultipartHttpServletRequest

class DocumentController {
	static final String DEFAULT_S3_SOURCE = 'https://s3.amazonaws.com'
	static final String DEFAULT_PATH = "McDowellSonoranConservancyImages"

	def amazonS3Service
	def searchableService
	def documentWidgetService
	
	// Remember to terminate the path with a '/'
	def rootDirectory = System.getProperty("user.home")
	def defaultUploadDirectory = '/upload/'

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max as Long ?: 30, 100)
		if (!params.sort) params.sort = "dateCreated"
		if (!params.order) params.order = "desc"
        [SCMSDocumentInstanceList: SCMSDocument.list(params), SCMSDocumentInstanceTotal: SCMSDocument.count()]
    }

    def create() {
        [SCMSDocumentInstance: new SCMSDocument(params)]
    }

    def save() {
        def SCMSDocumentInstance = new SCMSDocument(params)
        if (!SCMSDocumentInstance.save(flush: true)) {
            render(view: "create", model: [SCMSDocumentInstance: SCMSDocumentInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'SCMSDocument.label', default: 'SCMSDocument'), SCMSDocumentInstance.id])
        redirect(action: "show", id: SCMSDocumentInstance.id)
    }

    def show(Long id) {
        def SCMSDocumentInstance = SCMSDocument.get(id)
        if (!SCMSDocumentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'SCMSDocument.label', default: 'SCMSDocument'), id])
            redirect(action: "list")
            return
        }

        [SCMSDocumentInstance: SCMSDocumentInstance]
    }

    def edit(Long id) {
        def SCMSDocumentInstance = SCMSDocument.get(id)
        if (!SCMSDocumentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'SCMSDocument.label', default: 'SCMSDocument'), id])
            redirect(action: "list")
            return
        }

        [SCMSDocumentInstance: SCMSDocumentInstance]
    }

    def update(Long id, Long version) {
        def SCMSDocumentInstance = SCMSDocument.get(id)
        if (!SCMSDocumentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'SCMSDocument.label', default: 'SCMSDocument'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (SCMSDocumentInstance.version > version) {
                SCMSDocumentInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'SCMSDocument.label', default: 'SCMSDocument')] as Object[],
                          "Another user has updated this SCMSDocument while you were editing")
                render(view: "edit", model: [SCMSDocumentInstance: SCMSDocumentInstance])
                return
            }
        }

        SCMSDocumentInstance.properties = params

        if (!SCMSDocumentInstance.save(flush: true)) {
            render(view: "edit", model: [SCMSDocumentInstance: SCMSDocumentInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'SCMSDocument.label', default: 'SCMSDocument'), SCMSDocumentInstance.id])
        redirect(action: "show", id: SCMSDocumentInstance.id)
    }

    def delete(Long id) {
        def SCMSDocumentInstance = SCMSDocument.get(id)
        if (!SCMSDocumentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'SCMSDocument.label', default: 'SCMSDocument'), id])
            redirect(action: "list")
            return
        }

        try {
            SCMSDocumentInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'SCMSDocument.label', default: 'SCMSDocument'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'SCMSDocument.label', default: 'SCMSDocument'), id])
            redirect(action: "show", id: id)
        }
    }
	
	def uploadDocuments() {			
	}
	
	def upload = {
		try {
			def fileExtension = params.qqfile.tokenize(".")[-1]
			createUploadDirectory()
			File uploaded = createTemporaryFile(fileExtension)
			InputStream inputStream = selectInputStream(request)
			uploadFile(inputStream, uploaded)
			createDocumentForFile(uploaded, DEFAULT_S3_SOURCE, DEFAULT_PATH, uploaded.name, params.qqfile)
			transferFileToS3(uploaded)
			return render(text: [success:true] as JSON, contentType:'text/json')
		} catch (FileUploadException e) {
			log.error("Failed to upload file.", e)
			return render(text: [success:false, message: e.message] as JSON, contentType:'text/json')
		}
	}
	
	def transferFileToS3(file) {
		amazonS3Service.putFile(file)
	}
	
	def createUploadDirectory() {
		// Create the defaultUploadDirectory if necessary
		def directory = new File(rootDirectory + defaultUploadDirectory)
		if (!directory.isDirectory()) {
			log.info("Creating temporary directory")
			directory.mkdir()
		}
	}

	private InputStream selectInputStream(HttpServletRequest request) {
		if (request instanceof MultipartHttpServletRequest) {
			MultipartFile uploadedFile = ((MultipartHttpServletRequest) request).getFile('qqfile')
			return uploadedFile.inputStream
		}
		return request.inputStream
	}

	private File createTemporaryFile(fileExtension) {
		File uploaded
		UUID uuid = UUID.randomUUID()
		try {
			uploaded = File.createTempFile(uuid.toString(), '.' + fileExtension)
		} catch (IOException e) {
			// Using CloudFoundry, the temp directory is protected
			// Create a file in a directory beneath root
			log.info("Could not create temporary file")
			createUploadDirectory()
			uploaded = new File(rootDirectory + defaultUploadDirectory, uuid.toString() + '.' + fileExtension)
			uploaded.createNewFile()
		}
		return uploaded
	}

	private  void uploadFile(InputStream inputStream, File file) {
		try {
			file << inputStream
		} catch (Exception e) {
			throw new FileUploadException(e)
		}
	}

	private void createDocumentForFile(File source, String sourceURL, String path, String fileName, String originalFileName) {
		def metadataMap = [path: defaultUploadDirectory, fileName: source.getName()]
		def document = new SCMSDocument(metadataMap)
		document.source = sourceURL
		document.path = path
		document.fileName = fileName
		document.originalFileName = originalFileName
		def savedPhoto = document.save()
		if (savedPhoto == null) {
			log.error("Problem uploading file: ${document.originalFileName}, ${document.allKeywords}")
			document.errors.allErrors.each {
				log.error(it)
			}
		}
	}
	
	def searchForDocumentsMultiple() {
		def keywords = params["keywords"]
		params.max = 5
		def results = SCMSDocument.search(keywords, params)
		render(template: "/document/documentSearchListMultiple", model: ["searchResult": results], plugin: "simple-cms")
	}
	
	def documentWidgetDocuments() {
		def documentWidget = SCMSDocumentWidget.get(params.id)
		def max = params.max as Integer
		def offset = params.offset as Integer
		def last = Math.min(offset + max, documentWidget.documents.size())
		def documents = []
		if (!documentWidget.documents.isEmpty()) {
			documents = documentWidget.documents as List
			documents = documents[offset..last - 1]
		}
		render(template: "/document/documentWidgetDocumentList", model: [documentWidget: documentWidget, documents: documents, total: documentWidget.documents.size(), offset: offset, max: max], plugin: "simple-cms")
	}
	
	def updateDocumentWidgetTitle() {
		def documentWidget = SCMSDocumentWidget.get(params.id)
		if (documentWidget) {
			documentWidget.title = params.title
			documentWidget.save(failOnError: true)
		} else {
			log.error "Document Widget with id ${params.id} not found!"
		}
	}
	
	def deleteDocument() {
		def documentWidget = SCMSDocumentWidget.get(params.id)
		def document = SCMSDocument.get(params.documentId)
		documentWidgetService.deleteDocumentWidgetDocument(documentWidget, document)
		def documents = documentWidget.documents
		render(template: "/document/documentWidgetDocumentList", model: [documentWidget: documentWidget, documents: documents, total: documentWidget.documents.size(), offset: 0, max: 5], plugin: "simple-cms")
	}
	
	def updateDocumentWidgetDocumentIds() {
		def documentWidgetId = params.id
		def documentIds = params.list('documentIds[]')
		def documentWidget = documentWidgetService.updateDocumentWidgetIds(documentWidgetId, documentIds)
		def documents = documentWidget.documents
		println "--Documents size: ${documents.size()}"
		render(template: "/document/documentWidgetDocumentList", model: [documentWidget: documentWidget, documents: documents, total: documentWidget.documents.size(), offset: 0, max: 5], plugin: "simple-cms")
	}

}
