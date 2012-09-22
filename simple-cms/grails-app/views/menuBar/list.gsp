
<%@ page import="simple.cms.SCMSMenuBar" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="generatedLayout">
		<g:set var="entityName" value="${message(code: 'SCMSMenuBar.label', default: 'SCMSMenuBar')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-SCMSMenuBar" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-SCMSMenuBar" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="widgetId" title="${message(code: 'SCMSMenuBar.widgetId.label', default: 'Widget Id')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${menuBarInstanceList}" status="i" var="menuBarInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="edit" id="${menuBarInstance.id}">${fieldValue(bean: menuBarInstance, field: "widgetId")}</g:link></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${menuBarInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
