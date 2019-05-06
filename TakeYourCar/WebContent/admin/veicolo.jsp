<%@page import="model.Immagine"%>
<%@page import="model.ImmagineModel"%>
<%@page import="model.VeicoloModel"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Veicolo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	String URL = (String) request.getServletContext().getInitParameter("URL");
	String targa = request.getParameter("Targa");
	if (targa == null) {
		response.sendRedirect(response.encodeURL("veicoli"));
		return;
	}
	
	@SuppressWarnings ("unchecked")
	ArrayList<Immagine> immagini = (ArrayList<Immagine>) request.getAttribute("immagini");
	Veicolo veicolo = (Veicolo) request.getAttribute("veicolo");
	if (veicolo == null || immagini == null) {
		response.sendRedirect(response.encodeURL("veicolo"));
		return;
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Admin - veicolo</title>
		<link type="text/css" rel="stylesheet" href="css/tabella.css">
		<link type="text/css" rel="stylesheet" href="css/auto.css">
	</head>
	
	<body>
		<header>
			<jsp:include page="../header.html"></jsp:include>
		</header>
		<br>
		<h1>Veicolo: <%=veicolo.getModello()%></h1>
		<h3>Targa: <%=veicolo.getTarga()%> Colore: <%=veicolo.getColore()%> Deposito: <%=veicolo.getDeposito()%></h3>
		
		<table class="carTable">
			<tr>
				<th>ID</th>
				<th>Targa</th>
				<th>Foto</th>
				<th>Action</th>
			</tr>
			
			<% int i = 0;
			   String classe = "pari";
			   for (Immagine imm : immagini) {
			   		if (i % 2 == 0) classe = "pari";
					else classe = "dispari";
					i++;
			%>
					<tr>
						<td class="<%=classe%>"><%=imm.getID()%></td>
						<td class="<%=classe%>"><%=imm.getTarga()%></td>
						<td class="<%=classe%>"><img class="auto" src="<%=response.encodeURL(URL + "img?ID=" + imm.getID())%>"></td>
						<td class="<%=classe%>">
							<a href="<%=response.encodeURL(URL + "veicolo?action=delete&ID=" + imm.getID() + "&Targa=" + imm.getTarga())%>">Elimina</a>
							<a href="<%=response.encodeURL(URL + "veicolo?action=copertina&Targa=" + veicolo.getTarga() + "&ID=" + imm.getID())%>">Imposta copertina</a>
						</td>
					</tr>
			<%
				} if (immagini.size() == 0) {%>
					<tr>
						<td class="<%=classe%>" colspan="4">Non ci sono foto</td>
					</tr>
			<% } %>
		</table>
				
		<br>
		<form action="<%=response.encodeURL(URL + "upload")%>" method="post" enctype="multipart/form-data">
			<input type="hidden" name="tabella" value="immagini">
			<input type="hidden" name="Targa" value="<%=veicolo.getTarga()%>">
			<input type="file" name="file" accept="image/*" required multiple>
			<input type="submit" value="carica">
		</form>
	</body>
</html>