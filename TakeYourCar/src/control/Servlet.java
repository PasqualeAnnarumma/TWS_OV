package control;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.WebUser;

public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String URL = "";
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		URL = config.getServletContext().getInitParameter("URL");
	}
	
	public void destroy() {
		super.destroy();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = (String) request.getParameter("action");
		if (action == null) action = "";
		
		if (action.equals("logout")) {
			request.getSession(false).removeAttribute("cliente");
			request.getSession(false).removeAttribute("admin");
			request.getSession(false).invalidate();
			response.sendRedirect(response.encodeURL(URL + "login.jsp"));
			return;
		}
		
		WebUser utente = (WebUser) request.getSession().getAttribute("utente");
		
		if (action.equals("delete")) {
			if (utente != null && !utente.getNome().equals("") && utente.isAdmin()) {
				String ID = request.getParameter("ID");
				String Targa = request.getParameter("Targa");
				if (ID == null) ID = "";
				if (Targa == null) Targa = "";
					ImgServlet.rimuoviImmagine(ID);
					response.sendRedirect(response.encodeURL(URL + "admin/veicolo.jsp?Targa=" + Targa));
			}
		}
		else if (utente != null && !utente.getNome().equals("")) {
			response.sendRedirect(response.encodeURL(URL + "home.jsp"));
			return;			
		}
		else {
			response.sendRedirect(response.encodeURL(URL + "login.jsp"));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
