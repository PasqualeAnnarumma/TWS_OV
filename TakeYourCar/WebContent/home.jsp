<%@page import="model.VeicoloModel"%>
<%@page import="model.Immagine"%>
<%@page import="model.Veicolo"%>
<%@page import="model.ClienteModel"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.WebUser"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	WebUser utente = (WebUser) request.getSession().getAttribute("utente");
	String URL = (String) request.getServletContext().getInitParameter("URL");
	@SuppressWarnings ("unchecked")
	ArrayList<Veicolo> veicoli = (ArrayList<Veicolo>) request.getAttribute("veicoli");
	if (veicoli == null) {
		response.sendRedirect(response.encodeURL("home"));
		return;
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta charset="ISO-8859-1">
		<title>TakeYourCar - Homepage</title>
		<!-- <link type="text/css" rel="stylesheet" href="css/card.css"> -->
		<link type="text/css" rel="stylesheet" href="css/card2.css">
	</head>
	<body>
		<header>
			<jsp:include page="header.jsp"></jsp:include>
		</header>
		
		<div class="corpo">
			<% for (Veicolo v : veicoli) { %>
				<div class="container">
					<div class="box">
						<div class="content">
							<img class="img" src="img?Copertina=<%=v.getTarga()%>">
							<br>Colore: <%= v.getColore() %><br>
							<p class="addCart">Aggiungi al carrello</p><br>
							<p class="prezzo">EURO <%=v.getPrezzo()%></p>
						</div>
						<div class="info">
							<div class="img">
								<img src="img?Marca=<%=v.getMarca()%>">
							</div>
							<h3><%= v.getModello() %></h3>
						</div>
					</div>
				</div>
			<% } %>
		</div>
				
		<!-- <div class="container">
			<div class="card">
				<div class="card-head">
					<img class="img-prodotto" src="img/PANDA.jpg">
				</div>
				<div class="corpo-carta">
					<div class="titolo">
						Panda
					</div>
					<div class="descrizione">
						Macchina piccola ed elegante
					</div>
					<div class="prezzo">
						EURO <b>100</b>
					</div>
				</div>
			</div>
		</div>
		
		<div class="container">
			<div class="card">
				<div class="card-head">
					<img class="img-prodotto" src="img/JUKE.jpg">
				</div>
				<div class="corpo-carta">
					<div class="titolo">
						Juke
					</div>
					<div class="descrizione">
						Macchina vera
					</div>
					<div class="prezzo">
						EURO <b>100</b>
					</div>
				</div>
			</div>
		</div> -->
				
	</body>
</html>