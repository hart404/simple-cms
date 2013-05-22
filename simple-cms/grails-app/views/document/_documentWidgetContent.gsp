<div class="scmsHtmlContainer">
<g:if test="${documentWidget?.title}">${documentWidget?.title}</g:if>
</div>
<g:each var="document" in="${documentWidget.sortedDocuments()}">
	<ul class="hikeList">
	<li><a href="${document.fullPath()}" target="_blank"><g:if test="${document.description}">${document.description}</g:if><g:else>${document.originalFileName}</g:else></a></li>
	</ul>
</g:each>