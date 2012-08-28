<div id="dialog-form" title="Search for Photos" style="display: none">
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

    $(function() {
        
        $("#dialog-form").dialog({
            autoOpen: false,
            height: 800,
            width: 600,
            modal: true,
            buttons: {
                "Search": function() {
                    searchForKeywords($("#keywords").val(), 0);
                },
                "Cancel": function() {
                    $(this).dialog("close");
                }
            },
            close: function() {
            }
        });

    });

    function editPhoto(id) {
        photoWidgetId = id;
        $('#results').html('');
        $("#dialog-form").dialog("open");
        return false;
    }


    function searchForKeywords(keywords, offset) {
        jQuery.ajax({type:'POST', data:{'keywords': keywords, 'offset': offset}, url:"<g:createLink controller='photo'
            action='searchForPhotos' />",success:function(data,textStatus){jQuery('#results').html(data);},error:function(XMLHttpRequest,textStatus,errorThrown){console.log(errorThrown)}});
    }

    function selectPhoto(fileName, photoId) {
        jQuery.ajax({type:'POST', data:{'photoId': photoId, 'id': photoWidgetId}, url:"<g:createLink controller='widget' action='updatePhotoWidgetPhoto' />", 
            success:function(data,textStatus){window.location.reload()},error:function(XMLHttpRequest,textStatus,errorThrown){console.log(errorThrown)}});
        $("#dialog-form").dialog("close");
        return false;
    }
    </script>
    <form>
    <fieldset>
        <label for="keywords">Keywords</label>
        <input type="text" name="keywords" id="keywords" value="" class="text ui-widget-content ui-corner-all" />
        <img src="<g:createLinkTo dir='/images' file='spinner.gif'/>" id="spinner"/>
    </fieldset>
    </form>
    <div id="results"></div>
</div>
