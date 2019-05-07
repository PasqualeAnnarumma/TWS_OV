<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta charset="ISO-8859-1">
		<title>Admin - marca</title>
	</head>
	
	<body>
		<header>
			<jsp:include page="/header.jsp"></jsp:include>
		</header>
		<form action="<%=response.encodeURL("upload")%>" method="post" enctype="multipart/form-data">
			<input type="hidden" name="tabella" value="marca">
			Nome: <input type="text" name="Nome"><br>
			<input type="file" name="file" accept="image/*" required multiple><br>
			<input type="submit" value="carica">
		</form>
	</body>
</html>