package model;

import java.io.Serializable;
public class Veicolo implements Serializable {
	private static final long serialVersionUID = 1L;
	private boolean auto;
	private String targa;
	private String modello;
	private String colore;
	private String deposito;
	private String marca;
	private float prezzo;
	
	public Veicolo() {
		targa = "";
		modello = "";
		colore = "";
		deposito = "";
		marca = "";
		auto = false;
		prezzo = 0;
	}

	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public String getColore() {
		return colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}

	public String getDeposito() {
		return deposito;
	}

	public void setDeposito(String deposito) {
		this.deposito = deposito;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public void setAuto() {
		auto = true;
	}
	
	public boolean isAuto() {
		return auto;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public void setAuto(boolean auto) {
		this.auto = auto;
	}
}
