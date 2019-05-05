<%@page import="model.VeicoloModel"%>
<%@page import="model.Veicolo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	String URL = (String) request.getServletContext().getInitParameter("URL");
	@SuppressWarnings ("unchecked")
	ArrayList<Veicolo> veicoli = (ArrayList<Veicolo>) request.getAttribute("veicoli");
	if (veicoli == null) {
		RequestDispatcher disp = request.getRequestDispatcher(response.encodeURL("../Servlet?redirect=admin/veicoli.jsp"));
		disp.forward(request, response);
		return;
	}
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
			
			<% int i = 0;
			   String classe = "pari";
			   for (Veicolo v : veicoli) { 
			   		if (i % 2 == 0) classe = "pari";
					else classe = "dispari";
					i++;
			%>
					<tr>
						<td class="<%=classe%>"><%=v.getTarga()%></td>
						<td class="<%=classe%>"><%=v.getModello()%></td>
						<td class="<%=classe%>"><%=v.getColore()%></td>
						<td class="<%=classe%>"><%=v.getDeposito()%></td>
						<td class="<%=classe%>"><a href="<%=response.encodeURL(URL + "admin/veicolo.jsp?Targa=" + v.getTarga())%>">Modifica</a></td>
					</tr>
			<% } %>
		</table>
		
		<p><a href="<%=response.encodeURL(URL + "Servlet?action=logout")%>">Logout</a></p>
	</body>
</html>