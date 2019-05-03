package model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.Part;

import eccezioni.LoginException;

public class DbConnectionModel implements Model{
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
	
	public ArrayList<Cliente> ricercaTuttiClienti() throws SQLException {
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		ResultSet result = null;
		Connection connection = null;
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			String query = "SELECT * FROM CLIENTE";
			
			PreparedStatement statement = connection.prepareStatement(query);
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
				DriverManagerConnectionPool.releaseConnection(connection);
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			}
		}
		
		return lista;
	}
	
	public ArrayList<Veicolo> ricercaTuttiVeicoli() throws SQLException {
		ArrayList<Veicolo> veicoli = new ArrayList<Veicolo>();
		ResultSet result = null;
		Connection connection = null;
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			String query = "SELECT * FROM VEICOLO";
			
			PreparedStatement statement = connection.prepareStatement(query);
			result = statement.executeQuery();
			while (result.next()) {
				Veicolo veicolo = new Veicolo();
				veicolo.setTarga(result.getString("Targa"));
				veicolo.setModello(result.getString("Modello"));
				veicolo.setColore(result.getString("Colore"));
				veicolo.setDeposito(result.getString("ContenutoIn"));
				veicoli.add(veicolo);
			}
		} finally {
			try {
				DriverManagerConnectionPool.releaseConnection(connection);
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			}
		}
		
		return veicoli;
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
	
	public Veicolo selezionaVeicoloPerTarga(String targa) throws SQLException {
		Veicolo veicolo = new Veicolo();
		ResultSet result = null;
		Connection connection = null;
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			String query = "SELECT * FROM VEICOLO WHERE TARGA = ?";
			
			PreparedStatement statement = connection.prepareStatement(query);
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
				DriverManagerConnectionPool.releaseConnection(connection);
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			}
		}
		
		return veicolo;
	}
	
	public void caricaImmagine(String targa, Part part) throws SQLException, IOException {
		Connection connection = null;
		InputStream stream = null;
		if (part != null) stream = part.getInputStream();
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			String query = "INSERT INTO IMMAGINI (Targa, foto) VALUES (?, ?)";
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, targa);
			statement.setBlob(2, stream);
			statement.executeUpdate();
			connection.commit();
		} finally {
			try {
				DriverManagerConnectionPool.releaseConnection(connection);
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			}
		}
	}
	
	public ArrayList<Immagine> ricercaImmaginiPerTarga(String targa) throws SQLException {
		ArrayList<Immagine> immagini = new ArrayList<Immagine>();
		ResultSet result = null;
		Connection connection = null;
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			String query = "SELECT * FROM IMMAGINI WHERE Targa = ?";
			
			PreparedStatement statement = connection.prepareStatement(query);
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
				DriverManagerConnectionPool.releaseConnection(connection);
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			}
		}
		
		return immagini;
	}
	
	public Immagine selezionaImmaginePerId(String ID) throws SQLException {
		Immagine immagine = new Immagine();
		ResultSet result = null;
		Connection connection = null;
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			String query = "SELECT * FROM IMMAGINI WHERE ID = ?";
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, ID);
			result = statement.executeQuery();
			while (result.next()) {
				immagine.setID(result.getString("ID"));
				immagine.setTarga(result.getString("Targa"));
				immagine.setImmagine(result.getBlob("foto"));
			}
		} finally {
			try {
				DriverManagerConnectionPool.releaseConnection(connection);
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			}
		}
		
		return immagine;
	}

}
