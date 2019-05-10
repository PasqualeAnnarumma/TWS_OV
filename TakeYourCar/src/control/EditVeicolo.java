package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import eccezioni.LoginException;
import model.Immagine;
import model.ImmagineModel;
import model.Key;
import model.Veicolo;
import model.VeicoloModel;

public class EditVeicolo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String targa = request.getParameter("Targa");
		String action = request.getParameter("action");
		String ID = request.getParameter("ID");
		
		if (action == null) action = "";
		
		VeicoloModel model = new VeicoloModel();
		ImmagineModel imgModel = new ImmagineModel();
		
		if (targa == null) {
			response.sendRedirect(response.encodeURL("veicoli"));
			return;
		} else if (action.equals("delete")) {
			try {
				Key key = new Key(targa, ID);
				Immagine immagine = new Immagine();
				immagine = imgModel.selectByKey(key);
				imgModel.delete(immagine);
				response.sendRedirect(response.encodeURL("veicolo?Targa=" + targa));
				return;
			} catch (SQLException | LoginException ex) {
				System.err.println(ex.getMessage());
			}
		} else if (action.equals("copertina")) {
			try {
				Veicolo veicolo = new Veicolo();
				veicolo = model.selectByKey(targa);
				veicolo.setCopertina(Integer.parseInt(ID));
				model.set(veicolo);
			} catch (LoginException | SQLException ex) {
				System.err.println(ex.getMessage());
			}
		}
		
		try {
			Veicolo veicolo = model.selectByKey(targa);
			Key key = new Key(targa, ID);
			ArrayList<Immagine> immagini = imgModel.searchByKey(key);
			request.setAttribute("Targa", targa);
			request.setAttribute("veicolo", veicolo);
			request.setAttribute("immagini", immagini);
			RequestDispatcher disp = request.getRequestDispatcher(response.encodeURL("admin/veicolo.jsp"));
			disp.forward(request, response);
			return;
		} catch (SQLException | LoginException ex) {
			System.err.println(ex.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
