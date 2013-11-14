<html>
<head>
<meta name="layout" content="contentSidebarLayout">
<meta name="description" content="${page?.descriptionMetadata}" />
<meta name="keywords" content="${page?.keywordsMetadata}" />
<title>${page?.title}</title>
</head>
<body>
	<div class="contentContainer">
		<div class="scmsPhotoContainer">
			<scms:photoWidget widget="${photo1}" width='350' photoCssClass='innerGlow scmsPhoto' caption='Caption 1' captionCssClass='scmsCaption' />
            <scms:photoWidget widget="${photo2}" width='350' photoCssClass='innerGlow scmsPhoto' caption='Caption 2' captionCssClass='scmsCaption' />
        </div>
        <div class="scmsHtmlContainer">
            <scms:htmlWidget widget="${html1}" />
        </div>
	</div>
	<g:render template="/widget/updateHtmlWidgetDialog" plugin="simple-cms" />
    <g:render template="/widget/updatePhotoWidget" plugin="simple-cms" />
    <g:render template="/photo/searchPhotoDialog" plugin="simple-cms" />
</body>
</html>

