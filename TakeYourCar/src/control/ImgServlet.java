package control;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DbConnectionModel;
import model.Immagine;

public class ImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DbConnectionModel model;
	
	public void init(ServletConfig config) throws ServletException {
		model = new DbConnectionModel(config.getServletContext().getInitParameter("KEY"));
		super.init(config);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ID = request.getParameter("ID");
		try {
			Immagine immagine = model.selezionaImmaginePerId(ID);
			InputStream stream = immagine.getImmagine().getBinaryStream();
			OutputStream out = response.getOutputStream();
			response.setContentType("image/gif");
			byte[] buffer = new byte[1];
			while (stream.read(buffer) > 0) {
				out.write(buffer);
			}
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
