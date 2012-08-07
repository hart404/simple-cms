package simple.cms

class SCMSGalleryWidgetCreator extends SCMSWidgetCreator {

    static constraints = {
    }
	
	SCMSWidget getWidgetInstance() {
		new SCMSGalleryWidget("widgetId": widgetId)
	}

}
