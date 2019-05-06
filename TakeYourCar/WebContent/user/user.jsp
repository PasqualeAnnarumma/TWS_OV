<%@page import="model.WebUser"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	WebUser utente = (WebUser) request.getSession().getAttribute("utente");
	String URL = (String) request.getServletContext().getInitParameter("URL");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>User area</title>
	</head>
	
	<body>
		<header>
			<jsp:include page="../header.html"></jsp:include>
		</header>
		<h1 style="color:#fff;">Benvenuto <%=utente.getNome()%> <%=utente.getCognome()%></h1>
		<p style="color:#fff;"><a href="<%=response.encodeURL(URL + "login?action=logout")%>">Logout</a></p>
	</body>
</html>