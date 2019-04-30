package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import eccezioni.LoginException;

public interface Model<T, E> {
	
	public T selezionaClientePerUsername(String username) throws SQLException, LoginException;
	public E selezionaAdminPerUsername(String username) throws SQLException, LoginException;
	public ResultSet ricercaTuttiClienti() throws SQLException;
	public ResultSet ricercaTuttiVeicoli() throws SQLException;
	public ResultSet ricercaTuttiPagamenti() throws SQLException;
}
