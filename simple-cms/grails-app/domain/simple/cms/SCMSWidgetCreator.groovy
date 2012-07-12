package simple.cms

class SCMSWidgetCreator {
	
	String widgetId

    static constraints = {
		widgetId(nullable: false)
    }
	
	/*
	 * Subclasses should override this method to create the appropriate SCMSWidget instance
	 */
	SCMSWidget getWidgetInstance() {
		null 
	}
}
