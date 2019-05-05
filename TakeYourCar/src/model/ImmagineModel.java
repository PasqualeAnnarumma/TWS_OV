package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import eccezioni.LoginException;

public class ImmagineModel implements Model<Immagine>{

	public synchronized Immagine selectByKey(String targa) throws SQLException, LoginException {
		return null;
	}

	public synchronized ArrayList<Immagine> selectAll() throws SQLException {
		return null;
	}
	
	public synchronized ArrayList<Immagine> searchByKey(String targa) throws SQLException {
		ArrayList<Immagine> immagini = new ArrayList<Immagine>();
		ResultSet result = null;
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			String query = "SELECT * FROM IMMAGINI WHERE Targa = ?";
			
			statement = connection.prepareStatement(query);
			statement.setString(1, targa);
			result = statement.executeQuery();
			while (result.next()) {
				Immagine immagine = new Immagine();
				immagine.setID(result.getString("ID"));
				immagine.setTarga(result.getString("Targa"));
				immagine.setImmagine(result.getBlob("foto"));
				immagini.add(immagine);
			}
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
		
		return immagini;
	}

	public void set(String ID, String value) throws SQLException {
		
	}
	
	public synchronized void delete(String key) {
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			String query = "DELETE FROM IMMAGINI WHERE ID = ?";
			
			statement = connection.prepareStatement(query);
			statement.setInt(1, Integer.parseInt(key));
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
				if (connection != null) {
					try {
						DriverManagerConnectionPool.releaseConnection(connection);
					} catch (SQLException ex) {
						System.err.println(ex.getMessage());
					}
				}
			}
		}
	}

}
