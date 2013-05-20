<html>
<head>
<title>Document Upload</title>
<meta name="layout" content="generatedLayout" />
<head>
	<g:javascript src="ajax-uploader/application.js"/>
	<g:javascript src="ajax-uploader/fileuploader.js"/>
	<link rel="stylesheet" "type="text/css" href="${resource(dir:'css', file: 'ajax-uploader/uploader.css', plugin='simple-cms')}"/>
</head>
<body>
	<div class="scmsHtmlContainer">
		<h1>Document Upload</h1>
		<p>Please select the documents you wish to upload. Allowed file extensions are .PDF, .DOC, .DOCX, .PPT, .PPTX, .XLS and .XLSX. There is a limit of 20Mb on the individual file size.</p>
		<uploader:uploader id="mscUploader" url="${[controller:'document', action:'upload']}" buttonText="Upload Documents" sizeLimit="${20 * 1024 * 1024}" allowedExtensions="[ /'pdf'/, /'doc'/, /'docx'/, /'xls'/, /'xlsx'/, /'ppt'/, /'pptx'/, /'kml'/ , /'kmz'/  ]" />
	</div>
</body>
</html>