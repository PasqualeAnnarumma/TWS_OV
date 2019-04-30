<%@page import="model.Cliente"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="model.DbConnectionModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	String URL = (String) request.getServletContext().getInitParameter("URL");
	DbConnectionModel<Cliente> model = new DbConnectionModel<Cliente>(request.getServletContext().getInitParameter("KEY"));
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Admin - aggiungi utente</title>
	</head>
	
	<body>
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
			
			<% ResultSet result = model.ricercaTuttiClienti();
				while (result.next()) { %>
					<tr>
						<td><%=result.getString("CF")%></td>
						<td><%=result.getString("Nome")%></td>
						<td><%=result.getString("Cognome")%></td>
						<td><%=result.getString("LuogoNascita")%></td>
						<td><%=result.getString("dataNascita")%></td>
						<td><%=result.getString("Telefono")%></td>
						<td><%=result.getString("username")%></td>
						<td>Elimina</td>
					</tr>
			<% } %>
		</table>
		
		<p><a href="<%=response.encodeURL(URL + "Servlet?action=logout")%>">Logout</a></p>
	</body>
</html>