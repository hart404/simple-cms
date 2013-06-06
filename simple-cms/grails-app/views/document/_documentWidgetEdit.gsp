    <script>

    var documentWidgetDocumentIds = [];
    var documentWidgetId = '';
    var documentWidgetDocumentsHTMLId = '#documentWidgetDocuments';

    $(function() {        
        $("#dialog-form-document-widget").dialog({
            autoOpen: false,
            width: 800,
            height: 800,
            modal: true,
            buttons: {
                "Search": function() {
                	openDocumentSelectMultipleDialog(addDocumentWidgetDocumentId, removeDocumentWidgetDocumentId, updateDocumentWidgetDocumentIds);
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

    function updateDocumentWidgetTitle(title, documentWidgetId) {
        jQuery.ajax({type:'POST', data:{'title': title, 'id': documentWidgetId}, url:"<g:createLink controller='document'
            action='updateDocumentWidgetTitle' />",success:function(data,textStatus){},error:function(XMLHttpRequest,textStatus,errorThrown){console.log(errorThrown)}});    
    }

    function removeDocumentWidgetDocument(documentWidgetId, documentId) {
        jQuery.ajax({type:'POST', data:{'id': documentWidgetId, 'documentId': documentId}, url:"<g:createLink controller='document'
            action='deleteDocument' />", success:function(data,textStatus){jQuery('#documentWidgetDocuments').html(data);}, error:function(XMLHttpRequest,textStatus,errorThrown){console.log(errorThrown)}}); 
        getDocumentWidgetDocuments(documentWidgetId, 0, 5);   
    }
        
    function openDocumentWidgetUpdate(id) {
    	documentWidgetDocumentIds = [];
    	documentWidgetId = id;
    	getDocumentWidgetDocuments(id, 0, 5);
        $("#dialog-form-document-widget").dialog("open");
    }

    function getDocumentWidgetDocuments(id, offset, max) {
        jQuery.ajax({type:'GET', data:{'id': id, 'offset': offset, 'max': max}, url:"<g:createLink controller='document'
            action='documentWidgetDocuments' />",success:function(data,textStatus){jQuery('#documentWidgetDocuments').html(data);},error:function(XMLHttpRequest,textStatus,errorThrown){console.log(errorThrown)}});
    }

    function updateDocumentWidgetDocumentIds() {
    	jQuery.ajax({type:'POST', data:{'documentIds': documentWidgetDocumentIds, 'id': documentWidgetId}, url:"<g:createLink controller='document'
            action='updateDocumentWidgetDocumentIds' />",success:function(data,textStatus){jQuery('#documentWidgetDocuments').html(data);},error:function(XMLHttpRequest,textStatus,errorThrown){console.log(errorThrown)}});
    }

    function addDocumentWidgetDocumentId(id) {
        documentWidgetDocumentIds.push(id);
    }

    function removeDocumentWidgetDocumentId(id) {
        // Laziness because I can't be bothered figuring out how to remove an element from an array
        var newDocumentIds = [];
        for (var index in documentWidgetDocumentIds) {
            if (id != documentWidgetDocumentIds[index]) {
            	newDocumentIds.push(documentWidgetDocumentIds[index]);
            }
        }
        documentWidgetDocumentIds = newDocumentIds;
    }

    function editDocumentWidgetTitle(id, widgetId) {
    	createEditor(widgetId, id, updateDocumentWidgetTitle);
    }

    </script>
    <div id="dialog-form-document-widget" title="Document Update" style="display: none">
        <img src="<g:createLinkTo dir='/images' file='spinner.gif'/>" id="spinnerdm" style="display: none" />
        <div id="documentWidgetDocuments"></div>    
    </div>
    
