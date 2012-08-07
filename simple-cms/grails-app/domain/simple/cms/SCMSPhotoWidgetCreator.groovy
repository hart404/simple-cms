package simple.cms

class SCMSPhotoWidgetCreator extends SCMSWidgetCreator {

    static constraints = {
    }
	
	SCMSWidget getWidgetInstance() {
		new SCMSPhotoWidget("widgetId": widgetId)
	}

}
