    <script>
    $('#spinnerpm').hide();
    jQuery.ajaxSetup({
        beforeSend: function() {
            var element = $('#spinnerpm');
            element.css('visibility', 'visible');
        },
        complete: function(){
            var element = $('#spinnerpm');
            element.css('visibility', 'hidden');
        },
        success: function() {}
    });

    $(function() {       
        $("#dialog-form-photo-multiple").dialog({
            autoOpen: false,
            height: 800,
            width: 600,
            modal: true,
            buttons: {
                "Search": function() {
                    searchForKeywordsMultiple($("#keywordspm").val(), 0);
                },
                "Select": function() {
                	$(this).dialog("close");
                	selectPhotosCallback();
                },
                "Cancel": function() {
                    $(this).dialog("close");
                }
            },
            close: function() {
            }
        });

    });

    var addPhotoCallback, removePhotoCallback, selectPhotosCallback;

    function openPhotoSelectMultipleDialog(addFunction, removeFunction, selectFunction) {
        addPhotoCallback = addFunction;
        removePhotoCallback = removeFunction;
        selectPhotosCallback = selectFunction;
        $("#resultspm").html("");
        $("#dialog-form-photo-multiple").dialog("open");
    }

    function searchForKeywordsMultiple(keywords, offset) {
        jQuery.ajax({type:'POST', data:{'keywords': keywords, 'offset': offset}, url:"<g:createLink controller='photo'
            action='searchForPhotosMultiple' />",success:function(data,textStatus){updateResultspm(data);},error:function(XMLHttpRequest,textStatus,errorThrown){console.log(errorThrown)}});
    }

    function updateResultspm(data) {
        var element = jQuery('#resultspm');
    	element.html(data);
    }

    function selectOrDeselectPhoto(checkbox, id) {
        if (checkbox.checked) {
            addPhotoCallback(id);
        } else {
            removePhotoCallback(id);
        }
    }

    </script>
    <div id="dialog-form-photo-multiple" title="Search for Photos" style="display: none">
	    <form>
	        <fieldset>
	            <label for="keywords">Keywords</label>
	            <input type="text" name="keywordspm" id="keywordspm" value="" class="text ui-widget-content ui-corner-all" />
	            <img src="<g:createLinkTo dir='/images' file='spinner.gif'/>" id="spinnerpm" style="display: none"/>
	        </fieldset>
	    </form>
        <div id="resultspm"></div>
    </div>
