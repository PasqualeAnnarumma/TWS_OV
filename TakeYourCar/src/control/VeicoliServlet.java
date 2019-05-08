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
		VeicoloModel model = new VeicoloModel();
		String modello = (String) request.getParameter("modello");
		String colore = (String) request.getParameter("colore");
		String deposito = (String) request.getParameter("deposito");
		String marca = (String) request.getParameter("marca");
		String copertina = (String) request.getParameter("copertina");
		String targa = (String) request.getParameter("targa");
		String prezzo = (String) request.getParameter("prezzo");
		
		Veicolo veicolo = new Veicolo();
		veicolo.setTarga(targa);
		veicolo.setColore(colore);
		veicolo.setCopertina(Integer.parseInt(copertina));
		veicolo.setDeposito(deposito);
		veicolo.setMarca(marca);
		veicolo.setModello(modello);
		veicolo.setPrezzo(Float.parseFloat(prezzo));
		model.insert(veicolo);
		
		response.sendRedirect(response.encodeURL("veicoli"));
		return;
	}

}
