<%@page import="model.WebUser"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Cliente"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="model.ClienteModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	String URL = (String) request.getServletContext().getInitParameter("URL");
	ClienteModel model = new ClienteModel(request.getServletContext().getInitParameter("KEY"));
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Admin - aggiungi utente</title>
		<link type="text/css" rel="stylesheet" href="../css/tabella.css">
	</head>
	
	<body>
		<header>
			<jsp:include page="../header.jsp"></jsp:include>
		</header>
		<table>
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
			
			<% ArrayList<Cliente> clienti = model.selectAll();
				int i = 0;
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
		
		<p><a href="<%=response.encodeURL(URL + "Servlet?action=logout")%>">Logout</a></p>
	</body>
</html>