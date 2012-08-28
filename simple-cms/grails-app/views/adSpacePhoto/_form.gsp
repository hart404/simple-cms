<%@ page import="simple.cms.SCMSAdSpacePhoto" %>
<%@ page import="simple.cms.SCMSPhoto" %>



<div class="fieldcontain ${hasErrors(bean: adSpacePhotoInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="adSpacePhoto.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" required="" value="${adSpacePhotoInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: adSpacePhotoInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="adSpacePhoto.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${adSpacePhotoInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: adSpacePhotoInstance, field: 'photo', 'error')} required">
    <label for="photo">
        <g:message code="adSpacePhoto.photo.label" default="Photo" />
        <span class="required-indicator">*</span>
    </label>
    <g:select id="photo" name="photo.id" from="${simple.cms.SCMSPhoto.list()}" optionKey="id" required="" value="${adSpacePhotoInstance?.photo?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: adSpacePhotoInstance, field: 'link', 'error')} ">
    <label for="link">
        <g:message code="adSpacePhoto.link.label" default="Link" />
        
    </label>
    <g:textField name="link" value="${adSpacePhotoInstance?.link}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: adSpacePhotoInstance, field: 'displayStartDate', 'error')} required">
    <label for="displayStartDate">
        <g:message code="adSpacePhoto.displayStartDate.label" default="Display Start Date" />
        <span class="required-indicator">*</span>
    </label>
    <joda:datePicker name="displayStartDate" value="${adSpacePhotoInstance?.displayStartDate}" ></joda:datePicker>
</div>

<div class="fieldcontain ${hasErrors(bean: adSpacePhotoInstance, field: 'displayEndDate', 'error')} required">
	<label for="displayEndDate">
		<g:message code="adSpacePhoto.displayEndDate.label" default="Display End Date" />
		<span class="required-indicator">*</span>
	</label>
	<joda:datePicker name="displayEndDate" value="${adSpacePhotoInstance?.displayEndDate}" ></joda:datePicker>
</div>


