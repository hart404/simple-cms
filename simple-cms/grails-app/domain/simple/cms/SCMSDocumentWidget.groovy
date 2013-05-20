package simple.cms

class SCMSDocumentWidget extends SCMSWidget {

	String title
	
	static hasMany = [
		documents: SCMSDocument
	] 

	static constraints = {
		title(nullable: true)
	}
	
	static mapping = {
		documents cascade: 'all'
		cache: true
	}

}
