<%@ page import="simple.cms.SCMSAdSpacePhoto"%>
<!doctype html>
<html>
<head>
<meta name="layout" content="generatedLayout">
<g:set var="entityName"
	value="${message(code: 'adSpacePhoto.label', default: 'AdSpacePhoto')}" />
<title><g:message code="default.create.label"
		args="[entityName]" /></title>
</head>
<body>
	<div class="generalContainer">
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="list" action="list">
						<g:message code="default.list.label" args="[entityName]" />
					</g:link></li>
			</ul>
		</div>
		<div id="create-adSpacePhoto" class="content scaffold-create"
			role="main">
			<h1>Create Dynamic Lead/Ad Space</h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">
					${flash.message}
				</div>
			</g:if>
			<g:hasErrors bean="${adSpacePhotoInstance}">
				<ul class="errors" role="alert">
					<g:eachError bean="${adSpacePhotoInstance}" var="error">
						<li
							<g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
								error="${error}" /></li>
					</g:eachError>
				</ul>
			</g:hasErrors>
			<g:form action="save">
				<fieldset class="form">
					<div
						class="fieldcontain ${hasErrors(bean: adSpacePhotoInstance, field: 'title', 'error')} required">
						<label for="title"> <g:message
								code="adSpacePhoto.title.label" default="Title" /> <span
							class="required-indicator">*</span>
						</label>
						<g:textField name="title" required=""
							value="${adSpacePhotoInstance?.title}" />
					</div>

					<div
						class="fieldcontain ${hasErrors(bean: adSpacePhotoInstance, field: 'description', 'error')} ">
						<label for="description"> <g:message
								code="adSpacePhoto.description.label" default="Description" />

						</label>
						<g:textField name="description"
							value="${adSpacePhotoInstance?.description}" />
					</div>

					<div
						class="fieldcontain ${hasErrors(bean: adSpacePhotoInstance, field: 'photo', 'error')} required">
						<label for="photo"> <g:message
								code="adSpacePhoto.photo.label" default="Photo" /> <span
							class="required-indicator">*</span>
						</label>
						<span id="photoFileName">-None Selected-</span>
						<button id="search">Search for Photo</button>
						<input type="hidden" name="photoId" id= "photoId" value=""/>
					</div>

					<div
						class="fieldcontain ${hasErrors(bean: adSpacePhotoInstance, field: 'link', 'error')} ">
						<label for="link"> <g:message
								code="adSpacePhoto.link.label" default="Link" />

						</label>
						<g:textField name="link" value="${adSpacePhotoInstance?.link}" />
					</div>

					<div
						class="fieldcontain ${hasErrors(bean: adSpacePhotoInstance, field: 'displayStartDate', 'error')} required">
						<label for="displayStartDate"> <g:message
								code="adSpacePhoto.displayStartDate.label"
								default="Display Start Date" /> <span
							class="required-indicator">*</span>
						</label>
						<joda:datePicker name="displayStartDate"
							value="${adSpacePhotoInstance?.displayStartDate}"></joda:datePicker>
					</div>

					<div
						class="fieldcontain ${hasErrors(bean: adSpacePhotoInstance, field: 'displayEndDate', 'error')} required">
						<label for="displayEndDate"> <g:message
								code="adSpacePhoto.displayEndDate.label"
								default="Display End Date" /> <span class="required-indicator">*</span>
						</label>
						<joda:datePicker name="displayEndDate"
							value="${adSpacePhotoInstance?.displayEndDate}"></joda:datePicker>
					</div>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save"
						value="${message(code: 'default.button.create.label', default: 'Create')}" />
				</fieldset>
			</g:form>
		</div>
		<g:render template="/photo/searchPhotoDialog" plugin="simple-cms" />
	</div>
</body>
</html>
