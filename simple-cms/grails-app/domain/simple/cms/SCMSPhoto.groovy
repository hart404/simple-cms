package simple.cms

/*
 * Note: the URL for the photo is source + path + fileName
 */
class SCMSPhoto {
	
	static searchable = [only: ['keywords', 'allKeywords', 'artist', 'source', 'path', 'fileName', 'originalFileName', 'usageRights', 'width', 'height', 'description']]
	
	static Long DEFAULT_PHOTO_ID
	
	List<String> keywords
	String allKeywords
	String artist
	String copyright
	String description
	int height
	int width
	String source
	String path
	String fileName
	String originalFileName
	Date dateCreated
	String usageRights
	BigDecimal latitude
	BigDecimal longitude
	BigDecimal altitude

    static constraints = {
		source(nullable: false)
		fileName(nullable: false)
		path(nullable: false)
		artist(nullable: true)
		copyright(nullable: true)
		description(nullable: true, size: 0..2000)
		usageRights(nullable: true)
		latitude(nullable: true)
		longitude(nullable: true)
		altitude(nullable: true)
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
		"File: ${originalFileName} width: ${width} height: ${height} keywords: ${keywords}"
	}
	
	String shortenedFileName() {
		if (originalFileName.size() > 40) {
			return "${originalFileName[0..39]}..."
		}
		return originalFileName
	}
}
