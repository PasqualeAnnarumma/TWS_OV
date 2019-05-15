<%@page import="model.WebUser"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Cliente"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="model.ClienteModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	String URL = (String) request.getServletContext().getInitParameter("URL");
	@SuppressWarnings ("unchecked")
	ArrayList<Cliente> clienti = (ArrayList<Cliente>) request.getAttribute("clienti");
	if (clienti == null) {
		response.sendRedirect(response.encodeURL(URL + "admin/utentiUser"));
		return;
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta charset="ISO-8859-1">
		<title>Admin - aggiungi utente</title>
		<link type="text/css" rel="stylesheet" href="../css/tabella.css">
	</head>
	
	<body>
		<header>
			<jsp:include page="../header.jsp"></jsp:include>
		</header>
		<table class="container">
			<tr>
				<th>CF</th>
				<th>Nome</th>
				<th>Cognome</th>
				<th>Nato a</th>
				<th>Nato il</th>
				<th>Telefono</th>
				<th>Username</th>
				<th>Action</th>
			</tr>
			
			<%  int i = 0;
				String classe = "pari";
				for (Cliente cl : clienti) { 
					if (i % 2 == 0) classe = "pari";
					else classe = "dispari";
					i++;
			%>
					<tr>
						<td class="<%=classe%>"><%=cl.getCF()%></td>
						<td class="<%=classe%>"><%=cl.getNome()%></td>
						<td class="<%=classe%>"><%=cl.getCognome()%></td>
						<td class="<%=classe%>"><%=cl.getLuogoNascita()%></td>
						<td class="<%=classe%>"><%=cl.getDataNascita()%></td>
						<td class="<%=classe%>"><%=cl.getTelefono()%></td>
						<td class="<%=classe%>"><%=cl.getUsername()%></td>
						<td class="<%=classe%>">Elimina</td>
					</tr>
			<% } %>
		</table>
		
		<h3>Registrazione nuovo utente</h3>
		<fieldset>
			<legend>Aggiungi</legend>
			<form method="post" action="../register">
				CF: <input type="text" name="CF"><br>
				Nome: <input type="text" name="Nome"><br>
				Cognome: <input type="text" name="Cognome"><br>
				Nato a: <input type="text" name="Nato"><br>
				Nato il: <input type="text" name="Compleanno"><br>
				Telefono: <input type="text" name="Telefono"><br>
				Username: <input type="text" name="Username"><br>
				Passwrod: <input type="password" name="Password"><br>
				<input type="submit" value="registra">
			</form>
		</fieldset>
		
		
		<p><a href="<%=response.encodeURL(URL + "Servlet?action=logout")%>">Logout</a></p>
	</body>
</html>