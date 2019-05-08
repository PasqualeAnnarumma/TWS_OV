package model;

public class Key {
	private String targa;
	private String key;
	
	public Key(String t, String k) {
		targa = t;
		key = k;
	}

	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
