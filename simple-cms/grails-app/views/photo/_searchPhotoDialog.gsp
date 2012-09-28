<div id="dialog-form-photo" title="Search for Photos" style="display: none">
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

    $(function() {
        
        $("#dialog-form-photo").dialog({
            autoOpen: false,
            height: 850,
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

        $("#search")
        .button()
        .click(function() {
            $('#resultsPhoto').html('');
            selectPhotoCallback = standardPhotoSelectCallback;
            $("#dialog-form-photo").dialog("open");
            return false;
        });

    });

    function searchForKeywords(keywords, offset) {
        jQuery.ajax({type:'POST', data:{'keywords': keywords, 'offset': offset}, url:"<g:createLink controller='photo'
            action='searchForPhotos' />",success:function(data,textStatus){jQuery('#resultsPhoto').html(data);},error:function(XMLHttpRequest,textStatus,errorThrown){console.log(errorThrown)}});
    }

    var selectPhotoCallback;

    function openPhotoSelectDialog(selectPhotoFunction) {
        selectPhotoCallback = selectPhotoFunction;
        $('#resultsPhoto').html('');
        $("#dialog-form-photo").dialog("open");
    }

    function selectPhoto(fileName, id) {
        $("#dialog-form-photo").dialog("close");
        selectPhotoCallback(fileName, id);
        return false;
    }

    function standardPhotoSelectCallback(fileName, id) {
        $("#photoId").val(id);
        $("#photoFileName").text(fileName);
    }
    
    </script>
    <form>
        <fieldset>
	        <label for="keywords">Keywords</label>
	        <input type="text" name="keywords" id="keywords" value="" class="text ui-widget-content ui-corner-all" />
	        <img src="<g:createLinkTo dir='/images' file='spinner.gif'/>" id="spinner"/>
	    </fieldset>
    </form>
    <div id="resultsPhoto"></div>
</div>
