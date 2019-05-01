package model;

import java.io.Serializable;

public class Cliente extends WebUser implements Serializable {
	private static final long serialVersionUID = 1L;
	private String LuogoNascita;
	private String Telefono;
	private String N_cartadiCredito;
	
	public Cliente() {
		super();
		LuogoNascita = "";
		Telefono = "";
		N_cartadiCredito = "";
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
}
