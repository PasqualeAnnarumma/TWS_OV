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
		response.sendRedirect(response.encodeURL("veicoli"));
		return;
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Admin - Auto</title>
		<link type="text/css" rel="stylesheet" href="css/tabella.css">
	</head>
	
	<body>
		<header>
			<jsp:include page="../header.html"></jsp:include>
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
						<td class="<%=classe%>"><a href="<%=response.encodeURL(URL + "veicolo?Targa=" + v.getTarga())%>">Modifica</a></td>
						<td class="<%=classe%>"><a href="<%=response.encodeURL(URL + "veicolo?action=deleteVeicolo&Targa=" + v.getTarga())%>">Delete</a></td>
					</tr>
			<% } %>
		</table>
		
		<h3>Aggiungi veicolo</h3>
		
		<form action="<%=response.encodeURL("veicoli")%>" method="post">
			<input type="hidden" name="action" value="carica">
			Targa: <input type="text" name="Targa"><br>
			Modello : <input type="text" name="modello"><br>
			Colore : <input type="text" name="colore"><br>
			Deposito : <input type="text" name="deposito"><br>
			Marca : <input type="text" name="marca"><br>
			Copertina : <input type="text" name="copertina"><br>
			<input type="submit" value="carica">
		</form>
		
		<p><a href="<%=response.encodeURL(URL + "Servlet?action=logout")%>">Logout</a></p>
	</body>
</html>