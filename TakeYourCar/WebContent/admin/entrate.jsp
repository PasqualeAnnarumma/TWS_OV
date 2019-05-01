<%@page import="model.Cliente"%>
<%@page import="model.DbConnectionModel"%>
<%@page import="java.sql.ResultSet"%>
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
		<title>Admin - Entrate</title>
		<link type="text/css" rel="stylesheet" href="../css/tabella.css">
	</head>
	
	<body>
		<header>
			<jsp:include page="../header.jsp"></jsp:include>
		</header>
		<table>
			<tr>
				<th>Data</th>
				<th>Importo</th>
				<th>metodo</th>
				<th>Cliente</th>
				<th>Action</th>
			</tr>
			
			<% ResultSet result = model.ricercaTuttiPagamenti();
			   int i = 0;
			   String classe = "pari";
			   while (result.next()) { 
			   	if (i % 2 == 0) classe = "pari";
				else classe = "dispari";
				i++;
			%>
					<tr>
						<td class="<%=classe%>"><%=result.getString("Data_p")%></td>
						<td class="<%=classe%>"><%=result.getString("Importo")%></td>
						<td class="<%=classe%>"><%=result.getString("MetodoDiPagamento")%></td>
						<td class="<%=classe%>"><%=result.getString("Cliente")%></td>
						<td class="<%=classe%>">Elimina</td>
					</tr>
			<% } %>
		</table>
		
		<p><a href="<%=response.encodeURL(URL + "Servlet?action=logout")%>">Logout</a></p>
	</body>
</html>