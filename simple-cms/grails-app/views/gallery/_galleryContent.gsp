<div class="box full" >
    <div class="scmsGalleryTitle scmsHtmlContainer" id="title${gallery.widgetId}">
    ${gallery.title}
    </div>
    <div style="margin: 0 auto; width: 850px; height: 240px;">
        <!-- "previous page" action -->
        <a class="prev browse left"></a>
        <!-- root element for scrollable -->
        <div class="scrollable" id="scrollable" style="height: 240px;">
            <!-- root element for the items -->
            <div class="items">   
				<g:each var="photoWidget" in="${gallery.photoWidgets}" status="index" >
				    <g:if test="${index % 4 == 0}">
				        <div style="text-align: center; width: 700px; ">
				    </g:if>
				        <div class="scmsGalleryPhotoWidget">            
				            <img src="${photoWidget.photo.fullPath()}" width="200" height="${(photoWidget.photo.height * (200 / photoWidget.photo.width)) as Integer}"/>
				            <div class="scmsGalleryCaption">${photoWidget.caption}</div>
				        </div>
				    <g:if test="${index % 4 == 3 || index == gallery.photoWidgets.size() - 1}">
				        </div>
				    </g:if>            
				</g:each>
            </div>
        </div>
        <!-- "next page" action -->
        <a class="next browse right"></a>
    </div>
</div>

