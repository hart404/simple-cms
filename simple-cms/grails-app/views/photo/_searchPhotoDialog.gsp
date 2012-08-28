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

        $("#search")
            .button()
            .click(function() {
            	$('#results').html('');
                $("#dialog-form").dialog("open");
                return false;
            });

        $("#dialog-form").keydown(function (event) {
            if (event.keyCode == 13) {
                $(this).parent()
                       .find("button:eq(0)").trigger("click");
            }
            return false;
        });
    });

    function searchForKeywords(keywords, offset) {
        jQuery.ajax({type:'POST', data:{'keywords': keywords, 'offset': offset}, url:"<g:createLink controller='photo'
            action='searchForPhotos' />",success:function(data,textStatus){jQuery('#results').html(data);},error:function(XMLHttpRequest,textStatus,errorThrown){console.log(errorThrown)}});
    }

    // Assumes the using GSP has photoId and photoFileName elements
    function selectPhoto(fileName, id) {
        $("#photoId").val(id);
        $("#photoFileName").text(fileName);
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
