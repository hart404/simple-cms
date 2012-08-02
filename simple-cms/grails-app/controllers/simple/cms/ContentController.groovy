package simple.cms

class ContentController {

    def handleContent() { 
		def baseURI = request.forwardURI
		def pageURI = extractURI(baseURI)
		println pageURI
		[pageURI: pageURI]
	}
	
	String extractURI(baseURI) {
		def parts = baseURI.split('/')

		def newURIList = parts[-(parts.size() - 3)..-1]
		def newURI = ""
		newURIList.each { part ->
			newURI += '/' + part
		}
		newURI
	}
}
