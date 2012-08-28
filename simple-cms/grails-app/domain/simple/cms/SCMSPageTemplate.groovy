package simple.cms

class SCMSPageTemplate {
	
	String name
	String description
	String associatedGSP
	
	static hasMany = [
		widgetCreators: SCMSWidgetCreator
	]

    static constraints = {
		name(blank: false, size: 4..100)
		description(nullable: true, size: 10..1000)
		associatedGSP(blank: false, size: 5..40)
		widgetCreators(nullable: false)
    }
	
	String toString() {
		name
	}
}
