package control;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.DriverManagerConnectionPool;

public class ImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ID = request.getParameter("ID");
		OutputStream out = response.getOutputStream();
		byte[] buffer = ImgServlet.scaricaImmagine(ID);
		response.setContentType("image/gif");
		if (buffer != null) {
			out.write(buffer);
		}
		out.close();		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	 public synchronized static void caricaImmagine(String targa, Part part) throws SQLException, IOException {
		Connection connection = null;
		InputStream stream = null;
		PreparedStatement statement = null;
		
		stream = part.getInputStream();
			
		try {
			connection = DriverManagerConnectionPool.getConnection();
			String query = "INSERT INTO IMMAGINI (Targa, foto) VALUES (?, ?)";
				
			statement = connection.prepareStatement(query);
			statement.setString(1, targa);
			statement.setBlob(2, stream);
			statement.executeUpdate();
			connection.commit();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			} finally {
				if (connection != null) {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
		}
	}
	 
	 public synchronized static byte[] scaricaImmagine(String id) {
		 Connection connection = null;
		 PreparedStatement statement = null;
		 ResultSet result = null;
		 
		 byte[] buffer = null;
		 
		 try {
			 connection = DriverManagerConnectionPool.getConnection();
			 String sql = "SELECT foto FROM IMMAGINI WHERE ID = ?";
			 statement = connection.prepareStatement(sql);
			 statement.setString(1, id);
			 result = statement.executeQuery();
			 
			 if (result.next()) {
				 buffer = result.getBytes("foto");
			 }
		 } catch (SQLException ex) {
			 System.err.println(ex.getMessage());
		 } finally {
			 try {
				 if (statement != null) statement.close();
			 } catch (SQLException ex) {
				 System.err.println(ex.getMessage());
			 } finally {
				 try {
					 if (connection != null) DriverManagerConnectionPool.releaseConnection(connection);
				 } catch (SQLException ex) {
					 System.err.println(ex.getMessage());
				 }
			 }
		 }
		 
		 return buffer;
	 }
	 
	 public synchronized static void rimuoviImmagine(String ID) {
		 Connection connection = null;
		 PreparedStatement statement = null;
			
		 try {
		 connection = DriverManagerConnectionPool.getConnection();
		 String query = "DELETE FROM IMMAGINI WHERE ID = ?";
				
		 statement = connection.prepareStatement(query);
		 statement.setString(1, ID);
		 statement.executeUpdate();
		 connection.commit();
		 } catch (SQLException ex) {
			System.err.println(ex.getMessage()); 
		 } finally {
			 try {
				 if (statement != null) {
					statement.close();
				}
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			} finally {
				try {
					if (connection != null) {
						DriverManagerConnectionPool.releaseConnection(connection);
					}
				} catch (SQLException ex) {
					System.err.println(ex.getMessage());
				}
			}
		}
	 }
}
