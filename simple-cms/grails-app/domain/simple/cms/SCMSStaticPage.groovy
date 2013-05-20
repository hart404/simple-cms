package simple.cms

import java.util.List;

class SCMSStaticPage {

	String link
	Boolean canBeEditedByLeader
	static SCMSStaticPage currentPage
	
	List<SCMSWidget> widgets
	static hasMany = [
		widgets: SCMSWidget
	]
	
	static constraints = {
		link(blank: false, size: 4..40, unique: true)
		widgets(nullable: false)
	}
	
	static mapping = {
		widgets(cascade: "all, delete-orphan")
	}
	
}
