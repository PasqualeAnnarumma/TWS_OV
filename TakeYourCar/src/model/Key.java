package model;

public class Key {
	private String targa;
	private String ID;
	
	public Key(String t, String k) {
		targa = t;
		ID = k;
	}

	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public String getKey() {
		return ID;
	}

	public void setKey(String key) {
		this.ID = key;
	}
}
