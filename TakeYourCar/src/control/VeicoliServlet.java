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

public class VeicoliServlet extends HttpServlet {
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
		RequestDispatcher disp = request.getRequestDispatcher(response.encodeURL("admin/veicoli.jsp"));
		disp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Ciao");
		response.sendRedirect(response.encodeURL("veicoli"));
	}

}
