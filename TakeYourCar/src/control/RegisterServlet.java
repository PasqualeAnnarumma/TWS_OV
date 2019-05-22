package control;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import model.ClienteModel;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String KEY = "";
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		KEY = config.getServletContext().getInitParameter("KEY");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher disp = request.getRequestDispatcher("/register.jsp");
		disp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = (String) request.getParameter("nome");
		String cognome = (String) request.getParameter("cognome");
		String cf = (String) request.getParameter("cf");
		String luogo_nascita = (String) request.getParameter("luogo_nascita");
		String data_nascita = (String) request.getParameter("data_nascita");
		String telefono = (String) request.getParameter("telefono");
		String carta = (String) request.getParameter("carta");
		String username = (String) request.getParameter("username");
		String password = (String) request.getParameter("password");
		String email = (String) request.getParameter("email");
		
		Cliente cliente = new Cliente();
		cliente.setNome(nome);
		cliente.setCognome(cognome);
		cliente.setCF(cf);
		cliente.setLuogoNascita(luogo_nascita);
		cliente.setDataNascita(data_nascita);
		cliente.setTelefono(telefono);
		cliente.setCartadiCredito(carta);
		cliente.setUsername(username);
		cliente.setPassword(password);
		cliente.setEmail(email);
		
		ClienteModel model = new ClienteModel(KEY);
		try {
			model.insert(cliente);
			String msg = "Registrazione avvenuta con successo";
			request.getSession().setAttribute("success", msg);
			if (request.getSession().getAttribute("utente") != null)
				response.sendRedirect(response.encodeURL("admin/utentiAdmin"));
			else
				response.sendRedirect(response.encodeURL("login"));
			return;
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
			String msg = "Si è verificato un problema!";
			
			if (request.getSession().getAttribute("utente") != null) {
				request.getSession().setAttribute("error", msg);
				response.sendRedirect(response.encodeRedirectURL(("admin/utentiAdmin")));
				return;
			} else {
				request.setAttribute("error", msg);
				RequestDispatcher disp = request.getRequestDispatcher("register.jsp");
				disp.forward(request, response);
			}	
		}
	}

}
