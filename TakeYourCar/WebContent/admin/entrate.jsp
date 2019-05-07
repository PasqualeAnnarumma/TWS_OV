<%@page import="java.util.ArrayList"%>
<%@page import="model.Entrate"%>
<%@page import="model.EntrateModel"%>
<%@page import="model.Cliente"%>
<%@page import="model.ClienteModel"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	String URL = (String) request.getServletContext().getInitParameter("URL");
	@SuppressWarnings ("unchecked")
	ArrayList<Entrate> entrate = (ArrayList<Entrate>) request.getAttribute("entrate");
	if (entrate == null)
		response.sendRedirect(response.encodeURL("entrate"));
%>
<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta charset="ISO-8859-1">
		<title>Admin - Entrate</title>
		<link type="text/css" rel="stylesheet" href="css/tabella.css">
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
			
			<% int i = 0;
			   String classe = "pari";
			   for (Entrate e : entrate) { 
			   	if (i % 2 == 0) classe = "pari";
				else classe = "dispari";
				i++;
			%>
					<tr>
						<td class="<%=classe%>"><%=e.getData()%></td>
						<td class="<%=classe%>"><%=e.getImporto()%></td>
						<td class="<%=classe%>"><%=e.getMetodo()%></td>
						<td class="<%=classe%>"><%=e.getCliente()%></td>
						<td class="<%=classe%>">Elimina</td>
					</tr>
			<% } %>
		</table>
		
		<p><a href="<%=response.encodeURL(URL + "Servlet?action=logout")%>">Logout</a></p>
	</body>
</html>