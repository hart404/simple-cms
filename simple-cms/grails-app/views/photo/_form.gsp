<%@ page import="simple.cms.SCMSPhoto" %>



<div class="fieldcontain ${hasErrors(bean: photoInstance, field: 'originalFileName', 'error')} ">
    <label for="originalFileName">
        <g:message code="SCMSPhoto.originalFileName.label" default="Original File Name" />
        
    </label>
    <g:textField name="originalFileName" value="${photoInstance?.originalFileName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: photoInstance, field: 'width', 'error')} required">
    <label for="width">
        <g:message code="SCMSPhoto.width.label" default="Width" />
        <span class="required-indicator">*</span>
    </label>
    <g:field name="width" type="number" value="${photoInstance.width}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: photoInstance, field: 'height', 'error')} required">
    <label for="height">
        <g:message code="SCMSPhoto.height.label" default="Height" />
        <span class="required-indicator">*</span>
    </label>
    <g:field name="height" type="number" value="${photoInstance.height}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: photoInstance, field: 'path', 'error')} ">
	<label for="path">
		<g:message code="SCMSPhoto.path.label" default="Path" />
		
	</label>
	<g:textField name="path" value="${photoInstance?.path}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: photoInstance, field: 'artist', 'error')} ">
	<label for="artist">
		<g:message code="SCMSPhoto.artist.label" default="Artist" />
		
	</label>
	<g:textField name="artist" value="${photoInstance?.artist}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: photoInstance, field: 'copyright', 'error')} ">
	<label for="copyright">
		<g:message code="SCMSPhoto.copyright.label" default="Copyright" />
		
	</label>
	<g:textField name="copyright" value="${photoInstance?.copyright}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: photoInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="SCMSPhoto.description.label" default="Description" />
		
	</label>
	<g:textArea name="description" cols="40" rows="5" maxlength="2000" value="${photoInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: photoInstance, field: 'usageRights', 'error')} ">
	<label for="usageRights">
		<g:message code="SCMSPhoto.usageRights.label" default="Usage Rights" />
		
	</label>
	<g:textField name="usageRights" value="${photoInstance?.usageRights}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: photoInstance, field: 'allKeywords', 'error')} ">
	<label for="allKeywords">
		<g:message code="SCMSPhoto.allKeywords.label" default="All Keywords" />
		
	</label>
	<g:textField name="allKeywords" value="${photoInstance?.allKeywords}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: photoInstance, field: 'source', 'error')} ">
    <label for="source">
        <g:message code="SCMSPhoto.source.label" default="Source"cols="60"/>
        
    </label>
    <g:textField name="source" value="${photoInstance?.source}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: photoInstance, field: 'fileName', 'error')} ">
    <label for="fileName">
        <g:message code="SCMSPhoto.fileName.label" default="File Name" />
        
    </label>
    <g:textField name="fileName" value="${photoInstance?.fileName}"/>
</div>





