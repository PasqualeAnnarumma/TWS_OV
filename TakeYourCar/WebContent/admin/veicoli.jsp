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
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta charset="ISO-8859-1">
		<title>Admin - Auto</title>
		<link type="text/css" rel="stylesheet" href="css/tabella.css">
		<link type="text/css" rel="stylesheet" href="css/form.css">
	</head>
	
	<body>
		<header>
			<jsp:include page="../header.jsp"></jsp:include>
		</header>
		<table class="container">
			<tr>
				<th>Targa</th>
				<th>Modello</th>
				<th>Colore</th>
				<th>Deposito</th>
				<th colspan="2">Action</th>
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
						<td class="<%=classe%>"><a href="<%=response.encodeURL(URL + "veicolo?action=delete&Targa=" + v.getTarga())%>">Delete</a></td>
					</tr>
			<% } %>
		</table>
		
		<h3>Aggiungi veicolo</h3>
		
		<div class="div-tabella">
			<form class="form" action="<%=response.encodeURL("veicoli")%>" method="post">
				<input type="hidden" name="action" value="carica">
				Targa: <input type="text" name="targa" required><br>
				Modello : <input type="text" name="modello" required><br>
				Colore : <input type="text" name="colore" required><br>
				Deposito : <input type="text" name="deposito" required><br>
				Marca : <input type="text" name="marca" required><br>
				Copertina : <input type="text" name="copertina" required><br>
				Prezzo : <input type="text" name="prezzo" required><br>
				<input type="submit" value="carica">
			</form>
		</div>
		
		<p><a href="<%=response.encodeURL(URL + "login?action=logout")%>">Logout</a></p>
	</body>
</html>