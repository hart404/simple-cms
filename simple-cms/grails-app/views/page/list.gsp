
<%@ page import="simple.cms.SCMSPage" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="mainLayout">
		<g:set var="entityName" value="${message(code: 'SCMSPage.label', default: 'SCMSPage')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-SCMSPage" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-SCMSPage" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
                        <g:sortableColumn property="title" title="${message(code: 'SCMSPage.title.label', default: 'Title')}" />
					
						<g:sortableColumn property="link" title="${message(code: 'SCMSPage.link.label', default: 'Link')}" />
					
						<th><g:message code="SCMSPage.template.label" default="Template" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${pageInstanceList}" status="i" var="pageInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="edit" id="${pageInstance.id}">${fieldValue(bean: pageInstance, field: "title")}</g:link></td>
					    <td>${pageInstance.link}</td>
						<td>${fieldValue(bean: pageInstance, field: "template")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${pageInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
