package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Veicolo;
import model.VeicoloModel;

public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VeicoloModel model = new VeicoloModel();
		ArrayList<Veicolo> veicoli = new ArrayList<Veicolo>();
		try {
			veicoli = model.selectAll();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		
		request.setAttribute("veicoli", veicoli);
		RequestDispatcher disp = request.getRequestDispatcher("home.jsp");
		disp.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
