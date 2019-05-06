package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import model.ClienteModel;

public class UtentiAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClienteModel model = new ClienteModel(request.getServletContext().getInitParameter("KEY"));
		ArrayList<Cliente> clienti = new ArrayList<Cliente>();
		
		try {
			clienti = model.selectAll();
			request.setAttribute("clienti", clienti);
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		
		RequestDispatcher disp = request.getRequestDispatcher(response.encodeURL("admin/utenti.jsp"));
		disp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
