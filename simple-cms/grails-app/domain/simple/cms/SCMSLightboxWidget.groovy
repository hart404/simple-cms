package simple.cms

class SCMSLightboxWidget extends SCMSWidget {
	
	List<SCMSPhoto> photos
	
    static constraints = {
		photos(nullable: false)
    }
	
	SCMSWidget getWidgetInstance() {
		new SCMSPhotoWidget("widgetId": widgetId)
	}

}
