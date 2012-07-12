package simple.cms

class SCMSWidget {
	String widgetId
	Date dateCreated
	Date lastUpdated

    static constraints = {
		widgetId(size: 4..30)
    }
	
}
