<html>
<head>
<meta name="layout" content="contentSidebarLayout">
<meta name="description" content="${page.descriptionMetadata}" />
<meta name="keywords" content="${page.keywordsMetadata}" />
<title>${title}</title>
<link rel="stylesheet" href="<g:createLinkTo dir='/js/scrollable/css' file='scrollable-buttons.css'/>" type="text/css"/>
<link rel="stylesheet" href="<g:createLinkTo dir='/js/scrollable/css' file='scrollable-horizontal.css'/>" type="text/css"/>
<script type="text/javascript" src="<g:createLinkTo dir='/js/scrollable/js' file='jquery.tools.min.js'/>"></script>
</head>
<body>
    <div class="contentContainer">
        <div class="scmsPeoplePhotoContainer">
            <div style="float: right; padding: 10px; ">
                <scms:photoWidget widget="${photo1}" width='300' photoCssClass='innerGlow scmsPhoto' caption='Caption 1' captionCssClass='scmsCaption' />
            </div>
            <div style="float: right; padding: 10px; ">
                <scms:photoWidget widget="${photo2}" width='300' photoCssClass='innerGlow scmsPhoto' caption='Caption 2' captionCssClass='scmsCaption' />
            </div>
        </div>
        <div class="scmsHtmlContainer">
            <scms:htmlWidget widget="${html1}" />
        </div>
        <div class="scmsGalleryContainer">
	        <scms:galleryWidget widget="${gallery1}" />
	        <scms:galleryWidget widget="${gallery2}" /> 
        </div>       
    </div>
    <g:render template="/widget/updateHtmlWidgetDialog" plugin="simple-cms" />
    <g:render template="/widget/updatePhotoWidget" plugin="simple-cms" />
    <g:render template="/gallery/galleryEdit" plugin="simple-cms" />
    <g:render template="/photo/searchPhotoMultipleDialog" plugin="simple-cms" />
    <g:render template="/photo/searchPhotoDialog" plugin="simple-cms" />
</body>
</html>

