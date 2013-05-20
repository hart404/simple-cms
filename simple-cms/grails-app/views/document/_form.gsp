<%@ page import="simple.cms.SCMSDocument" %>

<div class="fieldcontain ${hasErrors(bean: SCMSDocumentInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="SCMSDocument.description.label" default="Description" />
	</label>
	<g:textArea name="description" cols="40" rows="5" maxlength="2000" value="${SCMSDocumentInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: SCMSDocumentInstance, field: 'allKeywords', 'error')} ">
	<label for="allKeywords">
		<g:message code="SCMSDocument.allKeywords.label" default="Keywords" />
	</label>
	<g:textArea name="allKeywords" cols="40" rows="5" maxlength="2000" value="${SCMSDocumentInstance?.allKeywords}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: SCMSDocumentInstance, field: 'originalFileName', 'error')} ">
	<label for="originalFileName">
		<g:message code="SCMSDocument.originalFileName.label" default="Original File Name" />
	</label>
	<g:field type="text" name="originalFileName" readonly="readonly" value="${SCMSDocumentInstance?.originalFileName}" size="100" />
</div>

