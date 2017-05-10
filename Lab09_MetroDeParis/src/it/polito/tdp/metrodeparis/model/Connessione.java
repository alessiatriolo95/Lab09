package it.polito.tdp.metrodeparis.model;

public class Connessione {
	
	private int id;
	private int idlinea;
	private int idf1;
	private int idf2;
	
	public Connessione(int id, int idlinea, int idf1, int idf2) {
		super();
		this.id = id;
		this.idlinea = idlinea;
		this.idf1 = idf1;
		this.idf2 = idf2;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Connessione other = (Connessione) obj;
		if (id != other.id)
			return false;
		return true;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdlinea() {
		return idlinea;
	}
	public void setIdlinea(int idlinea) {
		this.idlinea = idlinea;
	}
	public int getIdf1() {
		return idf1;
	}
	public void setIdf1(int idf1) {
		this.idf1 = idf1;
	}
	public int getIdf2() {
		return idf2;
	}
	public void setIdf2(int idf2) {
		this.idf2 = idf2;
	}
	@Override
	public String toString() {
		return "Connessione [id=" + id + ", idlinea=" + idlinea + ", idf1=" + idf1 + ", idf2=" + idf2 + "]";
	}
}
