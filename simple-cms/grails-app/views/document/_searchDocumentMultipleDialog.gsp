    <script>
    $('#spinnerdm').hide();
    jQuery.ajaxSetup({
        beforeSend: function() {
            var element = $('#spinnerdm');
            element.css('visibility', 'visible');
        },
        complete: function(){
            var element = $('#spinnerdm');
            element.css('visibility', 'hidden');
        },
        success: function() {}
    });

    $(function() {       
        $("#dialog-form-document-multiple").dialog({
            autoOpen: false,
            height: 850,
            width: 600,
            modal: true,
            buttons: {
                "Search": function() {
                    documentSearchForKeywordsMultiple($("#keywordsdm").val(), 0);
                },
                "Select": function() {
                	$(this).dialog("close");
                	selectDocumentsCallback();
                },
                "Cancel": function() {
                    $(this).dialog("close");
                }
            },
            close: function() {
            }
        });

    });

    var addDocumentCallback, removeDocumentCallback, selectDocumentsCallback;

    function openDocumentSelectMultipleDialog(addFunction, removeFunction, selectFunction) {
        addDocumentCallback = addFunction;
        removeDocumentCallback = removeFunction;
        selectDocumentsCallback = selectFunction;
        $("#resultsdm").html("");
        $("#dialog-form-document-multiple").dialog("open");
    }

    function documentSearchForKeywordsMultiple(keywords, offset) {
        jQuery.ajax({type:'POST', data:{'keywords': keywords, 'offset': offset}, url:"<g:createLink controller='document'
            action='searchForDocumentsMultiple' />",success:function(data,textStatus){updateResultsdm(data);},error:function(XMLHttpRequest,textStatus,errorThrown){console.log(errorThrown)}});
    }

    function updateResultsdm(data) {
        var element = jQuery('#resultsdm');
    	element.html(data);
    }

    function selectOrDeselectDocument(checkbox, id) {
        if (checkbox.checked) {
            addDocumentCallback(id);
        } else {
            removeDocumentCallback(id);
        }
    }

    </script>
    <div id="dialog-form-document-multiple" title="Search for Documents" style="display: none">
	    <form>
	        <fieldset>
	            <label for="keywords">Keywords</label>
	            <input type="text" name="keywordsdm" id="keywordsdm" value="" class="text ui-widget-content ui-corner-all" />
	            <img src="<g:createLinkTo dir='/images' file='spinner.gif'/>" id="spinnerdm" style="display: none"/>
	        </fieldset>
	    </form>
        <div id="resultsdm"></div>
    </div>
