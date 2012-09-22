package simple.cms

class SCMSGalleryWidget extends SCMSWidget {

	List<SCMSPhotoWidget> photoWidgets = []
	String title = "Default Title"
	static hasMany = [
		photoWidgets: SCMSPhotoWidget
	]

    static constraints = {
		photoWidgets(nullable: false)
		title(nullable: false)
    }
	
	static mapping = {
		photoWidgets cascade: "all"
	}
	
}
