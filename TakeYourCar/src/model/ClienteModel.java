package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import eccezioni.LoginException;

public class ClienteModel implements Model<Cliente, String>{
	private String KEY = "";
	
	public ClienteModel(String key) {
		KEY = key;
	}

	public synchronized Cliente selectByKey(String username) throws SQLException, LoginException {
		ResultSet result = null;
		Connection connection = null;
		PreparedStatement statement = null;
		Cliente utente = new Cliente();
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			String query = "SELECT CLIENTE.CF, Nome, Cognome, LuogoNascita, Telefono, dataNascita, N_CartadiCredito, username, N_tessera,"
					+ " cast(aes_decrypt(password, \"" + KEY + "\") AS char(100)) AS password"
					+ " FROM CLIENTE JOIN ABBONATO WHERE ABBONATO.CF = CLIENTE.CF AND username = ?";
			
			statement = connection.prepareStatement(query);
			statement.setString(1, username);
			result = statement.executeQuery();
			
			while (result.next()) {
				utente.setCF(result.getString("CF"));
				utente.setNome(result.getString("Nome"));
				utente.setCognome(result.getString("Cognome"));
				utente.setLuogoNascita(result.getString("LuogoNascita"));
				utente.setTelefono(result.getString("Telefono"));
				utente.setCartadiCredito(result.getString("N_CartadiCredito"));
				utente.setUsername(result.getString("username"));
				utente.setPassword(result.getString("password"));
				utente.setDataNascita(result.getString("dataNascita"));
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
		
		if (utente == null || utente.getNome().equals("")) throw new LoginException("Username o password errati");
		return utente;
	}
	
	public synchronized ArrayList<Cliente> selectAll() throws SQLException {
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		ResultSet result = null;
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			String query = "SELECT * FROM CLIENTE";
			
			statement = connection.prepareStatement(query);
			result = statement.executeQuery();
			while (result.next()) {
				Cliente cliente = new Cliente();
				cliente.setCF(result.getString("CF"));
				cliente.setNome(result.getString("Nome"));
				cliente.setCognome(result.getString("Cognome"));
				cliente.setLuogoNascita(result.getString("LuogoNascita"));
				cliente.setDataNascita(result.getString("dataNascita"));
				cliente.setTelefono(result.getString("Telefono"));
				cliente.setUsername(result.getString("username"));
				lista.add(cliente);
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
		
		return lista;
	}
	
	public synchronized ArrayList<Cliente> searchByKey(String username) throws SQLException {
		return null;
	}
	
	public synchronized void set(Cliente cliente) throws SQLException {
		
	}
	
	public synchronized void delete(Cliente cliente) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			String query = "DELETE FROM CLIENTE WHERE username = ?";
			
			statement = connection.prepareStatement(query);
			statement.setString(1, cliente.getUsername());
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
	
	public synchronized void insert(Cliente c) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		PreparedStatement statement2 = null;
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			String query = "INSERT INTO CLIENTE VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, aes_encrypt(?, \"" + KEY + "\"))";
			String query2 = "INSERT INTO ABBONATO (CF) VALUES (?)";
			
			statement = connection.prepareStatement(query);
			statement.setString(1, c.getCF());
			statement.setString(2, c.getNome());
			statement.setString(3, c.getCognome());
			statement.setString(4, c.getLuogoNascita());
			statement.setString(5, c.getTelefono());
			statement.setString(6, c.getDataNascita());
			statement.setString(7, c.getCartadiCredito());
			statement.setString(8, c.getEmail());
			statement.setString(9, c.getUsername());
			statement.setString(10, c.getPassword());
			statement.executeUpdate();
			connection.commit();
			
			statement2 = connection.prepareStatement(query2);
			statement2.setString(1, c.getCF());
			statement2.executeUpdate();
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
}
