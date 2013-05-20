package simple.cms

class SCMSPhotoWidget extends SCMSWidget {
	
	SCMSPhoto photo
	String caption

    static constraints = {
		photo(nullable: true)
		caption(nullable: true)
    }
	
	static mapping = {
		photo cascade: 'all', cache: true
	}
	
}
