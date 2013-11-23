
<%@ page import="simple.cms.SCMSDocument" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="mainLayout">
		<g:set var="entityName" value="${message(code: 'SCMSDocument.label', default: 'Document')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-SCMSDocument" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="list-SCMSDocument" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<p>Click on the original file name to update the description and/or keywords. Please remember to put your program in the keywords.</p>
			<table>
				<thead>
					<tr>
                        <g:sortableColumn property="originalFileName" title="${message(code: 'document.originalFileName.label', default: 'Original File Name')}" />
					    <th>Link</th>
                        <g:sortableColumn property="description" title="${message(code: 'document.description.label', default: 'Description')}" />
                        <g:sortableColumn property="allKeywords" title="${message(code: 'document.keywords.label', default: 'Keywords')}" />
                        <g:sortableColumn property="dateCreated" title="${message(code: 'document.dateCreated.label', default: 'Date Created')}" />
					</tr>
				</thead>
				<tbody>
				<g:each in="${SCMSDocumentInstanceList}" status="i" var="SCMSDocumentInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="edit" id="${SCMSDocumentInstance.id}">${fieldValue(bean: SCMSDocumentInstance, field: "originalFileName")}</g:link></td>
					
						<td><a href='${SCMSDocumentInstance.fullPath()}' target='_blank'>Click to view</a></td>
					
						<td>${fieldValue(bean: SCMSDocumentInstance, field: "description")}</td>
					
						<td>${fieldValue(bean: SCMSDocumentInstance, field: "allKeywords")}</td>
					
						<td><g:formatDate date="${SCMSDocumentInstance.dateCreated}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${SCMSDocumentInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
