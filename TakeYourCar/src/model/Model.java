package model;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.Part;

import eccezioni.LoginException;

public interface Model {
	public Cliente selezionaClientePerUsername(String username) throws SQLException, LoginException;
	public WebUser selezionaAdminPerUsername(String username) throws SQLException, LoginException;
	public ArrayList<Cliente> ricercaTuttiClienti() throws SQLException;
	public ArrayList<Veicolo> ricercaTuttiVeicoli() throws SQLException;
	public ArrayList<Immagine> ricercaImmaginiPerTarga(String targa) throws SQLException;
	public Veicolo selezionaVeicoloPerTarga(String targa) throws SQLException;
	public void caricaImmagine(String targa, Part part) throws SQLException, IOException;
	public ResultSet ricercaTuttiPagamenti() throws SQLException;
}
