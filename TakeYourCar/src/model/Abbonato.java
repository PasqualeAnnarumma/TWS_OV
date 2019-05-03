package model;

import java.io.Serializable;

public class Abbonato implements Serializable {
	private static final long serialVersionUID = 1L;
	private String NTessera;
	
	public Abbonato() {
		NTessera = "";
	}

	public String getNTessera() {
		return NTessera;
	}

	public void setNTessera(String nTessera) {
		NTessera = nTessera;
	}
}
