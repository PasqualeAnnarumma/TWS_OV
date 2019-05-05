package model;

import java.sql.SQLException;
import java.util.ArrayList;
import eccezioni.LoginException;

public interface Model<T> {
	
	public T selectByKey(String key) throws SQLException, LoginException;
	public ArrayList<T> selectAll() throws SQLException;
	public ArrayList<T> searchByKey(String targa) throws SQLException;
	public void set(String ID, String value) throws SQLException;
	public void delete(String key) throws SQLException;
}
