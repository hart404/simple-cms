
<%@ page import="simple.cms.SCMSPage" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="generatedLayout">
		<g:set var="entityName" value="${message(code: 'SCMSPage.label', default: 'SCMSPage')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-SCMSPage" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-SCMSPage" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list SCMSPage">
			
				<g:if test="${pageInstance?.title}">
                <li class="fieldcontain">
                    <span id="title-label" class="property-label"><g:message code="SCMSPage.title.label" default="Title" /></span>
                    
                        <span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${pageInstance}" field="title"/></span>
                    
                </li>
                </g:if>
            
                <g:if test="${pageInstance?.link}">
				<li class="fieldcontain">
					<span id="link-label" class="property-label"><g:message code="SCMSPage.link.label" default="Link" /></span>
					
						<span class="property-value" aria-labelledby="link-label"><g:fieldValue bean="${pageInstance}" field="link"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${pageInstance?.template}">
				<li class="fieldcontain">
					<span id="template-label" class="property-label"><g:message code="SCMSPage.template.label" default="Template" /></span>
					
						<span class="property-value" aria-labelledby="template-label"><g:link controller="pageTemplate" action="show" id="${pageInstance?.template?.id}">${pageInstance?.template?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${pageInstance?.widgets}">
				<li class="fieldcontain">
					<span id="widgets-label" class="property-label"><g:message code="SCMSPage.widgets.label" default="Widgets" /></span>
					
						<g:each in="${pageInstance.widgets}" var="w">
						<span class="property-value" aria-labelledby="widgets-label"><g:link controller="SCMSWidget" action="show" id="${w.id}">${w?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${pageInstance?.id}" />
					<g:link class="edit" action="edit" id="${pageInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
