<%@page import="java.sql.ResultSet"%>
<%@page import="model.Cliente"%>
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
		<title>Admin - Auto</title>
		<link type="text/css" rel="stylesheet" href="../css/tabella.css">
	</head>
	
	<body>
		<header>
			<jsp:include page="../header.jsp"></jsp:include>
		</header>
		<table>
			<tr>
				<th>Targa</th>
				<th>Modello</th>
				<th>Colore</th>
				<th>Deposito</th>
				<th>Action</th>
			</tr>
			
			<% ResultSet result = model.ricercaTuttiVeicoli();
			   int i = 0;
			   String classe = "pari";
			   while (result.next()) { 
			   		if (i % 2 == 0) classe = "pari";
					else classe = "dispari";
					i++;
			%>
					<tr>
						<td class="<%=classe%>"><%=result.getString("Targa")%></td>
						<td class="<%=classe%>"><%=result.getString("Modello")%></td>
						<td class="<%=classe%>"><%=result.getString("Colore")%></td>
						<td class="<%=classe%>"><%=result.getString("ContenutoIn")%></td>
						<td class="<%=classe%>">Elimina</td>
					</tr>
			<% } %>
		</table>
		
		<p><a href="<%=response.encodeURL(URL + "Servlet?action=logout")%>">Logout</a></p>
	</body>
</html>