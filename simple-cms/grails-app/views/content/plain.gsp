<html>
<head>
<meta name="layout" content="contentSidebarLayout">
<g:if test="${page?.descriptionMetadata}">
    <meta name="description" content="${page?.descriptionMetadata}" />
</g:if>
<g:if test="${page?.keywordsMetadata}">
    <meta name="keywords" content="${page?.keywordsMetadata}" />
</g:if>
<title>${page.title}</title>
<link rel="stylesheet" href="<g:createLinkTo dir='/js/lightbox/css' file='lightbox.css'/>" type="text/css"/>
<script type="text/javascript" src="<g:createLinkTo dir='/js/lightbox/js' file='lightbox.js'/>"></script>
</head>
<body>
    <div class="contentContainer">
        <div class="scmsHtmlContainer">
            <scms:htmlWidget widget="${html1}" />
        </div>
    </div>
    <g:render template="/widget/updateHtmlWidgetDialog" plugin="simple-cms" />
</body>
</html>
