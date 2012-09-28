<script>
    $('#spinner').hide();
    jQuery.ajaxSetup({
        beforeSend: function() {
            var element = $('#spinner');
            element.css('visibility', 'visible');
        },
        complete: function(){
            var element = $('#spinner');
            element.css('visibility', 'hidden');
        },
        success: function() {}
    });

    var photoWidgetId;

    function editPhotoWidgetPhoto(id) {
        photoWidgetId = id;
        openPhotoSelectDialog(selectPhotoForPhotoWidget);
        return false;
    }

    function selectPhotoForPhotoWidget(fileName, photoId) {
        jQuery.ajax({type:'POST', data:{'photoId': photoId, 'id': photoWidgetId}, url:"<g:createLink controller='widget' action='updatePhotoWidgetPhoto' />", 
            success:function(data,textStatus){window.location.reload(); },error:function(XMLHttpRequest,textStatus,errorThrown){console.log(errorThrown)}});
        return false;
    }
</script>
