package simple.cms

import java.util.Date;
import java.util.List;

class SCMSDocument {
	static searchable = [only: ['keywords', 'allKeywords', 'source', 'path', 'fileName', 'originalFileName', 'description']]
	
	List<String> keywords
	String allKeywords
	String description
	String source
	String path
	String fileName
	String originalFileName
	Date dateCreated
	Date lastUpdated

	static constraints = {
		source(nullable: false)
		fileName(nullable: false)
		path(nullable: false)
		description(nullable: true, size: 0..2000)
		keywords(nullable: true)
		allKeywords(nullable: true)
	}
	
	static mapping = {
		allKeywords type: "text"
		description type: "text"
		cache: true
	}
	
	def fullPath() {
		def url = source + '/' + path + '/' + fileName
		url
	}
	
	String toString() {
		"File: ${originalFileName} keywords: ${keywords}"
	}
	
	String shortenedFileName() {
		if (originalFileName.size() > 40) {
			return "${originalFileName[0..39]}..."
		}
		return originalFileName
	}

}
