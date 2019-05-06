package filtri;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class JspFilter implements Filter {
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse hresponse = (HttpServletResponse) response;
		hresponse.sendRedirect(hresponse.encodeURL("/TakeYourCar/home"));
		return;
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
