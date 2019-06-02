package control;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import eccezioni.LoginException;
import model.AdminModel;
import model.WebUser;

@WebServlet("/fastLogin")
public class FastLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = (String) request.getParameter("username");
		String pass = (String) request.getParameter("password");
		String ruolo = (String) request.getParameter("ruolo");
		
		if (username == null) username = "";
		if (pass == null) pass = "";
		if (ruolo == null) ruolo = "";
		
		if (ruolo.equals("admin")) {
			try {
				AdminModel model = new AdminModel(request.getServletContext().getInitParameter("KEY"));
				WebUser utente = model.selectByKey(username);
				if (utente.getPassword().equals(pass)) {
					request.getSession().setAttribute("utente", utente);
					JSONObject json = new JSONObject();
					json.put("username", utente.getNome());
					json.put("email", utente.getEmail());
					json.put("admin", utente.isAdmin());
					System.out.println("[LoginServlet]" + json.toString());
					response.getWriter().write(json.toString());
				}
				return;
			} catch (SQLException | LoginException | JSONException ex) {
				request.setAttribute("error", ex.getMessage());
				RequestDispatcher disp = request.getRequestDispatcher("/login.jsp");
				disp.forward(request, response);
				System.err.println(ex.getMessage());
				return;
			}
		}
	}

}
