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
import model.Cliente;
import model.DbConnectionModel;
import model.WebAdmin;

public class LoginFilter implements Filter {
	private String URL = "";
	private DbConnectionModel<Cliente> model = null;
	
	public void init(FilterConfig fConfig) throws ServletException {
		URL = fConfig.getServletContext().getInitParameter("URL");
		model = new DbConnectionModel<Cliente>(fConfig.getServletContext().getInitParameter("KEY"));
	}
	
    public LoginFilter() {
    }
    
	public void destroy() {
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//System.out.println("LOGINFILTER");
		HttpServletRequest hrequest = (HttpServletRequest) request;
		HttpServletResponse hresponse = (HttpServletResponse) response;
		
		String username = (String) request.getParameter("username");
		String pass = (String) request.getParameter("password");
		String ruolo = (String) request.getParameter("ruolo");
		
		//System.out.println("[LOGIN] ID:" + hrequest.getSession().getId());
		
		if (username == null) username = "";
		if (pass == null) pass = "";
		if (ruolo == null) ruolo = "";
		//System.out.println("Ruolo: " + ruolo + ", username: " + username + ", password: " + pass);
		
		Cliente cliente = (Cliente) hrequest.getSession().getAttribute("cliente");
		WebAdmin admin = (WebAdmin) hrequest.getSession().getAttribute("admin");
		
		if (cliente != null && !cliente.getNome().equals("")) {
			//System.out.println("E' UN CLIENTE");
			chain.doFilter(hrequest, hresponse);
			return;
		}
		else if (admin != null && !admin.getNome().equals("")) {
			//System.out.println("E' UN ADMIN");
			chain.doFilter(hrequest, hresponse);
			return;
		}
		else if (ruolo.equals("utente") && (cliente == null || cliente.getNome().equals(""))) {
			try {
				cliente = model.selezionaClientePerUsername(username);
				hrequest.getSession().setAttribute("cliente", cliente);
				chain.doFilter(hrequest, hresponse);
				return;
			} catch (SQLException | LoginException ex) {
				hrequest.getSession().setAttribute("error", ex.getMessage());
				hresponse.sendRedirect(hresponse.encodeURL(URL + "home.jsp"));
				System.err.println(ex.getMessage());
				return;
			}
		}
		
		else if (ruolo.equals("admin") && (admin == null || admin.getNome().equals(""))) {
			//System.out.println("--ADMIN--");
			try {
				admin = model.selezionaAdminPerUsername(username);
				hrequest.getSession().setAttribute("admin", admin);
				chain.doFilter(hrequest, hresponse);
				return;
			} catch (SQLException | LoginException ex) {
				hrequest.getSession().setAttribute("error", ex.getMessage());
				hresponse.sendRedirect(hresponse.encodeURL(URL + "home.jsp"));
				System.err.println(ex.getMessage());
				return;
			}
		}
		
		hrequest.getSession().setAttribute("error", "Username o password errati");
		hresponse.sendRedirect(hresponse.encodeURL(URL + "home.jsp"));
	}
}
