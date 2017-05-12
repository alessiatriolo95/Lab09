package it.polito.tdp.metrodeparis.model;

public class Connessione {
	
	private int id;
	private int idlinea;
	private Fermata f1;
	private Fermata f2;
	
	public Connessione(int id, int idlinea, Fermata f1,Fermata f2) {
		super();
		this.id = id;
		this.idlinea = idlinea;
		this.f1 = f1;
		this.f2 = f2;
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
	public Fermata getF1() {
		return f1;
	}
	public void setF1(Fermata f1) {
		this.f1 = f1;
	}
	public Fermata getF2() {
		return f2;
	}
	public void setF2(Fermata f2) {
		this.f2 = f2;
	}
	@Override
	public String toString() {
		return "Connessione [id=" + id + ", idlinea=" + idlinea + ", f1=" + f1 + ", f2=" + f2 + "]";
	}
	
}
