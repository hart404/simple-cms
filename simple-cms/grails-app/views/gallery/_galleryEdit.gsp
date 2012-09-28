    <script>

    $(function() {
        // initialize scrollable
        $(".scrollable").scrollable();
    });
    
    $('#spinnerGallery').hide();
    jQuery.ajaxSetup({
        beforeSend: function() {
            var element = $('#spinnerGallery');
            element.css('visibility', 'visible');
        },
        complete: function(){
            var element = $('#spinnerGallery');
            element.css('visibility', 'hidden');
        },
        success: function() {}
    });

    var galleryPhotoIds = [];
    var galleryWidgetId = '';
    var galleryPhotoWidgetsHTMLId = '#galleryPhotoWidgets';

    $(function() {        
        $("#dialog-form-gallery").dialog({
            autoOpen: false,
            width: 800,
            height: 800,
            modal: true,
            buttons: {
                "Search": function() {
                	openPhotoSelectMultipleDialog(addGalleryPhotoId, removeGalleryPhotoId, updateGalleryPhotoIds);
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

    function updateGalleryPhotoWidgetCaption(caption, photoWidgetId) {
        jQuery.ajax({type:'POST', data:{'caption': caption, 'id': photoWidgetId}, url:"<g:createLink controller='widget'
            action='updatePhotoWidgetCaption' />",success:function(data,textStatus){},error:function(XMLHttpRequest,textStatus,errorThrown){console.log(errorThrown)}});    
    }

    function removeGalleryPhotoWidget(galleryId, photoWidgetId) {
        jQuery.ajax({type:'POST', data:{'id': galleryId, 'photoWidgetId': photoWidgetId}, url:"<g:createLink controller='gallery'
            action='deletePhotoWidget' />", success:function(data,textStatus){}, error:function(XMLHttpRequest,textStatus,errorThrown){console.log(errorThrown)}}); 
        getGalleryPhotoWidgets(galleryId, 0, 5);   
    }
        
    function openGalleryUpdate(id) {
        galleryWidgetId = id;
    	getGalleryPhotoWidgets(id, 0, 5);
        $("#dialog-form-gallery").dialog("open");
    }

    function getGalleryPhotoWidgets(id, offset, max) {
        jQuery.ajax({type:'GET', data:{'id': id, 'offset': offset, 'max': max}, url:"<g:createLink controller='gallery'
            action='galleryPhotoWidgets' />",success:function(data,textStatus){jQuery('#galleryPhotoWidgets').html(data);},error:function(XMLHttpRequest,textStatus,errorThrown){console.log(errorThrown)}});
    }

    function updateGalleryPhotoIds() {
    	jQuery.ajax({type:'POST', data:{'photoIds': galleryPhotoIds, 'id': galleryWidgetId}, url:"<g:createLink controller='gallery'
            action='updateGalleryPhotoIds' />",success:function(data,textStatus){},error:function(XMLHttpRequest,textStatus,errorThrown){console.log(errorThrown)}});
    	getGalleryPhotoWidgets(galleryWidgetId, 0, 5);
    }

    function addGalleryPhotoId(id) {
        galleryPhotoIds.push(id);
    }

    function removeGalleryPhotoId(id) {
        // Laziness because I can't be bothered figuring out how to remove an element from an array
        var newPhotoIds = [];
        for (var index in galleryPhotoIds) {
            if (id != galleryPhotoIds[index]) {
                newPhotoIds.push(galleryPhotoIds[index]);
            }
        }
        galleryPhotoIds = newPhotoIds;
    }

    function editGalleryTitle(id, widgetId) {
    	createEditor(widgetId, id, updateGalleryTitle);
    }

    function updateGalleryTitle(html, id) {
        jQuery.ajax({type:'POST', data:{'title': html, 'id': id}, url:"<g:createLink controller='gallery'
            action='updateGalleryTitle' />",success:function(data,textStatus){},error:function(XMLHttpRequest,textStatus,errorThrown){console.log(errorThrown)}});    
    }

    </script>
    <div id="dialog-form-gallery" title="Gallery Photo Update" style="display: none">
        <img src="<g:createLinkTo dir='/images' file='spinner.gif'/>" id="spinnerGallery" style="display: none" />
        <div id="galleryPhotoWidgets"></div>    
    </div>
    
