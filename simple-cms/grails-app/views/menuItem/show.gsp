
<%@ page import="simple.cms.SCMSMenuItem" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="generatedLayout">
		<g:set var="entityName" value="${message(code: 'SCMSMenuItem.label', default: 'SCMSMenuItem')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-SCMSMenuItem" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-SCMSMenuItem" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list SCMSMenuItem">
			
				<g:if test="${menuItemInstance?.title}">
				<li class="fieldcontain">
					<span id="title-label" class="property-label"><g:message code="SCMSMenuItem.title.label" default="Title" /></span>
					
						<span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${menuItemInstance}" field="title"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${menuItemInstance?.link}">
				<li class="fieldcontain">
					<span id="link-label" class="property-label"><g:message code="SCMSMenuItem.link.label" default="Link" /></span>
					
						<span class="property-value" aria-labelledby="link-label"><g:fieldValue bean="${menuItemInstance}" field="link"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${menuItemInstance?.roles}">
				<li class="fieldcontain">
					<span id="roles-label" class="property-label"><g:message code="SCMSMenuItem.roles.label" default="Roles" /></span>
					
						<span class="property-value" aria-labelledby="roles-label"><g:fieldValue bean="${menuItemInstance}" field="roles"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${menuItemInstance?.id}" />
					<g:link class="edit" action="edit" id="${menuItemInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
