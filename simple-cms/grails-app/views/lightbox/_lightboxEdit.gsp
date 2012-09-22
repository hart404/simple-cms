    <script>
    $('#spinnerlb').hide();
    jQuery.ajaxSetup({
        beforeSend: function() {
            var element = $('#spinnerlb');
            element.css('visibility', 'visible');
        },
        complete: function(){
            var element = $('#spinnerlb');
            element.css('visibility', 'hidden');
        },
        success: function() {}
    });

    var photoIds = [];
    var lightboxWidgetId = '';
    var photoWidgetsHTMLId = '#photoWidgets';

    $(function() {        
        $("#dialog-form-lightbox").dialog({
            autoOpen: false,
            width: 800,
            height: 800,
            modal: true,
            buttons: {
                "Search": function() {
                	openPhotoSelectMultipleDialog(addPhotoId, removePhotoId, updateLightboxPhotoIds);
                },        
                "Close": function() {
                    $(this).dialog("close");
                    window.location.reload();
                }
            },
            close: function() {
                
            },
        });

    });

    var lightboxId;

    function editLinkPhoto(id) {
    	lightboxId = id;
        openPhotoSelectDialog(updatePhotoWidgetPhoto);
    }

    function updatePhotoWidgetPhoto(fileName, photoId) {
        jQuery.ajax({type:'POST', data:{'photoId': photoId, 'id': lightboxId}, url:"<g:createLink controller='lightbox' action='updateLinkPhoto' />", 
            success:function(data,textStatus){window.location.reload()},error:function(XMLHttpRequest,textStatus,errorThrown){console.log(errorThrown)}});
        return false;
    }

    function updatePhotoWidgetCaption(caption, photoWidgetId) {
        jQuery.ajax({type:'POST', data:{'caption': caption, 'id': photoWidgetId}, url:"<g:createLink controller='widget'
            action='updatePhotoWidgetCaption' />",success:function(data,textStatus){},error:function(XMLHttpRequest,textStatus,errorThrown){console.log(errorThrown)}});    
    }

    function removePhotoWidget(lightboxId, photoWidgetId) {
        jQuery.ajax({type:'POST', data:{'id': lightboxId, 'photoWidgetId': photoWidgetId}, url:"<g:createLink controller='lightbox'
            action='deletePhotoWidget' />", success:function(data,textStatus){}, error:function(XMLHttpRequest,textStatus,errorThrown){console.log(errorThrown)}}); 
        getLightboxPhotoWidgets(lightboxId, 0, 5);   
    }
        
    function openLightboxUpdate(id) {
        lightboxWidgetId = id;
    	getLightboxPhotoWidgets(id, 0, 5);
        $("#dialog-form-lightbox").dialog("open");
    }

    function getLightboxPhotoWidgets(id, offset, max) {
        jQuery.ajax({type:'GET', data:{'id': id, 'offset': offset, 'max': max}, url:"<g:createLink controller='lightbox'
            action='lightboxPhotoWidgets' />",success:function(data,textStatus){jQuery('#photoWidgets').html(data);},error:function(XMLHttpRequest,textStatus,errorThrown){console.log(errorThrown)}});
    }

    function updateLightboxPhotoIds() {
    	jQuery.ajax({type:'POST', data:{'photoIds': photoIds, 'id': lightboxWidgetId}, url:"<g:createLink controller='lightbox'
            action='updateLightboxPhotoIds' />",success:function(data,textStatus){},error:function(XMLHttpRequest,textStatus,errorThrown){console.log(errorThrown)}});
    	getLightboxPhotoWidgets(lightboxWidgetId, 0, 5);
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

    </script>
    <div id="dialog-form-lightbox" title="Lightbox Photo Update" style="display: none">
        <img src="<g:createLinkTo dir='/images' file='spinner.gif'/>" id="spinnerlb" style="display: none" />
        <div id="photoWidgets"></div>    
    </div>
    
