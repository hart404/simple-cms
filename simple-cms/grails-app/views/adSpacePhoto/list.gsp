
<%@ page import="simple.cms.SCMSAdSpacePhoto" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="generatedLayout">
		<g:set var="entityName" value="${message(code: 'adSpacePhoto.label', default: 'AdSpacePhoto')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
        <div class="generalContainer">
		<a href="#list-adSpacePhoto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-adSpacePhoto" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="title" title="${message(code: 'adSpacePhoto.title.label', default: 'Title')}" />
					
						<g:sortableColumn property="displayStartDate" title="${message(code: 'adSpacePhoto.displayStartDate.label', default: 'Display Start Date')}" />
					
                        <g:sortableColumn property="displayEndDate" title="${message(code: 'adSpacePhoto.displayEndDate.label', default: 'Display End Date')}" />
                    
						<th><g:message code="adSpacePhoto.photo.label" default="Photo" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${adSpacePhotoInstanceList}" status="i" var="adSpacePhotoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${adSpacePhotoInstance.id}">${fieldValue(bean: adSpacePhotoInstance, field: "title")}</g:link></td>
					
						<td>${fieldValue(bean: adSpacePhotoInstance, field: "displayStartDate")}</td>
					
                        <td>${fieldValue(bean: adSpacePhotoInstance, field: "displayEndDate")}</td>
                    
						<td>${fieldValue(bean: adSpacePhotoInstance, field: "photo")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${adSpacePhotoInstanceTotal}" />
			</div>
		</div>
		</div>
	</body>
</html>
