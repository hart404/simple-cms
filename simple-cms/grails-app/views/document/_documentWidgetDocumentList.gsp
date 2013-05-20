<g:if test="${documents.size() > 0}">
<ul class="scmsPhotoList">
    <g:each var="document" in="${documents}">
        <li>
            ${document.shortenedFileName()} - ${document.description}
            <input type="button" name="Remove" value="Remove Document" onclick="removeDocumentWidgetDocument(${documentWidget.id}, ${document.id})" /></li>
    </g:each>
       <g:set var="totalPages" value="${Math.ceil(total / max) as Integer}" />
       <g:set var="page" value="${(offset / max) + 1}" />
       <g:if test="${totalPages == 1}">
       </g:if>
       <g:else>
         <span class="currentStep">Page ${page} of ${totalPages}</span>
         <input type="button" name="previous" value="Previous" id="previous"
             onClick='getDocumentWidgetDocuments(${documentWidget.id}, ${offset - max}, ${max})' />
         <input type="button" name="next" value="Next" id="next"
             onClick='getDocumentWidgetDocuments(${documentWidget.id}, ${offset + max}, ${max})' />
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
</ul>
<div id="resultsldl"></div>
</g:if>
<g:else>
No documents added to this document widget yet. Click 'Search' below to select documents.
</g:else>

