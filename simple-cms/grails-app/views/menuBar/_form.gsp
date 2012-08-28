<%@ page import="simple.cms.SCMSMenuBar"%>
<%@ page import="simple.cms.SCMSMenuItem"%>
<%@ page import="simple.cms.SCMSMenu"%>

<div class="fieldcontain ${hasErrors(bean: menuBarInstance, field: 'widgetId', 'error')} ">
	<label for="widgetId"> <g:message code="SCMSMenuBar.widgetId.label" default="Widget Id" /></label>
	${menuBarInstance?.widgetId}
</div>

<div class="fieldcontain ${hasErrors(bean: menuBarInstance, field: 'menus', 'error')} required">
	<label for="menus"> <g:message code="SCMSMenuBar.menus.label" default="Menus" /> <span class="required-indicator">*</span>
	</label>
	<table>
		<thead>
			<tr>
				<th>Title</th>
                <th>Link</th>
                <th>Roles</th>
                <th></th>
                <th></th>
                <th></th>
                <th></th>
			</tr>
		</thead>
		<tbody>
			<g:each var="menu" in="${menuBarInstance.menus}" status="i">
				<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
	                <td>
	                   <g:field type="text" name="title" value="${menu.title}" id="title${menu.id}" />
	                </td>
	                <td>
                          <g:field type="text" name="link" value="${menu.link}" id="link${menu.id}" />
                    </td>
	                <td>
					<g:select name="roles"
						          from="${SCMSMenuItem.possibleRoles()}"
						          value="${menu?.roles}"
						          multiple="true"  id="roles${menu.id}" />
                    </td>
                    <td>
                       <button type='button' onClick="updateMenu(${menu.id});" >Update</button>
                    </td>
					<td>
					   <g:link controller="menu" action="edit" id="${menu.id}">Edit</g:link>
					</td>
                    <td>
                       <g:link controller="menu" action="delete" id="${menu.id}">Delete</g:link>
                    </td>
				</tr>
			</g:each>
			<tr>
                <td>
                    <g:field type="text" name="title" value="" id="title" />
                </td>
                <td>
                      <g:field type="text" name="link" value="" id="link" />
                </td>
                <td>
                <g:select name="roles"
                              from="${SCMSMenuItem.possibleRoles()}"
                              multiple="true"  id="roles" />
                </td>
                <td>
                   <button type='button' onClick="addMenu(${menuBarInstance.id});" >Add Menu</button>
                </td>
			</tr>
		</tbody>
	</table>
    <div id="results"></div>
    <script>
        function updateMenu(menuId) {
            var title = $("#title" + menuId).val()
            var link = $("#link" + menuId).val()
            var roles = $("#roles" + menuId).val()
            if (roles == null) {
                jQuery.ajax({type:'GET', data:{'id': menuId, 'title': title, 'link': link}, url:"<g:createLink controller='menu'
                    action='updateMenu' />",success:function(data,textStatus){jQuery('#results').html(data);},error:function(XMLHttpRequest,textStatus,errorThrown){console.log(errorThrown)}});
            } else {
	            jQuery.ajax({type:'GET', data:{'id': menuId, 'title': title, 'link': link, 'roles': roles}, url:"<g:createLink controller='menu'
	                action='updateMenu' />",success:function(data,textStatus){jQuery('#results').html(data);},error:function(XMLHttpRequest,textStatus,errorThrown){console.log(errorThrown)}});
            }
        }

        function addMenu(menuBarId) {
            var title = $("#title").val()
            var link = $("#link").val()
            var roles = $("#roles").val()
            if (roles == null) {
                jQuery.ajax({type:'GET', data:{'id': menuBarId, 'title': title, 'link': link}, url:"<g:createLink controller='menuBar'
                    action='addMenu' />",success:function(data,textStatus){jQuery('#results').html(data); window.location.reload();},error:function(XMLHttpRequest,textStatus,errorThrown){console.log(errorThrown)}});
            } else {
                jQuery.ajax({type:'GET', data:{'id': menuBarId, 'title': title, 'link': link, 'roles': roles}, url:"<g:createLink controller='menuBar'
                    action='addMenu' />",success:function(data,textStatus){jQuery('#results').html(data); window.location.reload();},error:function(XMLHttpRequest,textStatus,errorThrown){console.log(errorThrown)}});
            }
        }
                
    </script>
</div>

