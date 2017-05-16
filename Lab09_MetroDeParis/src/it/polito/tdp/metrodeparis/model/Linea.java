package it.polito.tdp.metrodeparis.model;

public class Linea {
	
	private String nome;
	private int id;
	public Linea(String nome, int id) {
		super();
		this.nome = nome;
		this.id = id;
	}
	public Linea(int int1) {
	this.id=int1;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
		Linea other = (Linea) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Linea [nome=" + nome + ", id=" + id + "]";
	}
}
