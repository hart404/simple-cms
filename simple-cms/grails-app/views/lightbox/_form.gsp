<%@ page import="simple.cms.SCMSLightboxWidget" %>



<div class="fieldcontain ${hasErrors(bean: SCMSLightboxWidgetInstance, field: 'widgetId', 'error')} ">
	<label for="widgetId">
		<g:message code="SCMSLightboxWidget.widgetId.label" default="Widget Id" />
		
	</label>
	<g:textField name="widgetId" maxlength="30" value="${SCMSLightboxWidgetInstance?.widgetId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: SCMSLightboxWidgetInstance, field: 'cssClass', 'error')} ">
	<label for="cssClass">
		<g:message code="SCMSLightboxWidget.cssClass.label" default="Css Class" />
		
	</label>
	<g:textField name="cssClass" value="${SCMSLightboxWidgetInstance?.cssClass}"/>
</div>

