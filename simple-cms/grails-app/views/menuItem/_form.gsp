<%@ page import="simple.cms.SCMSMenuItem" %>



<div class="fieldcontain ${hasErrors(bean: menuItemInstance, field: 'title', 'error')} ">
	<label for="title">
		<g:message code="SCMSMenuItem.title.label" default="Title" />
		
	</label>
	<g:textField name="title" value="${menuItemInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: menuItemInstance, field: 'link', 'error')} ">
	<label for="link">
		<g:message code="SCMSMenuItem.link.label" default="Link" />
		
	</label>
	<g:textField name="link" value="${menuItemInstance?.link}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: menuItemInstance, field: 'roles', 'error')} ">
	<label for="roles">
		<g:message code="SCMSMenuItem.roles.label" default="Roles" />
		
	</label>
	
</div>

