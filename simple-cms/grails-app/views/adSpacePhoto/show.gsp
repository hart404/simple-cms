
<%@ page import="simple.cms.SCMSAdSpacePhoto" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="mainLayout">
		<g:set var="entityName" value="${message(code: 'adSpacePhoto.label', default: 'AdSpacePhoto')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
	    <div class="generalContainer">
			<a href="#show-adSpacePhoto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
			<div class="nav" role="navigation">
				<ul>
					<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
					<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
					<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
				</ul>
			</div>
			<div id="show-adSpacePhoto" class="content scaffold-show" role="main">
				<h1><g:message code="default.show.label" args="[entityName]" /></h1>
				<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
				</g:if>
				<ol class="property-list adSpacePhoto">
				
					<g:if test="${adSpacePhotoInstance?.title}">
					<li class="fieldcontain">
						<span id="title-label" class="property-label"><g:message code="adSpacePhoto.title.label" default="Title" /></span>
						
							<span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${adSpacePhotoInstance}" field="title"/></span>
						
					</li>
					</g:if>
				
					<g:if test="${adSpacePhotoInstance?.description}">
					<li class="fieldcontain">
						<span id="description-label" class="property-label"><g:message code="adSpacePhoto.description.label" default="Description" /></span>
						
							<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${adSpacePhotoInstance}" field="description"/></span>
						
					</li>
					</g:if>
				
					<g:if test="${adSpacePhotoInstance?.displayEndDate}">
					<li class="fieldcontain">
						<span id="displayEndDate-label" class="property-label"><g:message code="adSpacePhoto.displayEndDate.label" default="Display End Date" /></span>
						
							<span class="property-value" aria-labelledby="displayEndDate-label"><g:fieldValue bean="${adSpacePhotoInstance}" field="displayEndDate"/></span>
						
					</li>
					</g:if>
				
					<g:if test="${adSpacePhotoInstance?.displayStartDate}">
					<li class="fieldcontain">
						<span id="displayStartDate-label" class="property-label"><g:message code="adSpacePhoto.displayStartDate.label" default="Display Start Date" /></span>
						
							<span class="property-value" aria-labelledby="displayStartDate-label"><g:fieldValue bean="${adSpacePhotoInstance}" field="displayStartDate"/></span>
						
					</li>
					</g:if>
				
					<g:if test="${adSpacePhotoInstance?.photo}">
					<li class="fieldcontain">
						<span id="photo-label" class="property-label"><g:message code="adSpacePhoto.photo.label" default="Photo" /></span>
						
							<span class="property-value" aria-labelledby="photo-label"><g:link controller="photo" action="show" id="${adSpacePhotoInstance?.photo?.id}">${adSpacePhotoInstance?.photo?.encodeAsHTML()}</g:link></span>
						
					</li>
					</g:if>
				
				</ol>
				<g:form>
					<fieldset class="buttons">
						<g:hiddenField name="id" value="${adSpacePhotoInstance?.id}" />
						<g:link class="edit" action="edit" id="${adSpacePhotoInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
						<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					</fieldset>
				</g:form>
			</div>
		</div>
	</body>
</html>
