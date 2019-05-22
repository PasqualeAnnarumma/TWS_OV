<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Registration</title>
	</head>
	
	<body>
		<h1>Registration</h1>
		<form action="register" method="post">
			Nome: <input type="text" name="nome"><br>
			Cognome: <input type="text" name="cognome"><br>
			CF: <input type="text" name="cf"><br>
			Luogo nascita: <input type="text" name="luogo_nascita"><br>
			Data nasctia: <input type="text" name="data_nascita"><br>
			Telefono: <input type="number" name="telefono"><br>
			N° carta di credito: <input type="number" name="carta"><br>
			Username: <input type="text" name="username"><br>
			Password: <input type="password" name="password"><br>
			Confirm Password: <input type="password" name="c_password"><br>
			E-mail: <input type="email" name="email"><br>
			Confirm E-mail: <input type="email" name="c_email"><br>
			<input type="submit" value="registrati!">
		</form>
	</body>
</html>