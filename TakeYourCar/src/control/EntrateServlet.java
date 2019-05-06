package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Entrate;
import model.EntrateModel;

public class EntrateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Entrate> entrate = new ArrayList<Entrate>();
		EntrateModel model = new EntrateModel();
		
		try {
			entrate = model.selectAll();
			request.setAttribute("entrate", entrate);
			RequestDispatcher disp = request.getRequestDispatcher(response.encodeURL("admin/entrate.jsp"));
			disp.forward(request, response);
			return;
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
