
<%@ page import="simple.cms.SCMSPhoto" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="generatedLayout">
		<g:set var="entityName" value="${message(code: 'photo.label', default: 'Photo')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-photo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="uploadPhotos"><g:message code="default.old.label" default="Upload Photos" /></g:link></li>
			</ul>
		</div>
		<div id="list-photo" class="content scaffold-list" role="main">
			<h1>Photos</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					    <th>Select</th>
						<g:sortableColumn property="originalFileName" title="${message(code: 'photo.originalFileName.label', default: 'Original File Name')}" />
					
						<g:sortableColumn property="artist" title="${message(code: 'photo.artist.label', default: 'Artist')}" />
					    <th>Date Created</th>
						<th><g:message code="photo.width.label" default="Width" /></th>
                        <th><g:message code="photo.height.label" default="Height" /></th>
                        <th><g:message code="photo.image.label" default="Image" /></th>
										
					</tr>
				</thead>
				<tbody>
				<g:each in="${photoInstanceList}" status="i" var="photoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
                        <td><input type="checkbox" name="Select" value="select" onclick="selectOrDeselectPhoto(this, ${photoInstance.id})" /></td>
					
						<td><g:link action="edit" id="${photoInstance.id}">${fieldValue(bean: photoInstance, field: "originalFileName")}</g:link></td>
					
						<td>${fieldValue(bean: photoInstance, field: "artist")}</td>
					    <td>${fieldValue(bean: photoInstance, field: "dateCreated")}</td>
						<td>${fieldValue(bean: photoInstance, field: "width")}</td>
					
						<td>${fieldValue(bean: photoInstance, field: "height")}</td>
					
						<td><img src="${photoInstance.fullPath()}" height="100" width="${(100 / photoInstance.height) * photoInstance.width}" /></td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${photoInstanceTotal}" />
			</div>
			<input type='button' value='Delete' onclick="deleteSelectedPhotos();">
		</div>
		<script>
		
	    var photoIds = [];
			
	    function selectOrDeselectPhoto(checkbox, id) {
	        if (checkbox.checked) {
	            addPhotoId(id);
	        } else {
	            removePhotoId(id);
	        }
	    }

	    function addPhotoId(id) {
	    	photoIds.push(id);
	    }

	    function removePhotoId(id) {
	        // Laziness because I can't be bothered figuring out how to remove an element from an array
	        var newPhotoIds = [];
	        for (var index in photoIds) {
	            if (id != photoIds[index]) {
	                newPhotoIds.push(photoIds[index]);
	            }
	        }
	        photoIds = newPhotoIds;
	    }

	    function deleteSelectedPhotos() {
		    console.log("deleting: " + photoIds);
	        jQuery.ajax({type:'POST', data:{'photoIds': photoIds}, url:"<g:createLink controller='photo' action='deleteMultiplePhotos' />",
		        success:function(data,textStatus){$('#resultsPhoto').html('<p>Success</p>'); },error:function(XMLHttpRequest,textStatus,errorThrown){$('#resultsPhoto').html('<p>Failure</p>'); }});
	        return false;
	    }	

	    </script>
	    <div id="resultsPhoto"></div>
	</body>
</html>
