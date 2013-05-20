package simple.cms

class SCMSMenuLink {
	
	SCMSMenu menu
	String link
	
	static constraints = {
		menu(nullable: false)
		link(nullable: false)
	}
	
	static mapping = {
		cache: 'non-strict-read-write'
	}
	
}
