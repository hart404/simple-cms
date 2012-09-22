<g:if test="${searchResult.results.size() == 0}"> 
    No results    
</g:if>
<g:else>
	<ul>
		<g:each var="photo" in="${searchResult.results}">
			<li> 
				<g:if test="${photo.height > 100}">
					<img src="${photo.fullPath()}" height="100" width="${(100 / photo.height) * photo.width}" />
				</g:if> 
				<g:else>
					<img src="${photo.fullPath()}" />
				</g:else> 
				${photo.shortenedFileName()} - ${photo.width}x${photo.height}
				<input type="checkbox" name="Select" value="select" onclick="selectOrDeselectPhoto(this, ${photo.id})" />
			</li>
		</g:each>
	</ul>
	<g:if test="${searchResult?.results}"> 
	    <g:set var="totalPages" value="${Math.ceil(searchResult.total / searchResult.max) as Integer}" />
	    <g:set var="page" value="${(searchResult.offset / searchResult.max) + 1}" />
	    <g:if test="${totalPages == 1}">
        </g:if>
	    <g:else>
	        <span class="currentStep">Page ${page} of ${totalPages}</span>
	        <input type="button" name="previous" value="Previous" id="previous" onclick='searchForKeywordsMultiple("${params.keywords}", ${searchResult.offset - searchResult.max})' />
	        <input type="button" name="next" value="Next" id="next" onclick='searchForKeywordsMultiple("${params.keywords}", ${searchResult.offset + searchResult.max})' />
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
</g:else>
