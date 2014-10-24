	<div id="dialog-form-html" title="Update Text" style="display: none">
    <img src="<g:createLinkTo dir='/images' file='spinner.gif'/>" id="spinner"/>
    <div id='editor'></div>
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

    var editor, html = '';
    var htmlWidgetId = '';
    var cssWidgetId = '';
    var callbackHTML;

    $(function() {
        
        $("#dialog-form-html").dialog({
            autoOpen: false,
            width: 800,
            height: 800,
            modal: true,
            buttons: {
                "Save": function() {
                    html = editor.getData();
                    var element = $('#' + cssWidgetId);
                	element.html(html);
                    updateHtmlText(html, htmlWidgetId);
                    editor.destroy();
                    editor = null;
                    $(this).dialog("close");
                },
                "Cancel": function() {
                    $(this).dialog("close");
                }
            },
            close: function() {
                editor.destroy();
                editor = null;
            },
            resizeStop: function(event, ui) {resizeEditor($("#dialog-form-html").dialog("option", "height"), $("#dialog-form-html").dialog("option", "width"))}
        });

    });

    function updateHtmlText(html, id) {
    	callbackHTML(html, id);
    }

    function resizeEditor(width, height) {
        CKEDITOR.instances.editor1.resize(width, height - 380);
    }

    function createEditor(widgetId, id, callback) {
    	callbackHTML = callback;
    	htmlWidgetId = id;
    	cssWidgetId = widgetId;
    	var html = $('#' + widgetId).html();
    	var config = {};
     	CKEDITOR.config.resize_enabled = false;
    	CKEDITOR.config.width = 760;
    	CKEDITOR.config.height = 500;
    	CKEDITOR.config.resize_dir = 'both';
    	CKEDITOR.config.baseFloatZIndex = 9000;
    	editor = CKEDITOR.appendTo('editor', config, html);
    	$("#dialog-form-html").dialog("open");
        return false;
    }

    function updateHtmlWidget(html, id) {
        jQuery.ajax({type:'POST', data:{'htmlText': html, 'id': id}, url:"<g:createLink controller='widget'
            action='updateHtmlWidget' />",success:function(data,textStatus){},error:function(XMLHttpRequest,textStatus,errorThrown){console.log(errorThrown)}});
    }

    function updatePhotoWidgetCaption(html, id) {
        jQuery.ajax({type:'POST', data:{'caption': html, 'id': id}, url:"<g:createLink controller='widget'
            action='updatePhotoWidgetCaption' />",success:function(data,textStatus){},error:function(XMLHttpRequest,textStatus,errorThrown){console.log(errorThrown)}});
    }

    function createEditorForHTMLWidget(widgetId, id) {
        createEditor(widgetId, id, updateHtmlWidget);
    }

    function createEditorForPhotoCaption(widgetId, id) {
        createEditor(widgetId, id, updatePhotoWidgetCaption);
    }
    </script>
</div>
