<g:each var="photoWidget" in="${lightbox.photoWidgets}" status="index" >
    <g:if test="${index == 0}">
        <a href="${photoWidget.photo.fullPath()}" rel="lightbox[${lightbox.id}]" title="${photoWidget.caption}">
            <g:if test="${lightbox.linkPhoto == null}">
                <img src="<g:createLinkTo dir='/images/general' file='img_black-throated-sparrow-150x150.png'/>" width="100" height="100"/>
            </g:if>
            <g:else>
                <img src="${lightbox.linkPhoto.fullPath()}" width="100" height="${(lightbox.linkPhoto.height * (100 / lightbox.linkPhoto.width)) as Integer}"/>
            </g:else>
        </a>
        <br/>
        Click the image for a slideshow
    </g:if>
    <g:else>
        <a href="${photoWidget.photo.fullPath()}" rel="lightbox[${lightbox.id}]" title="${photoWidget.caption}"></a>
    </g:else>    
</g:each>
