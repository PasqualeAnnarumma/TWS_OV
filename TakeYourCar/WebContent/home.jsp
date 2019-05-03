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
		<title>TakeYourCar - Homepage</title>
	</head>
	<body>
		<header>
			<jsp:include page="header.jsp"></jsp:include>
		</header>
		<h1>Welome on TakeYourCar!</h1>
		<% if (utente.isAdmin()) { %>
			<p>Logged like admin</p>
		<% } else {%>
			<p>Logged like user</p>
		<% } %>
	</body>
</html>