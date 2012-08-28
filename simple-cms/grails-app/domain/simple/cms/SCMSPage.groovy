package simple.cms

class SCMSPage extends SCMSStaticPage {
	
	SCMSPageTemplate template
	String title
	
    static constraints = {
		title(nullable: false, size: 5..100)
		template(nullable: false)
    }
	
	def generateWidgets() {
		template.widgetCreators.each { creator ->
			addToWidgets(creator.widgetInstance)
		}
	}
}
	