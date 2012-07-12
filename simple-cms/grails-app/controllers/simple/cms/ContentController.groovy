package simple.cms

class ContentController {

    def handleContent() { 
		def pageURI = request.forwardURI
		println pageURI
	}
}
