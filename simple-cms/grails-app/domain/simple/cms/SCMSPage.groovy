package simple.cms

class SCMSPage {
	
	String URI
	SCMSPageTemplate template
	
	static hasMany = [
		widgets: SCMSWidget
	]

    static constraints = {
		URI(blank: false, size: 4..40)
		template(nullable: false)
		widgets(nullable: false)
    }
	
	def generateWidgets() {
		template.widgetCreators.each { creator ->
			addToWidgets(creator.domainClass.newInstance())
		}
	}
}
