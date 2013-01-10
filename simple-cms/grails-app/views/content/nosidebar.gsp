<html>
<head>
<meta name="layout" content="mainLayout">
<meta name="description" content="${page.descriptionMetadata}" />
<meta name="keywords" content="${page.keywordsMetadata}" />
<title>${page.title}</title>
</head>
<body>
	<div class="generalContainer">
        <div class="scmsHtmlContainer">
            <scms:htmlWidget widget="${html1}" />
        </div>
	</div>
	<g:render template="/widget/updateHtmlWidgetDialog" plugin="simple-cms" />
    <g:render template="/widget/updatePhotoWidget" plugin="simple-cms" />
    <g:render template="/photo/searchPhotoDialog" plugin="simple-cms" />
</body>
</html>

