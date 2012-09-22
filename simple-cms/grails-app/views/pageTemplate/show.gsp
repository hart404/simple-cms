
<%@ page import="simple.cms.SCMSPageTemplate" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="generatedLayout">
		<g:set var="entityName" value="${message(code: 'SCMSPageTemplate.label', default: 'SCMSPageTemplate')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-SCMSPageTemplate" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-SCMSPageTemplate" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list SCMSPageTemplate">
			
				<g:if test="${pageTemplateInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="SCMSPageTemplate.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${pageTemplateInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${pageTemplateInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="SCMSPageTemplate.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${pageTemplateInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${pageTemplateInstance?.associatedGSP}">
				<li class="fieldcontain">
					<span id="associatedGSP-label" class="property-label"><g:message code="SCMSPageTemplate.associatedGSP.label" default="Associated GSP" /></span>
					
						<span class="property-value" aria-labelledby="associatedGSP-label"><g:fieldValue bean="${pageTemplateInstance}" field="associatedGSP"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${pageTemplateInstance?.widgetCreators}">
				<li class="fieldcontain">
					<span id="widgetCreators-label" class="property-label"><g:message code="SCMSPageTemplate.widgetCreators.label" default="Widget Creators" /></span>
					
						<g:each in="${pageTemplateInstance.widgetCreators}" var="w">
						<span class="property-value" aria-labelledby="widgetCreators-label"><g:link controller="SCMSWidgetCreator" action="show" id="${w.id}">${w?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${pageTemplateInstance?.id}" />
					<g:link class="edit" action="edit" id="${pageTemplateInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
