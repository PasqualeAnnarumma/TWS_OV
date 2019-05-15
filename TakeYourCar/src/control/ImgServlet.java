package control;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import eccezioni.LoginException;
import model.DriverManagerConnectionPool;
import model.Immagine;
import model.ImmagineModel;
import model.Key;

public class ImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String URL = "";
	
	public void init(ServletConfig config) throws ServletException {
		URL = (String) config.getServletContext().getInitParameter("URL");
		super.init(config);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		byte[] buffer = null;
		String marca = request.getParameter("Marca");
		String copertina = request.getParameter("Copertina");
		String ID = request.getParameter("ID");
		String action = request.getParameter("action");
		String targa = request.getParameter("Targa");
		if (action == null) action = "";
		
		if (action.equals("delete")) {
			try {
				ImmagineModel imgModel = new ImmagineModel();
				Key key = new Key(targa, ID);
				Immagine immagine = new Immagine();
				immagine = imgModel.selectByKey(key);
				imgModel.delete(immagine);
				response.sendRedirect(response.encodeURL(URL + "admin/veicolo?Targa=" + targa));
				return;
			} catch (SQLException | LoginException ex) {
				System.err.println(ex.getMessage());
			}
		} else if (marca != null) {
			buffer = ImgServlet.cercaLogo(marca);
		}
		else if (copertina != null) {
			buffer = ImgServlet.cercaCopertina(copertina);
		}
		else {
			buffer = ImgServlet.scaricaImmagine(ID);
		}
		
		OutputStream out = response.getOutputStream();
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
	 
	 public synchronized static void caricaMarca(String nome, Part part) throws SQLException, IOException {
			Connection connection = null;
			InputStream stream = null;
			PreparedStatement statement = null;
			
			stream = part.getInputStream();
				
			try {
				connection = DriverManagerConnectionPool.getConnection();
				String query = "INSERT INTO MARCA (Nome, Logo) VALUES (?, ?)";
					
				statement = connection.prepareStatement(query);
				statement.setString(1, nome);
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
	 
	 public synchronized static byte[] cercaLogo(String nome) {
		 Connection connection = null;
		 PreparedStatement statement = null;
		 ResultSet result = null;
		 
		 byte[] buffer = null;
		 
		 try {
			 connection = DriverManagerConnectionPool.getConnection();
			 String sql = "SELECT Logo FROM MARCA WHERE Nome = ?";
			 statement = connection.prepareStatement(sql);
			 statement.setString(1, nome);
			 result = statement.executeQuery();
			 
			 if (result.next()) {
				 buffer = result.getBytes("Logo");
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
	 
	 public synchronized static byte[] cercaCopertina(String targa) {
		 Connection connection = null;
		 PreparedStatement statement = null;
		 ResultSet result = null;
		 
		 byte[] buffer = null;
		 
		 try {
			 connection = DriverManagerConnectionPool.getConnection();
			 String sql = "SELECT foto FROM VEICOLO JOIN IMMAGINI WHERE VEICOLO.COPERTINA = IMMAGINI.ID AND VEICOLO.Targa=?";
			 statement = connection.prepareStatement(sql);
			 statement.setString(1, targa);
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
}
