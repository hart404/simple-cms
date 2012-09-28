package simple.cms

import grails.converters.JSON

import javax.servlet.http.HttpServletRequest

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.MultipartHttpServletRequest

import com.drew.imaging.ImageMetadataReader
import com.drew.metadata.Directory
import com.drew.metadata.Metadata
import com.drew.metadata.exif.ExifIFD0Directory
import com.drew.metadata.iptc.IptcDirectory
import com.drew.metadata.jpeg.JpegDirectory


class PhotoController {
	static final String DEFAULT_S3_SOURCE = 'https://s3.amazonaws.com'
	static final String DEFAULT_PATH = "McDowellSonoranConservancyImages"
	
	def amazonS3Service
	def searchableService
	def photoService
	
	// Remember to terminate the path with a '/'
	def rootDirectory = System.getProperty("user.home")
	def defaultUploadDirectory = '/upload/'

	def uploadPhotos = {
	}
	
	def show = {
		def photoToShow = SCMSPhoto.get(params.id)
		assert photoToShow != null
		[photo: photoToShow]
	}

	def upload = {
		try {
			createUploadDirectory()
			File uploaded = createTemporaryFile()
			InputStream inputStream = selectInputStream(request)
			uploadFile(inputStream, uploaded)
			createPhotoForFile(uploaded, DEFAULT_S3_SOURCE, DEFAULT_PATH, uploaded.name, params.qqfile)
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

	private File createTemporaryFile() {
		File uploaded
		UUID uuid = UUID.randomUUID()
		try {
			uploaded = File.createTempFile(uuid.toString(), '.jpg')
		} catch (IOException e) {
			// Using CloudFoundry, the temp directory is protected
			// Create a file in a directory beneath root
			log.info("Could not create temporary file")
			createUploadDirectory()
			uploaded = new File(rootDirectory + defaultUploadDirectory, uuid)
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

	private void createPhotoForFile(File source, String sourceURL, String path, String fileName, String originalFileName) {
		Metadata metadata = ImageMetadataReader.readMetadata(source)
		def metadataMap = [path: defaultUploadDirectory, fileName: source.getName()]
		addJPEGItems(metadataMap, metadata)
		addEXIFItems(metadataMap, metadata)
		addIPTCItems(metadataMap, metadata)
		def photo = new SCMSPhoto(metadataMap)
		photo.source = sourceURL
		photo.path = path
		photo.fileName = fileName
		photo.originalFileName = originalFileName
		def savedPhoto = photo.save()
		if (savedPhoto == null) {
			log.error("Problem uploading photo: ${photo.originalFileName}, ${photo.allKeywords}")
			photo.errors.allErrors.each {
				log.error(it)
			}
		}
	}
	
	private void addJPEGItems(metadataMap, metadata) {
		Directory directory = metadata.getDirectory(JpegDirectory.class)
		metadataMap["width"] = directory.getInt(JpegDirectory.TAG_JPEG_IMAGE_WIDTH)
		metadataMap["height"] = directory.getInt(JpegDirectory.TAG_JPEG_IMAGE_HEIGHT)		
	}
	
	private void addEXIFItems(metadataMap, metadata) {
		Directory directory = metadata?.getDirectory(ExifIFD0Directory.class)
		metadataMap["artist"] = directory?.getString(ExifIFD0Directory.TAG_ARTIST) ?: "No Artist"
		String copyright = directory?.getString(ExifIFD0Directory.TAG_COPYRIGHT)
		if (copyright == null) {
			copyright = "No copyright"
		} else {
			copyright = copyright[1..-1] 
		}
		metadataMap["copyright"] =  copyright
		metadataMap["description"] = directory?.getString(ExifIFD0Directory.TAG_IMAGE_DESCRIPTION)
	}
	
	private void addIPTCItems(metadataMap, metadata) {
		Directory directory = metadata.getDirectory(IptcDirectory.class)
		metadataMap["dateCreated"] = directory?.getDate(IptcDirectory.TAG_DATE_CREATED) ?: new Date()
		metadataMap["keywords"] = (directory?.getStringArray(IptcDirectory.TAG_KEYWORDS) ?: []) as List
		metadataMap["allKeywords"] = directory?.getString(IptcDirectory.TAG_KEYWORDS) ?: ""
		// metadataMap["usageRights"] = directory?.getString(IptcDirectory.TAG_) ?: ""
	}
	
	def searchForPhotos() {
		def keywords = params["keywords"]
		params.max = 5
		def results = SCMSPhoto.search(keywords, params)
		render(template: "/photo/photoSearchList", model: ["searchResult": results], plugin: "simple-cms")
	}
	
	def searchForPhotosMultiple() {
		def keywords = params["keywords"]
		params.max = 5
		def results = SCMSPhoto.search(keywords, params)
		render(template: "/photo/photoSearchListMultiple", model: ["searchResult": results], plugin: "simple-cms")
	}
	
	def list() {
		params.max = Math.min(params.max ? params.int('max') : 20, 100)
		params.sort = "originalFileName"
		params.order = "asc"
		[photoInstanceList: SCMSPhoto.list(params), photoInstanceTotal: SCMSPhoto.count()]
	}
	
	def save() {
		def photoInstance = new SCMSPhoto(params)
		if (!photoInstance.save(flush: true)) {
			render(view: "create", model: [photoInstance: photoInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'SCMSPhoto.label', default: 'SCMSPhoto'), photoInstance.id])
		redirect(action: "show", id: photoInstance.id)
	}

	def edit(Long id) {
		def photoInstance = SCMSPhoto.get(id)
		if (!photoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'SCMSPhoto.label', default: 'SCMSPhoto'), id])
			redirect(action: "list")
			return
		}
		[photoInstance: photoInstance]
	}
	
	def update(Long id, Long version) {
		def photoInstance = SCMSPhoto.get(id)
		if (!photoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'SCMSPhoto.label', default: 'SCMSPhoto'), id])
			redirect(action: "list")
			return
		}

		if (version != null) {
			if (photoInstance.version > version) {
				photoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						  [message(code: 'SCMSPhoto.label', default: 'SCMSPhoto')] as Object[],
						  "Another user has updated this SCMSPhoto while you were editing")
				render(view: "edit", model: [photoInstance: photoInstance])
				return
			}
		}

		photoInstance.properties = params

		if (!photoInstance.save(flush: true)) {
			render(view: "edit", model: [photoInstance: photoInstance])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'SCMSPhoto.label', default: 'SCMSPhoto'), photoInstance.id])
		redirect(action: "show", id: photoInstance.id)
	}

	def delete(Long id) {
		def photoInstance = SCMSPhoto.get(id)
		if (!photoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'SCMSPhoto.label', default: 'SCMSPhoto'), id])
			redirect(action: "list")
			return
		}

		try {
			photoInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'SCMSPhoto.label', default: 'SCMSPhoto'), id])
			redirect(action: "list")
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'SCMSPhoto.label', default: 'SCMSPhoto'), id])
			redirect(action: "show", id: id)
		}
	}
	
	def deleteMultiplePhotos() {
		println "Deleting photos with params: ${params}"
		def photoIds = params.list('photoIds[]')
		photoIds.each { photoId ->
			photoService.deleteIndividualPhoto(photoId)
		}
		render(text: [success:true] as JSON, contentType:'text/json')
	}
	
}
