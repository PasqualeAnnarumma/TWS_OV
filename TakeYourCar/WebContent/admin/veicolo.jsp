<%@page import="model.Immagine"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Veicolo"%>
<%@page import="model.Cliente"%>
<%@page import="model.DbConnectionModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	String URL = (String) request.getServletContext().getInitParameter("URL");
	DbConnectionModel model = new DbConnectionModel(request.getServletContext().getInitParameter("KEY"));
	String targa = request.getParameter("Targa");
	if (targa == null) targa = "";
	Veicolo veicolo = model.selezionaVeicoloPerTarga(targa);
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Admin - veicolo</title>
		<link type="text/css" rel="stylesheet" href="../css/tabella.css">
	</head>
	
	<body>
		<header>
			<jsp:include page="../header.jsp"></jsp:include>
		</header>
		<br>
		<h1>Veicolo: <%=veicolo.getModello()%></h1>
		<h3>Targa: <%=veicolo.getTarga()%> Colore: <%=veicolo.getColore()%> Deposito: <%=veicolo.getDeposito()%></h3>
		
		<table>
			<tr>
				<th>Targa</th>
				<th>Foto</th>
				<th>Action</th>
			</tr>
			
			<% ArrayList<Immagine> immagini = model.ricercaImmaginiPerTarga(targa);
			   int i = 0;
			   String classe = "pari";
			   for (Immagine imm : immagini) { 
			   		if (i % 2 == 0) classe = "pari";
					else classe = "dispari";
					i++;
			%>
					<tr>
						<td class="<%=classe%>"><%=imm.getTarga()%></td>
						<td class="<%=classe%>"><%=imm.getImmagine()%></td>
						<td class="<%=classe%>"><a href="">Elimina</a></td>
					</tr>
			<% } %>
		</table>
		
		<br>
		<form action="<%=response.encodeURL(URL + "upload")%>" method="post" enctype="multipart/form-data">
			<input type="hidden" name="action" value="upload">
			<input type="hidden" name="Targa" value="<%=veicolo.getTarga()%>">
			<input type="file" name="file" accept="image/*" multiple>
			<input type="submit" value="carica">
		</form>
	</body>
</html>