<%@page import="model.Immagine"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Veicolo"%>
<%@page import="model.DbConnectionModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	String URL = (String) request.getServletContext().getInitParameter("URL");
	String targa = request.getParameter("Targa");
	if (targa == null) targa = "";
	DbConnectionModel model = new DbConnectionModel(request.getServletContext().getInitParameter("KEY"));
	Veicolo veicolo = model.selezionaVeicoloPerTarga(targa);
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Admin - veicolo</title>
		<link type="text/css" rel="stylesheet" href="../css/tabella.css">
		<link type="text/css" rel="stylesheet" href="../css/auto.css">
	</head>
	
	<body>
		<header>
			<jsp:include page="../header.jsp"></jsp:include>
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
			
			<% ArrayList<Immagine> immagini = new ArrayList<Immagine>();
			   immagini = model.ricercaImmaginiPerTarga(veicolo.getTarga());
			   int i = 0;
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
						<td class="<%=classe%>"><a href="<%=response.encodeURL(URL + "Servlet?action=delete&ID=" + imm.getID() + "&Targa=" + imm.getTarga())%>">Elimina</a></td>
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
			<input type="hidden" name="action" value="upload">
			<input type="hidden" name="Targa" value="<%=veicolo.getTarga()%>">
			<input type="file" name="file" accept="image/*" required multiple>
			<input type="submit" value="carica">
		</form>
	</body>
</html>