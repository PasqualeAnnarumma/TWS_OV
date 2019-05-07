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
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta charset="ISO-8859-1">
		<title>Admin page</title>
	</head>
	
	<body>
		<header>
			<jsp:include page="../header.jsp"></jsp:include>
		</header>
		<h1 style="color:#fff;">Benvenuto admin <%=utente.getNome()%> <%=utente.getCognome()%></h1>
		<ul>
			<li><a href="<%=response.encodeURL("utentiAdmin")%>">Gestisci utenti</a></li>
			<li><a href="<%=response.encodeURL("veicoli")%>">Gestisci veicoli</a></li>
			<li><a href="<%=response.encodeURL("entrate")%>">Visualizza entrate</a></li>
			<li><a href="<%=response.encodeURL("marca")%>">Gestisci loghi</a></li>	
		</ul>
		<p><a href="<%=response.encodeURL(URL + "login?action=logout")%>">Logout</a></p>
	</body>
</html>