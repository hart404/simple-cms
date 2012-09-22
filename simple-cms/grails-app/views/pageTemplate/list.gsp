
<%@ page import="simple.cms.SCMSPageTemplate" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="generatedLayout">
		<g:set var="entityName" value="${message(code: 'SCMSPageTemplate.label', default: 'SCMSPageTemplate')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-SCMSPageTemplate" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-SCMSPageTemplate" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'SCMSPageTemplate.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="description" title="${message(code: 'SCMSPageTemplate.description.label', default: 'Description')}" />
					
						<g:sortableColumn property="associatedGSP" title="${message(code: 'SCMSPageTemplate.associatedGSP.label', default: 'Associated GSP')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${pageTemplateInstanceList}" status="i" var="pageTemplateInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${pageTemplateInstance.id}">${fieldValue(bean: pageTemplateInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: pageTemplateInstance, field: "description")}</td>
					
						<td>${fieldValue(bean: pageTemplateInstance, field: "associatedGSP")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${pageTemplateInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
