<%@page import="model.WebUser"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	WebUser utente = (WebUser) request.getSession().getAttribute("utente");
	String URL = (String) request.getServletContext().getInitParameter("URL");
	String active = request.getServletPath().substring(1);
	String classe = "active";
	int sizeMenu = 4;
	int sizeAdminMenu = 1;
	String[] menu = { "Home", "Cerca", "Area utente", "Logout"};
	String[] adminMenu = {"Area admin"};
	String[] percorsi = {"home", "Search", "utente", "login?action=logout"};
	String[] percorsiAdmin = {"admin"};
%>

<!DOCTYPE html>
<html>

	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" href=<%= URL + "css/style.css" %>>
	</head>
	
	<header class="head">
        <div class="menu-toggle" id="hamburger">
            <i class="fas fa-bars"></i>
        </div>
        <div class="overlay"></div>
        <div class="container-nav">
            <nav class="nav">
                <h1 class="brand"><a href="<%= request.getContextPath() + "/home" %>">Take<span class="span">Your</span>Car</a></h1>
                <ul class="lista">
                    <li class="item"><a href="#">Cerca</a></li>
                    <li class="item"><a href="<%= request.getContextPath() + "/admin/utente"%>">Area utente</a></li>
                    <% if (utente != null && utente.isAdmin()) { %>
                    <li class="item"><a href="<%= request.getContextPath() + "/admin"%>">Area admin</a></li>
                    <% } %>
                    <li class="item"><a href=<%= request.getContextPath() + "/login?action=logout"%>>Logout</a></li>
                </ul>
            </nav>
        </div>
	</header>
</html>