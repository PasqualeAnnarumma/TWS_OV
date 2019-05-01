package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import eccezioni.LoginException;

public interface Model<T> {
	
	public T selezionaClientePerUsername(String username) throws SQLException, LoginException;
	public T selezionaAdminPerUsername(String username) throws SQLException, LoginException;
	public ResultSet ricercaTuttiClienti() throws SQLException;
	public ResultSet ricercaTuttiVeicoli() throws SQLException;
	public ResultSet ricercaTuttiPagamenti() throws SQLException;
}
