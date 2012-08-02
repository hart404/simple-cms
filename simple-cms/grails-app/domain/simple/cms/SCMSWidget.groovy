package simple.cms

class SCMSWidget {
	String widgetId
	String cssClass
	Date dateCreated
	Date lastUpdated

    static constraints = {
		widgetId(size: 4..30)
		cssClass(nullable: true)
    }
	
}
