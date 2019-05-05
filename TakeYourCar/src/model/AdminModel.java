package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import eccezioni.LoginException;

public class AdminModel implements Model<WebUser> {
	private String KEY = "";
	
	public AdminModel(String key) {
		KEY = key;
	}

	public synchronized WebUser selectByKey(String username) throws SQLException, LoginException {
		Connection connection = null;
		WebUser utente = new WebUser();
		PreparedStatement statement = null;
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			String query = "SELECT DIPENDENTE.nome, DIPENDENTE.cognome, username," + 
					" cast(aes_decrypt(password, \"" + KEY + "\") AS char(100)) AS password, DIPENDENTE.CF," + 
					" DIPENDENTE.dataNascita, DIPENDENTE.numero, DIPENDENTE.via" + 
					" FROM WEBADMIN JOIN DIPENDENTE WHERE DIPENDENTE.CF = WEBADMIN.CF AND username= ?";
			
			statement = connection.prepareStatement(query);
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

	public synchronized ArrayList<WebUser> selectAll() throws SQLException {
		return null;
	}

	public synchronized ArrayList<WebUser> searchByKey(String targa) throws SQLException {
		return null;
	}
	
	public void set(String ID, String value) throws SQLException {
		
	}
	
	public synchronized void delete(String key) {
		
	}

}
