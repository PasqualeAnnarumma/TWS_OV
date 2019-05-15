package filtri;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.WebUser;

public class LoginFilter implements Filter {
	
    public LoginFilter() {
    }
    
	public void destroy() {
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hrequest = (HttpServletRequest) request;
		HttpServletResponse hresponse = (HttpServletResponse) response;
		
		WebUser utente = (WebUser) hrequest.getSession().getAttribute("utente");
		
		if (utente != null) {
			chain.doFilter(hrequest, hresponse);
			return;
		}
		
		hresponse.sendRedirect(hresponse.encodeURL("/TakeYourCar/login"));
		
	}
}
