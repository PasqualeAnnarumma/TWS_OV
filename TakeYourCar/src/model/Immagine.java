package model;

import java.io.Serializable;
import java.sql.Blob;

public class Immagine implements Serializable {
	private static final long serialVersionUID = 1L;
	private String ID;
	private String targa;
	private Blob immagine;
	
	public Immagine() {
		ID = "";
		targa = "";
		immagine = null;
	}
	
	public String getID() {
		return ID;
	}
	
	public void setID(String id) { 
		ID = id;
	}

	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public Blob getImmagine() {
		return immagine;
	}

	public void setImmagine(Blob immagine) {
		this.immagine = immagine;
	}
}
