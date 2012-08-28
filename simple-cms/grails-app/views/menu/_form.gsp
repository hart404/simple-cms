<%@ page import="simple.cms.SCMSMenu" %>
<%@ page import="simple.cms.SCMSMenuItem" %>

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
            <g:each var="menu" in="${menuInstance.menuItems}" status="i">
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
                       <g:if test="${menu.canHaveItemsAdded()}">
                          <button type='button' onClick="updateMenu(${menu.id});" >Update</button>
                       </g:if>
                       <g:else>
                          <button type='button' onClick="updateMenuItem(${menu.id});" >Update</button>
                       </g:else>                       
                    </td>
                    <td>
                       <g:if test="${menu.canHaveItemsAdded()}">
                          <g:link controller="menu" action="edit" id="${menu.id}">Edit</g:link>
                       </g:if>
                    </td>
                    <td>
                       <g:if test="${menu.canHaveItemsAdded()}">
                          <g:link controller="menu" action="delete" id="${menu.id}">Delete</g:link>
                       </g:if>
                       <g:else>
                          <g:link controller="menuItem" action="delete" id="${menu.id}">Delete</g:link>
                       </g:else>                       
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
                   <button type='button' onClick="addMenu(${menuInstance.id});" >Add Menu</button>
                </td>
                <td>
                   <button type='button' onClick="addMenuItem(${menuInstance.id});" >Add Menu Item</button>
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

        function updateMenuItem(menuId) {
            var title = $("#title" + menuId).val()
            var link = $("#link" + menuId).val()
            var roles = $("#roles" + menuId).val()
            if (roles == null) {
                jQuery.ajax({type:'GET', data:{'id': menuId, 'title': title, 'link': link}, url:"<g:createLink controller='menuItem'
                    action='updateMenuItem' />",success:function(data,textStatus){jQuery('#results').html(data);},error:function(XMLHttpRequest,textStatus,errorThrown){console.log(errorThrown)}});
            } else {
                jQuery.ajax({type:'GET', data:{'id': menuId, 'title': title, 'link': link, 'roles': roles}, url:"<g:createLink controller='menuItem'
                    action='updateMenuItem' />",success:function(data,textStatus){jQuery('#results').html(data);},error:function(XMLHttpRequest,textStatus,errorThrown){console.log(errorThrown)}});
            }
        }

        function addMenu(menuId) {
            var title = $("#title").val()
            var link = $("#link").val()
            var roles = $("#roles").val()
            if (roles == null) {
                jQuery.ajax({type:'GET', data:{'id': menuId, 'title': title, 'link': link}, url:"<g:createLink controller='menu'
                    action='addSubMenu' />",success:function(data,textStatus){jQuery('#results').html(data); window.location.reload();},error:function(XMLHttpRequest,textStatus,errorThrown){console.log(errorThrown)}});
            } else {
                jQuery.ajax({type:'GET', data:{'id': menuId, 'title': title, 'link': link, 'roles': roles}, url:"<g:createLink controller='menu'
                    action='addSubMenu' />",success:function(data,textStatus){jQuery('#results').html(data); window.location.reload();},error:function(XMLHttpRequest,textStatus,errorThrown){console.log(errorThrown)}});
            }
        }
            
        function addMenuItem(menuId) {
            var title = $("#title").val()
            var link = $("#link").val()
            var roles = $("#roles").val()
            if (roles == null) {
                jQuery.ajax({type:'GET', data:{'id': menuId, 'title': title, 'link': link}, url:"<g:createLink controller='menu'
                    action='addMenuItem' />",success:function(data,textStatus){jQuery('#results').html(data); window.location.reload();},error:function(XMLHttpRequest,textStatus,errorThrown){console.log(errorThrown)}});
            } else {
                jQuery.ajax({type:'GET', data:{'id': menuId, 'title': title, 'link': link, 'roles': roles}, url:"<g:createLink controller='menu'
                    action='addMenuItem' />",success:function(data,textStatus){jQuery('#results').html(data); window.location.reload();},error:function(XMLHttpRequest,textStatus,errorThrown){console.log(errorThrown)}});
            }
        }
                    
    </script>

