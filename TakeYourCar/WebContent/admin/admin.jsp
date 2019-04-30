<%@page import="model.WebAdmin"%>
<%@page import="model.Cliente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	WebAdmin admin = (WebAdmin) request.getSession().getAttribute("admin");
	String URL = (String) request.getServletContext().getInitParameter("URL");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Admin page</title>
	</head>
	
	<body>
		<h1>Benvenuto admin <%=admin.getNome()%> <%=admin.getCognome()%></h1>
		<ul>
			<li><a href="<%=response.encodeURL(URL + "admin/add_user.jsp")%>">Aggiungi utente</a></li>
			<li>Elimina utente</li>
			<li>Modifica utente</li>
			<li>Aggiungi auto</li>
			<li>Rimuovi auto</li>
			<li>Modifica auto</li>
			<li>Visualizza entrate</li>	
		</ul>
		<p><a href="<%=response.encodeURL(URL + "Servlet?action=logout")%>">Logout</a></p>
	</body>
</html>