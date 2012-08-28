<html>
<head>
<meta name="layout" content="mainLayout">
<title>${title}</title>
</head>
<body>
	<div class="generalContainer">
        <div class="scmsHtmlContainer">
            <scms:htmlWidget widget="${html1}" />
        </div>
		<div class="scmsPhotoContainer">
			<scms:photoWidget widget="${photo1}" width='350' photoCssClass='innerGlow scmscPhoto' caption='Caption 1' captionCssClass='scmsCaption' />
            <scms:photoWidget widget="${photo2}" width='350' photoCssClass='innerGlow scmscPhoto' caption='Caption 2' captionCssClass='scmsCaption' />
        </div>
	</div>
	<g:render template="/widget/updateHtmlWidgetDialog" plugin="simple-cms" />
    <g:render template="/widget/updatePhotoDialog" plugin="simple-cms" />
</body>
</html>

