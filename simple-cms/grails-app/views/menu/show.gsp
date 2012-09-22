
<%@ page import="simple.cms.SCMSMenu" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="generatedLayout">
		<g:set var="entityName" value="${message(code: 'SCMSMenu.label', default: 'SCMSMenu')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-SCMSMenu" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-SCMSMenu" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list SCMSMenu">
			
				<g:if test="${menuInstance?.title}">
				<li class="fieldcontain">
					<span id="title-label" class="property-label"><g:message code="SCMSMenu.title.label" default="Title" /></span>
					
						<span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${menuInstance}" field="title"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${menuInstance?.link}">
				<li class="fieldcontain">
					<span id="link-label" class="property-label"><g:message code="SCMSMenu.link.label" default="Link" /></span>
					
						<span class="property-value" aria-labelledby="link-label"><g:fieldValue bean="${menuInstance}" field="link"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${menuInstance?.roles}">
				<li class="fieldcontain">
					<span id="roles-label" class="property-label"><g:message code="SCMSMenu.roles.label" default="Roles" /></span>
					
						<span class="property-value" aria-labelledby="roles-label"><g:fieldValue bean="${menuInstance}" field="roles"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${menuInstance?.menuItems}">
				<li class="fieldcontain">
					<span id="menuItems-label" class="property-label"><g:message code="SCMSMenu.menuItems.label" default="Menu Items" /></span>
					
						<g:each in="${menuInstance.menuItems}" var="m">
						<span class="property-value" aria-labelledby="menuItems-label"><g:link controller="menuItem" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${menuInstance?.id}" />
					<g:link class="edit" action="edit" id="${menuInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
