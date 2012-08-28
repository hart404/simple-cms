<%@ page import="simple.cms.SCMSPage" %>


<div class="fieldcontain ${hasErrors(bean: pageInstance, field: 'title', 'error')} required">
    <label for="title">
        <g:message code="SCMSPage.title.label" default="Title" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="title" maxlength="100" required="true" value="${pageInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pageInstance, field: 'link', 'error')} required">
	<label for="uri">
		<g:message code="SCMSPage.link.label" default="Link" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="link" maxlength="40" required="true" value="${pageInstance?.link}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pageInstance, field: 'template', 'error')} required">
	<label for="template">
		<g:message code="SCMSPage.template.label" default="Template" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="template" name="template.id" from="${simple.cms.SCMSPageTemplate.list()}" optionKey="id" required="" value="${pageInstance?.template?.id}" class="many-to-one"/>
</div>

