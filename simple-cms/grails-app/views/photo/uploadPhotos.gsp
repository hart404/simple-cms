<html>
<head>
<title>Photo Upload</title>
<meta name="layout" content="generatedLayout" />
<head>
	<g:javascript src="ajax-uploader/application.js"/>
	<g:javascript src="ajax-uploader/fileuploader.js"/>
	<link rel="stylesheet" "type="text/css" href="${resource(dir:'css', file: 'ajax-uploader/uploader.css', plugin='simple-cms')}"/>
</head>
<body>
	<h1>Photo Upload</h1>
	<p>Please select the photos you wish to upload. Please ensure that the photos contain all the necessary meta information (photographers,
		keywords, etc).</p>
	<uploader:uploader id="mscUploader" url="${[controller:'photo', action:'upload']}" buttonText="Upload Images" sizeLimit="${8 * 1024 * 1024}" allowedExtensions="[ /'jpg'/, /'jpeg'/ ]" />
</body>
</html>