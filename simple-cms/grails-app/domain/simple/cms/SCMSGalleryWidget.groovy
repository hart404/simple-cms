package simple.cms

class SCMSGalleryWidget extends SCMSWidget {

	List<SCMSPhoto> photos
	
    static constraints = {
		photos(nullable: false)
    }
}
