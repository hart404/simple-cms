package simple.cms

class SCMSHTMLWidget extends SCMSWidget {
	String htmlText = "<h1>Default text</h1>"

    static constraints = {
		htmlText(size: 1..60000)
    }
	
	static mapping = {
		htmlText type: "text"
	}
	
}
