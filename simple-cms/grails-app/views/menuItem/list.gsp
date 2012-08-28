
<%@ page import="simple.cms.SCMSMenuItem" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'SCMSMenuItem.label', default: 'SCMSMenuItem')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-SCMSMenuItem" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-SCMSMenuItem" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="title" title="${message(code: 'SCMSMenuItem.title.label', default: 'Title')}" />
					
						<g:sortableColumn property="link" title="${message(code: 'SCMSMenuItem.link.label', default: 'Link')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${menuItemInstanceList}" status="i" var="menuItemInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${menuItemInstance.id}">${fieldValue(bean: menuItemInstance, field: "title")}</g:link></td>
					
						<td>${fieldValue(bean: menuItemInstance, field: "link")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${menuItemInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
