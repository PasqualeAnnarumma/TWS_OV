package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import eccezioni.LoginException;

public class DbConnectionModel<T> implements Model<WebUser>{
	private String KEY = "";
	
	public DbConnectionModel(String key) {
		KEY = key;
	}

	public Collection<Cliente> ricerca(String ordine) throws SQLException {
		return null;
	}

	public Cliente selezionaClientePerUsername(String username) throws SQLException, LoginException {
		ResultSet result = null;
		Connection connection = null;
		Cliente utente = new Cliente();
		try {
			connection = DriverManagerConnectionPool.getConnection();
			String query = "SELECT CLIENTE.CF, Nome, Cognome, LuogoNascita, Telefono, dataNascita, N_CartadiCredito, username, N_tessera,"
					+ " cast(aes_decrypt(password, \"" + KEY + "\") AS char(100)) AS password"
					+ " FROM CLIENTE JOIN ABBONATO WHERE ABBONATO.CF = CLIENTE.CF AND username = ?";
			
			PreparedStatement statement = connection.prepareStatement(query);
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
				DriverManagerConnectionPool.releaseConnection(connection);
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			}
		}
		
		if (utente == null || utente.getNome().equals("")) throw new LoginException("Username o password errati");
		return utente;
	}
	
	public WebUser selezionaAdminPerUsername(String username) throws SQLException, LoginException {
		Connection connection = null;
		WebUser utente = new WebUser();
		try {
			connection = DriverManagerConnectionPool.getConnection();
			String query = "SELECT DIPENDENTE.nome, DIPENDENTE.cognome, username," + 
					" cast(aes_decrypt(password, \"" + KEY + "\") AS char(100)) AS password, DIPENDENTE.CF," + 
					" DIPENDENTE.dataNascita, DIPENDENTE.numero, DIPENDENTE.via" + 
					" FROM WEBADMIN JOIN DIPENDENTE WHERE DIPENDENTE.CF = WEBADMIN.CF AND username= ?";
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
				utente.setCF(result.getString("CF"));
				utente.setNome(result.getString("Nome"));
				utente.setCognome(result.getString("Cognome"));
				utente.setUsername(result.getString("username"));
				utente.setPassword(result.getString("password"));
				utente.setDataNascita(result.getString("dataNascita"));
				utente.setAdmin(true);
			}
		} finally {
			try {
				DriverManagerConnectionPool.releaseConnection(connection);
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			}
		}
	
		if (utente == null || utente.getNome().equals("")) throw new LoginException("Username o password errati");
		return utente;
	}
	
	public ResultSet ricercaTuttiClienti() throws SQLException {
		ResultSet result = null;
		Connection connection = null;
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			String query = "SELECT * FROM CLIENTE";
			
			PreparedStatement statement = connection.prepareStatement(query);
			result = statement.executeQuery();
		} finally {
			try {
				DriverManagerConnectionPool.releaseConnection(connection);
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			}
		}
		
		return result;
	}
	
	public ResultSet ricercaTuttiVeicoli() throws SQLException {
		ResultSet result = null;
		Connection connection = null;
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			String query = "SELECT * FROM VEICOLO";
			
			PreparedStatement statement = connection.prepareStatement(query);
			result = statement.executeQuery();
		} finally {
			try {
				DriverManagerConnectionPool.releaseConnection(connection);
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			}
		}
		
		return result;
	}
	
	public ResultSet ricercaTuttiPagamenti() throws SQLException {
		ResultSet result = null;
		Connection connection = null;
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			String query = "SELECT * FROM PAGAMENTO";
			
			PreparedStatement statement = connection.prepareStatement(query);
			result = statement.executeQuery();
		} finally {
			try {
				DriverManagerConnectionPool.releaseConnection(connection);
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			}
		}
		
		return result;
	}

}
