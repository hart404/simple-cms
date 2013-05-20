
<%@ page import="simple.cms.SCMSDocument" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="mainLayout">
		<g:set var="entityName" value="${message(code: 'SCMSDocument.label', default: 'Document')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-SCMSDocument" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-SCMSDocument" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list SCMSDocument">
			
				<g:if test="${SCMSDocumentInstance?.source}">
				<li class="fieldcontain">
					<span id="source-label" class="property-label"><g:message code="SCMSDocument.source.label" default="Source" /></span>
					
						<span class="property-value" aria-labelledby="source-label"><g:fieldValue bean="${SCMSDocumentInstance}" field="source"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${SCMSDocumentInstance?.fileName}">
				<li class="fieldcontain">
					<span id="fileName-label" class="property-label"><g:message code="SCMSDocument.fileName.label" default="File Name" /></span>
					
						<span class="property-value" aria-labelledby="fileName-label"><g:fieldValue bean="${SCMSDocumentInstance}" field="fileName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${SCMSDocumentInstance?.path}">
				<li class="fieldcontain">
					<span id="path-label" class="property-label"><g:message code="SCMSDocument.path.label" default="Path" /></span>
					
						<span class="property-value" aria-labelledby="path-label"><g:fieldValue bean="${SCMSDocumentInstance}" field="path"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${SCMSDocumentInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="SCMSDocument.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${SCMSDocumentInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${SCMSDocumentInstance?.allKeywords}">
				<li class="fieldcontain">
					<span id="allKeywords-label" class="property-label"><g:message code="SCMSDocument.allKeywords.label" default="All Keywords" /></span>
					
						<span class="property-value" aria-labelledby="allKeywords-label"><g:fieldValue bean="${SCMSDocumentInstance}" field="allKeywords"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${SCMSDocumentInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="SCMSDocument.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${SCMSDocumentInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${SCMSDocumentInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="SCMSDocument.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${SCMSDocumentInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${SCMSDocumentInstance?.originalFileName}">
				<li class="fieldcontain">
					<span id="originalFileName-label" class="property-label"><g:message code="SCMSDocument.originalFileName.label" default="Original File Name" /></span>
					
						<span class="property-value" aria-labelledby="originalFileName-label"><g:fieldValue bean="${SCMSDocumentInstance}" field="originalFileName"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${SCMSDocumentInstance?.id}" />
					<g:link class="edit" action="edit" id="${SCMSDocumentInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
