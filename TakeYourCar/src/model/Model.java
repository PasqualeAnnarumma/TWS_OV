package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import eccezioni.LoginException;

public interface Model<T, E> {
	
	public Collection<T> ricerca(String ordine) throws SQLException;
	public T selezionaClientePerUsername(String username) throws SQLException, LoginException;
	public E selezionaAdminPerUsername(String username) throws SQLException, LoginException;
	public ResultSet ricercaTuttiClienti() throws SQLException;
	public T ricercaPerId(int id) throws SQLException;
	public void doSave(T obj) throws SQLException;
	public void doUpdate(T obj) throws SQLException;
	public void doDelete(T obj) throws SQLException;
}
