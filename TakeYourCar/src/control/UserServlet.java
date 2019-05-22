package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eccezioni.LoginException;
import model.Cliente;
import model.ClienteModel;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String KEY = "";
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		KEY = config.getServletContext().getInitParameter("KEY");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = (String) request.getParameter("action");
		String username = (String) request.getParameter("username");
		
		if (action == null) action = "";
		if (username == null) username = "";
		
		if (action.equals("delete")) {
			ClienteModel model = new ClienteModel(KEY);
			try {
				Cliente cliente = model.selectByKey(username);
				model.delete(cliente);
				response.sendRedirect(response.encodeURL("utentiAdmin"));
				return;
			} catch (LoginException | SQLException ex) {
				System.err.println(ex.getMessage());
			}
		}
		
		RequestDispatcher disp = request.getRequestDispatcher(response.encodeURL("../user/user.jsp"));
		disp.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
