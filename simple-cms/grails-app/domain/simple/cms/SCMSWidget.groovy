package simple.cms

class SCMSWidget {
	String widgetId
	String cssClass
	Date dateCreated
	Date lastUpdated

    static constraints = {
		widgetId(size: 1..30)
		cssClass(nullable: true)
    }
	
}
