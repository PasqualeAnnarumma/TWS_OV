package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.DbConnectionModel;

public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DbConnectionModel model;
	private String URL;
	
	public void init() {
		model = new DbConnectionModel(getServletContext().getInitParameter("KEY"));
		URL = getServletContext().getInitParameter("URL");
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String targa = request.getParameter("Targa");
		if (targa == null) targa  = "";
		try {
			Part immagine = (Part) request.getPart("file");
			model.caricaImmagine(targa, immagine);
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		
		response.sendRedirect(response.encodeURL(URL + "admin/veicolo.jsp?Targa=" + targa));
	}
}
