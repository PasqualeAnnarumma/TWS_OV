package filtri;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.WebUser;

public class UserFilter implements Filter {
	private String URL = "";
	
	public void init(FilterConfig fConfig) throws ServletException {
		URL = fConfig.getServletContext().getInitParameter("URL");
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hrequest = (HttpServletRequest) request;
		HttpServletResponse hresponse = (HttpServletResponse) response;
		
		WebUser utente = (WebUser) hrequest.getSession().getAttribute("utente");
		if (utente != null && !utente.isAdmin()) {
			chain.doFilter(hrequest, hresponse);
			return;
		} else if (utente != null && utente.isAdmin()) {
			hresponse.sendRedirect(hresponse.encodeURL(URL + "admin/admin.jsp"));
			return;
		}
		
		hrequest.getSession().setAttribute("error", "Username o password errati");
		hresponse.sendRedirect(hresponse.encodeURL(URL + "login.jsp"));
	}

}
