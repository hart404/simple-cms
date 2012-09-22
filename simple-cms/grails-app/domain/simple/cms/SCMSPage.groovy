package simple.cms

class SCMSPage extends SCMSStaticPage {
	
	SCMSPageTemplate template
	String title
	String descriptionMetadata = ""
	String keywordsMetadata = ""
	
    static constraints = {
		title(nullable: false, size: 5..100)
		template(nullable: false)
		descriptionMetadata(nullable: true, size: 0..2048)
		keywordsMetadata(nullable: true, size: 0..2048)
    }
	
	def generateWidgets() {
		template.widgetCreators.each { creator ->
			// Don't replace an existing widget - may happen when changing templates
			if (!hasWidgetWithId(creator.widgetId)) {
				addToWidgets(creator.widgetInstance)
			}
		}
	}
	
	def hasWidgetWithId(widgetId) {
		for (widget in widgets) {
			if (widget.widgetId == widgetId) return true
		}
		return false
	}
	
	String toString() {
		title + " - Widget Count: ${widgets.size()}"
	}
}
	