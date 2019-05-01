package model;

import java.io.Serializable;

public class Dipendente extends Persona implements Serializable{
	private static final long serialVersionUID = 1L;
	private String via;
	private String numero;
	
	public Dipendente() {
		super();
		via = "";
		numero = "";
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
}
