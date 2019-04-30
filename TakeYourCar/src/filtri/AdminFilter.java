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
import model.Cliente;
import model.WebAdmin;

public class AdminFilter implements Filter {
	private String URL = "";
	
	public void init(FilterConfig fConfig) throws ServletException {
		URL = fConfig.getServletContext().getInitParameter("URL");
	}
	
	public void destroy() {
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//System.out.println("ADMINFILTER");
		HttpServletRequest hrequest = (HttpServletRequest) request;
		HttpServletResponse hresponse = (HttpServletResponse) response;
		//System.out.println("[ADMIN] ID: " + hrequest.getSession().getId());
		
		Cliente cliente = (Cliente) hrequest.getSession().getAttribute("cliente");
		WebAdmin admin = (WebAdmin) hrequest.getSession().getAttribute("admin");
		if (admin != null) {
			//System.out.println("admin");
			chain.doFilter(hrequest, hresponse);
			return;
		} else if (cliente != null) {
			hresponse.sendRedirect(hresponse.encodeURL(URL + "user/user.jsp"));
			return;
		}
		
		hrequest.getSession().setAttribute("error", "Username o password errati");
		hresponse.sendRedirect(hresponse.encodeURL(URL + "home.jsp"));
		
	}

}
