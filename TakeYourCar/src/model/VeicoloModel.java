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
				veicolo.setPrezzo(result.getFloat("Prezzo"));
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
				veicolo.setPrezzo(result.getFloat("Prezzo"));
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
	
	public synchronized void delete(String targa) {
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			String query = "DELETE FROM VEICOLO WHERE Targa = ?";
			
			statement = connection.prepareStatement(query);
			statement.setString(1, targa);
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
		return;
	}
	
	public synchronized void insert(String targa, String modello, String colore, int deposito, String marca, int copertina) {
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			String query = "INSERT INTO VEICOLO VALUES (?, ?, ?, ?, ?, ?)";
			
			statement = connection.prepareStatement(query);
			statement.setString(1, targa);
			statement.setString(2, modello);
			statement.setString(3, colore);
			statement.setInt(4, deposito);
			statement.setString(5, marca);
			statement.setInt(6, copertina);
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
