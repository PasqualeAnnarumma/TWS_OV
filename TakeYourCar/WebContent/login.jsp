<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	String error = (String) request.getAttribute("error");
	String URL = (String) request.getServletContext().getInitParameter("URL");
	String classe = "container";
	String success = (String) request.getSession().getAttribute("success");
	if (error != null) classe = "shake";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
		<meta charset="ISO-8859-1">
		<title>TakeYourCar</title>
		<link type="text/css" rel="stylesheet" href="css/login.css">
	</head>
	
	<body>
		
		<div class="<%=classe%>">
		
			<div class="titolo">Welcome on <div id="take">TakeYourCar</div> !</div> 
			
			<form action="<%=response.encodeURL("login")%>" method="post">
				<div class="box">
					<input type="text" name="username" placeholder="username"><br>
				</div>
				
				<div class="box">
					<input type="password" name="password" placeholder="password"><br>
				</div>
				
				<div class="radio">
					<input type="radio" name="ruolo" value="admin" required> Admin
					<input type="radio" name="ruolo" value="utente" required> Utente
				</div>
				
				<input class="btn" type="submit" value="login">
				
			</form>
			
			<a href="" class="b1">Password dimenticata?</a>
			<a href="register" class="b2">Crea un account!</a>
			<%if (error != null) { %>
					<p class="error"><%=error%></p>
				<%request.getSession().removeAttribute("error"); %>
			<%} else if (success != null) { %>
				<p class="success"><%=success%></p>
				<%request.getSession().removeAttribute("success"); %>
			<%} %>
		</div>
	</body>
</html>