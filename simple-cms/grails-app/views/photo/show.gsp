<html>
<head>
	<title>Show Photo</title>
	<meta name="layout" content="mainLayout" />
</head>
<body>
	<g:if test="${photo.width > 600}">
		<div>
			<img src="${photo.fullPath()}" width="600" height="${(600 / photo.width) * photo.height}"/>
		</div>
	</g:if>
	<g:else>
		<div>
			<img src="${photo.fullPath()}"/>
		</div>
	</g:else>
    <p class="bigp">Source: ${photo.source}</p>
    <p class="bigp">Path: ${photo.path}</p>
	<p class="bigp">Name: ${photo.fileName}</p>
    <p class="bigp">Original Name: ${photo.originalFileName}</p>
	<p class="bigp">Artist: ${photo.artist}</p>
	<p class="bigp">Keywords: ${photo.allKeywords}</p>
	<p class="bigp">Date Taken: ${photo.dateCreated}</p>
	<p class="bigp">Copyright: ${photo.copyright}</p>
	<p class="bigp">Dimensions: ${photo.width}x${photo.height}</p>	
</body>
</html>