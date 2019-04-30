package model;

import java.io.Serializable;

public class Cliente extends Persona implements Serializable {
	private static final long serialVersionUID = 1L;
	private String LuogoNascita;
	private String Telefono;
	private String N_cartadiCredito;
	private String username;
	private String password;
	
	public Cliente() {
		LuogoNascita = "";
		Telefono = "";
		N_cartadiCredito = "";
		username = "";
		password = "";
	}

	public String getLuogoNascita() {
		return LuogoNascita;
	}

	public void setLuogoNascita(String luogoNascita) {
		LuogoNascita = luogoNascita;
	}

	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String telefono) {
		Telefono = telefono;
	}

	public String getCartadiCredito() {
		return N_cartadiCredito;
	}

	public void setCartadiCredito(String n_cartadiCredito) {
		N_cartadiCredito = n_cartadiCredito;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	
}
