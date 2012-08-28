package simple.cms

class SCMSPhotoWidgetCreator extends SCMSWidgetCreator {

    static constraints = {
    }
	
	SCMSWidget getWidgetInstance() {
		def photo = SCMSPhoto.get(SCMSPhoto.DEFAULT_PHOTO_ID)
		new SCMSPhotoWidget("widgetId": widgetId, "photo": photo)
	}

}
