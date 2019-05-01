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
	String[] percorsi = {"home.jsp", "Search", "user/user.jsp", "Servlet?action=logout"};
	String[] percorsiAdmin = {"admin/admin.jsp"};
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>TakeYourCar - Homepage</title>
		<link type="text/css" rel="stylesheet" href=<%= URL + "css/header.css"%>>
	</head>
	<body>
		<% if (utente != null) { %>
			<div class="header">
				<ul>
		<% for (int i = 0; i < sizeMenu-1; i++) {
				if (active.equals(percorsi[i])) { %>
					<li class="active"><a href="<%=URL + percorsi[i]%>"><%=menu[i]%></a></li>
		<% 		} else {%>
					<li><a href="<%=URL + percorsi[i]%>"><%=menu[i]%></a></li>
		<% 		} %>
		<% 	}
		   if (utente.isAdmin()) {
		   for (int i = 0; i < sizeAdminMenu; i++) {
		   		if (active.equals(percorsiAdmin[i])) { %>
					<li class="active"><a href="<%=URL + percorsiAdmin[i]%>"><%=adminMenu[i]%></a></li>
		<% 		} else {%>
					<li><a href="<%=URL + percorsiAdmin[i]%>"><%=adminMenu[i]%></a></li>
		<% 		} %>
		 <% } 
		   }%>
					<li><a href="<%=URL + percorsi[sizeMenu-1]%>"><%=menu[sizeMenu-1]%></a></li>
				</ul>
			</div>
		<%  } %>
	</body>
</html>