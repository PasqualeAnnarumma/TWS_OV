package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import eccezioni.LoginException;

public class VeicoloModel implements Model<Veicolo>{
	
	public synchronized Veicolo selectByKey(String targa) throws SQLException, LoginException {
		Veicolo veicolo = new Veicolo();
		ResultSet result = null;
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			String query = "SELECT * FROM VEICOLO WHERE TARGA = ?";
			
			statement = connection.prepareStatement(query);
			statement.setString(1, targa);
			result = statement.executeQuery();
			while (result.next()) {
				veicolo.setTarga(result.getString("Targa"));
				veicolo.setModello(result.getString("Modello"));
				veicolo.setColore(result.getString("Colore"));
				veicolo.setDeposito(result.getString("ContenutoIn"));
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
		
		return veicolo;
	}

	public synchronized ArrayList<Veicolo> selectAll() throws SQLException {
		ArrayList<Veicolo> veicoli = new ArrayList<Veicolo>();
		ResultSet result = null;
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			String query = "SELECT * FROM VEICOLO";
			
			statement = connection.prepareStatement(query);
			result = statement.executeQuery();
			while (result.next()) {
				Veicolo veicolo = new Veicolo();
				veicolo.setTarga(result.getString("Targa"));
				veicolo.setModello(result.getString("Modello"));
				veicolo.setColore(result.getString("Colore"));
				veicolo.setMarca(result.getString("Marca"));
				veicolo.setDeposito(result.getString("ContenutoIn"));
				veicoli.add(veicolo);
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
		
		return veicoli;
	}

	public synchronized ArrayList<Veicolo> searchByKey(String targa) throws SQLException {
		return null;
	}
	
	public synchronized void set(String numero, String targa) {
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			String query = "UPDATE VEICOLO SET Copertina = ? WHERE Targa = ?";
			
			statement = connection.prepareStatement(query);
			statement.setInt(1, Integer.parseInt(numero));
			statement.setString(2, targa);
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
	
	public synchronized void delete(String key) {
		return;
	}
}
