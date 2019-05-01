<%@page import="model.WebUser"%>
<%@page import="model.Cliente"%>
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
		<title>Admin page</title>
	</head>
	
	<body>
		<header>
			<jsp:include page="../header.jsp"></jsp:include>
		</header>
		<h1>Benvenuto admin <%=utente.getNome()%> <%=utente.getCognome()%></h1>
		<ul>
			<li><a href="<%=response.encodeURL(URL + "admin/utenti.jsp")%>">Gestisci utenti</a></li>
			<li><a href="<%=response.encodeURL(URL + "admin/veicoli.jsp")%>">Gestisci veicoli</a></li>
			<li><a href="<%=response.encodeURL(URL + "admin/entrate.jsp")%>">Visualizza entrate</a></li>	
		</ul>
		<p><a href="<%=response.encodeURL(URL + "Servlet?action=logout")%>">Logout</a></p>
	</body>
</html>