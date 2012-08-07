package simple.cms

class SCMSPhotoWidget extends SCMSWidget {
	
	String photoURL

    static constraints = {
		photoURL(blank: false)
    }
	
	SCMSWidget getWidgetInstance() {
		new SCMSPhotoWidget("widgetId": widgetId)
	}
}
