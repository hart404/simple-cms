<%@ page import="simple.cms.SCMSPageTemplate" %>



<div class="fieldcontain ${hasErrors(bean: pageTemplateInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="SCMSPageTemplate.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" maxlength="100" required="" value="${pageTemplateInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pageTemplateInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="SCMSPageTemplate.description.label" default="Description" />
		
	</label>
	<g:textArea name="description" cols="40" rows="5" maxlength="1000" value="${pageTemplateInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pageTemplateInstance, field: 'associatedGSP', 'error')} required">
	<label for="associatedGSP">
		<g:message code="SCMSPageTemplate.associatedGSP.label" default="Associated GSP" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="associatedGSP" maxlength="40" required="" value="${pageTemplateInstance?.associatedGSP}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pageTemplateInstance, field: 'widgetCreators', 'error')} required">
	<label for="widgetCreators">
		<g:message code="SCMSPageTemplate.widgetCreators.label" default="Widget Creators" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="widgetCreators" from="${simple.cms.SCMSWidgetCreator.list()}" multiple="multiple" optionKey="id" size="5" required="" value="${pageTemplateInstance?.widgetCreators*.id}" class="many-to-many"/>
</div>

