package model;

import java.sql.SQLException;
import java.util.ArrayList;
import eccezioni.LoginException;

public interface Model<T, E> {
	
	public T selectByKey(E key) throws SQLException, LoginException;
	public ArrayList<T> selectAll() throws SQLException;
	public ArrayList<T> searchByKey(E key) throws SQLException;
	public void set(T obj) throws SQLException;
	public void delete(T obj) throws SQLException;
	public void insert(T obj) throws SQLException;
}
