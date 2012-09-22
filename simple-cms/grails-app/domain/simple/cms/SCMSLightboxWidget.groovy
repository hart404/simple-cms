package simple.cms

class SCMSLightboxWidget extends SCMSWidget {
	
	List<SCMSPhotoWidget> photoWidgets = []
	SCMSPhoto linkPhoto
	static hasMany = [
		photoWidgets: SCMSPhotoWidget
	]
		
    static constraints = {
		photoWidgets(nullable: false)
		linkPhoto(nullable: true)
    }
	
	static mapping = {
		photoWidgets cascade: "all"
		linkPhoto cascade: "all"
	}
	
	SCMSWidget getWidgetInstance() {
		new SCMSPhotoWidget("widgetId": widgetId)
	}

}
