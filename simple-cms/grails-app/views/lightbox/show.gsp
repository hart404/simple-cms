
<%@ page import="simple.cms.SCMSLightboxWidget" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'SCMSLightboxWidget.label', default: 'SCMSLightboxWidget')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-SCMSLightboxWidget" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-SCMSLightboxWidget" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list SCMSLightboxWidget">
			
				<g:if test="${SCMSLightboxWidgetInstance?.widgetId}">
				<li class="fieldcontain">
					<span id="widgetId-label" class="property-label"><g:message code="SCMSLightboxWidget.widgetId.label" default="Widget Id" /></span>
					
						<span class="property-value" aria-labelledby="widgetId-label"><g:fieldValue bean="${SCMSLightboxWidgetInstance}" field="widgetId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${SCMSLightboxWidgetInstance?.cssClass}">
				<li class="fieldcontain">
					<span id="cssClass-label" class="property-label"><g:message code="SCMSLightboxWidget.cssClass.label" default="Css Class" /></span>
					
						<span class="property-value" aria-labelledby="cssClass-label"><g:fieldValue bean="${SCMSLightboxWidgetInstance}" field="cssClass"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${SCMSLightboxWidgetInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="SCMSLightboxWidget.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${SCMSLightboxWidgetInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${SCMSLightboxWidgetInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="SCMSLightboxWidget.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${SCMSLightboxWidgetInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${SCMSLightboxWidgetInstance?.id}" />
					<g:link class="edit" action="edit" id="${SCMSLightboxWidgetInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
