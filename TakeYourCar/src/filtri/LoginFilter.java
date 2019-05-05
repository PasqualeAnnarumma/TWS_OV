package filtri;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import eccezioni.LoginException;
import model.AdminModel;
import model.ClienteModel;
import model.WebUser;

public class LoginFilter implements Filter {
	private String URL = "";
	
	public void init(FilterConfig fConfig) throws ServletException {
		URL = fConfig.getServletContext().getInitParameter("URL");
	}
	
    public LoginFilter() {
    }
    
	public void destroy() {
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("Filtro");
		HttpServletRequest hrequest = (HttpServletRequest) request;
		HttpServletResponse hresponse = (HttpServletResponse) response;
		
		String username = (String) request.getParameter("username");
		String pass = (String) request.getParameter("password");
		String ruolo = (String) request.getParameter("ruolo");
		
		if (username == null) username = "";
		if (pass == null) pass = "";
		if (ruolo == null) ruolo = "";
		
		WebUser utente = (WebUser) hrequest.getSession().getAttribute("utente");
		
		if (utente != null && !utente.getNome().equals("")) {
			chain.doFilter(hrequest, hresponse);
			return;
		}
		else if (ruolo.equals("utente") && (utente == null || utente.getNome().equals(""))) {
			try {
				ClienteModel model = new ClienteModel(request.getServletContext().getInitParameter("KEY"));
				utente = model.selectByKey(username);
				hrequest.getSession().setAttribute("utente", utente);
				chain.doFilter(hrequest, hresponse);
				return;
			} catch (SQLException | LoginException ex) {
				hrequest.getSession().setAttribute("error", ex.getMessage());
				hresponse.sendRedirect(hresponse.encodeURL(URL + "login.jsp"));
				System.err.println(ex.getMessage());
				return;
			}
		}
		
		else if (ruolo.equals("admin") && (utente == null || utente.getNome().equals(""))) {
			try {
				AdminModel model = new AdminModel(request.getServletContext().getInitParameter("KEY"));
				utente = model.selectByKey(username);
				hrequest.getSession().setAttribute("utente", utente);
				chain.doFilter(hrequest, hresponse);
				return;
			} catch (SQLException | LoginException ex) {
				hrequest.getSession().setAttribute("error", ex.getMessage());
				hresponse.sendRedirect(hresponse.encodeURL(URL + "login.jsp"));
				System.err.println(ex.getMessage());
				return;
			}
		}
		
		hrequest.getSession().setAttribute("error", "Username o password errati");
		hresponse.sendRedirect(hresponse.encodeURL(URL + "login.jsp"));
	}
}
