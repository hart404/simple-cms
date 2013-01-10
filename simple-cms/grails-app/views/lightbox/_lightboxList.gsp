<g:if test="${photoWidgets.size() > 0}">
<ul class="scmsPhotoList">
	<g:each var="photoWidget" in="${photoWidgets}">
		<li>
		    <g:if test="${photoWidget.photo.height > 100}">
				<img src="${photoWidget.photo.fullPath()}" height="100" width="${(100 / photoWidget.photo.height) * photoWidget.photo.width}" />
			</g:if> 
			<g:else>
				<img src="${photoWidget.photo.fullPath()}" />
			</g:else> 
			${photoWidget.photo.shortenedFileName()} - ${photoWidget.photo.width}x${photoWidget.photo.height}
			<input type="text" name="Caption" value="${photoWidget.caption}" id="caption${photoWidget.id}" /> 
			<input type="button" name="Update" value="Update Caption" onclick="updatePhotoWidgetCaption($('#caption${photoWidget.id}').val(), ${photoWidget.id})" /> 
			<input type="button" name="Remove" value="Remove Photo" onclick="removePhotoWidget(${lightbox.id}, ${photoWidget.id})" /></li>
	</g:each>
	    <g:if test="${photoWidgets.size() > 0}">
		  <g:set var="totalPages" value="${Math.ceil(total / max) as Integer}" />
		  <g:set var="page" value="${(offset / max) + 1}" />
		  <g:if test="${totalPages == 1}">
		  </g:if>
		  <g:else>
			<span class="currentStep">Page ${page} of ${totalPages}</span>
			<input type="button" name="previous" value="Previous" id="previous"
				onClick='getLightboxPhotoWidgets(${lightbox.id}, ${offset - max}, ${max})' />
			<input type="button" name="next" value="Next" id="next"
				onClick='getLightboxPhotoWidgets(${lightbox.id}, ${offset + max}, ${max})' />
			<g:if test="${page == 1}">
				<script>
                    $('#previous').attr('disabled', true);
                </script>
			</g:if>
			<g:else>
				<script>
                    $('#previous').attr('disabled', false);
                </script>
			</g:else>
			<g:if test="${page == totalPages}">
				<script>
                    $('#next').attr('disabled', true);
                </script>
			</g:if>
			<g:else>
				<script>
                    $('#next').attr('disabled', false);
                </script>
			</g:else>
		</g:else>
	</g:if>
</ul>
<div id="resultslbl"></div>
</g:if>
<g:else>
No photos added to this lightbox yet. Click 'Search' below to select photos.
</g:else>

