<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	String error = (String) request.getSession().getAttribute("error");
	String URL = (String) request.getServletContext().getInitParameter("URL");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>TakeYourCar</title>
	</head>
	
	<body>
		<header>
			<jsp:include page="/header.jsp"></jsp:include>
		</header>
		<h1>Welcome!</h1>
		<form action="<%=response.encodeURL("Servlet")%>" method="post">
			<label for="username">Username: </label> <input type="text" name="username" placeholder="username"><br>
			<label for="password">Password: </label> <input type="password" name="password" value="xxx"><br>
			<input type="radio" name="ruolo" value="admin" required> Admin 
			<input type="radio" name="ruolo" value="utente" required> Utente<br>
			<input type="submit" value="login">
			
			<%if (error != null) { %>
				<p style="color:red; font-weight: bold;"><%=error%></p>
			<%  request.getSession().removeAttribute("error");
			}%>
		</form>
	</body>
</html>