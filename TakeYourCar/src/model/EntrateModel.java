package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import eccezioni.LoginException;

public class EntrateModel implements Model<Entrate, String>{

	public synchronized Entrate selectByKey(String key) throws SQLException, LoginException {
		return null;
	}

	public synchronized ArrayList<Entrate> selectAll() throws SQLException {
		ArrayList<Entrate> entrate = new ArrayList<Entrate>();
		ResultSet result = null;
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			String query = "SELECT * FROM PAGAMENTO";
			
			statement = connection.prepareStatement(query);
			result = statement.executeQuery();
			while (result.next()) {
				Entrate ent = new Entrate();
				ent.setData(result.getString("Data_P"));
				ent.setImporto(Integer.parseInt(result.getString("Importo")));
				ent.setMetodo(result.getString("MetodoDiPagamento"));
				ent.setCliente(result.getString("Cliente"));
				entrate.add(ent);
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
		
		return entrate;
	}

	public synchronized ArrayList<Entrate> searchByKey(String targa) throws SQLException {
		return null;
	}
	
	public synchronized void set(Entrate entrate) throws SQLException {
		
	}
	
	public synchronized void delete(Entrate entrate) {
		
	}
	
	public synchronized void insert(Entrate obj) throws SQLException {
		
	}

}
