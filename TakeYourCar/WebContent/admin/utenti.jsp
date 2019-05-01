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
			
			<% ResultSet result = model.ricercaTuttiClienti();
				int i = 0;
				String classe = "pari";
				while (result.next()) { 
					if (i % 2 == 0) classe = "pari";
					else classe = "dispari";
					i++;
			%>
					<tr>
						<td class="<%=classe%>"><%=result.getString("CF")%></td>
						<td class="<%=classe%>"><%=result.getString("Nome")%></td>
						<td class="<%=classe%>"><%=result.getString("Cognome")%></td>
						<td class="<%=classe%>"><%=result.getString("LuogoNascita")%></td>
						<td class="<%=classe%>"><%=result.getString("dataNascita")%></td>
						<td class="<%=classe%>"><%=result.getString("Telefono")%></td>
						<td class="<%=classe%>"><%=result.getString("username")%></td>
						<td class="<%=classe%>">Elimina</td>
					</tr>
			<% } %>
		</table>
		
		<p><a href="<%=response.encodeURL(URL + "Servlet?action=logout")%>">Logout</a></p>
	</body>
</html>