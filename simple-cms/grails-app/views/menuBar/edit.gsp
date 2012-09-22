<%@ page import="simple.cms.SCMSMenuBar" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="generatedLayout">
		<g:set var="entityName" value="${message(code: 'SCMSMenuBar.label', default: 'SCMSMenuBar')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#edit-SCMSMenuBar" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="edit-SCMSMenuBar" class="content scaffold-edit" role="main">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${menuBarInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${menuBarInstance}" var="error">
				<li<g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form method="post" >
				<g:hiddenField name="id" value="${menuBarInstance?.id}" />
				<g:hiddenField name="version" value="${menuBarInstance?.version}" />
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
