package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eccezioni.LoginException;
import model.AdminModel;
import model.ClienteModel;
import model.WebUser;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = (String) request.getParameter("username");
		String pass = (String) request.getParameter("password");
		String ruolo = (String) request.getParameter("ruolo");
		String action = request.getParameter("action");
				
		if (action != null && action.equals("logout")) {
			request.getSession().removeAttribute("cliente");
			request.getSession().removeAttribute("admin");
			request.getSession().removeAttribute("utente");
			request.getSession().invalidate();
			response.sendRedirect("login");
			return;
		} else if (username == null || pass == null || ruolo == null) {
			RequestDispatcher disp = request.getRequestDispatcher("login.jsp");
			disp.forward(request, response);
			return;
		} else if (ruolo.equals("admin")) {
			try {
				AdminModel model = new AdminModel(request.getServletContext().getInitParameter("KEY"));
				WebUser utente = model.selectByKey(username);
				request.getSession().setAttribute("utente", utente);
				response.sendRedirect("home");
				return;
			} catch (SQLException | LoginException ex) {
				request.setAttribute("error", ex.getMessage());
				RequestDispatcher disp = request.getRequestDispatcher("/login.jsp");
				disp.forward(request, response);
				System.err.println(ex.getMessage());
				return;
			}
		} else if (ruolo.equals("utente")) {
			try {
				ClienteModel model = new ClienteModel(request.getServletContext().getInitParameter("KEY"));
				WebUser utente = model.selectByKey(username);
				request.getSession().setAttribute("utente", utente);
				response.sendRedirect("home");
				return;
			} catch (SQLException | LoginException ex) {
				request.setAttribute("error", ex.getMessage());
				RequestDispatcher disp = request.getRequestDispatcher("/login.jsp");
				disp.forward(request, response);
				return;
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
