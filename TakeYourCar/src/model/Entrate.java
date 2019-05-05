package model;

import java.io.Serializable;

public class Entrate implements Serializable {
	private static final long serialVersionUID = 1L;
	private String data;
	private float importo;
	private String metodo;
	private String cliente;
	
	public Entrate() {
		data = "";
		importo = 0;
		metodo = "";
		cliente = "";
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public float getImporto() {
		return importo;
	}

	public void setImporto(float importo) {
		this.importo = importo;
	}

	public String getMetodo() {
		return metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
}
