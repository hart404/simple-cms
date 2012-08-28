
<%@ page import="simple.cms.SCMSMenuBar" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="mainLayout">
		<g:set var="entityName" value="${message(code: 'SCMSMenuBar.label', default: 'SCMSMenuBar')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-SCMSMenuBar" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-SCMSMenuBar" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list SCMSMenuBar">
			
				<g:if test="${menuBarInstance?.widgetId}">
				<li class="fieldcontain">
					<span id="widgetId-label" class="property-label"><g:message code="SCMSMenuBar.widgetId.label" default="Widget Id" /></span>
					
						<span class="property-value" aria-labelledby="widgetId-label"><g:fieldValue bean="${menuBarInstance}" field="widgetId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${menuBarInstance?.menus}">
				<li class="fieldcontain">
					<span id="menus-label" class="property-label"><g:message code="SCMSMenuBar.menus.label" default="Menus" /></span>
					
						<g:each in="${menuBarInstance.menus}" var="m">
						<span class="property-value" aria-labelledby="menus-label"><g:link controller="menu" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${menuBarInstance?.id}" />
					<g:link class="edit" action="edit" id="${menuBarInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
