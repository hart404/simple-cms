package simple.cms

import org.joda.time.LocalDate


class SCMSAdSpacePhoto {
	
	String title
	String description
	LocalDate displayStartDate = LocalDate.now()
	LocalDate displayEndDate = new LocalDate(2050, 12, 31)
	SCMSPhoto photo
	String link
	
	static constraints = {
		title(blank: false)
		description(nullable: true)
		photo(nullable: false)
		displayStartDate(nullable: false)
		displayEndDate(nullable: false)
		link(nullable: false)
	}
	
	static mapping = {
		cache: 'non-strict-read-write'
	}

}
