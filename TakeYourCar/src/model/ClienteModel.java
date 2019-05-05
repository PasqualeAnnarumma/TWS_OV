package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import eccezioni.LoginException;

public class ClienteModel implements Model<Cliente>{
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
	
	public synchronized ArrayList<Cliente> searchByKey(String targa) throws SQLException {
		return null;
	}
	
	public void set(String ID, String value) throws SQLException {
		
	}
	
	public synchronized void delete(String key) {
		
	}
}
