<!doctype html>
<html>
	<head>
		<title>Page Not Found or other error</title>
		<meta name="layout" content="mainLayout">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'errors.css')}" type="text/css">
	</head>
	<body>
	   <h1>Apologies!</h1>
	   <p>Our lovely web site has obviously done something really goofy. We've told our support staff about 
	   this issue and, hopefully, they'll get their act together and fix the problem. Once again, our apologies 
	   for the inconvenience.</p>
		<g:renderException exception="${exception}" />
	</body>
</html>