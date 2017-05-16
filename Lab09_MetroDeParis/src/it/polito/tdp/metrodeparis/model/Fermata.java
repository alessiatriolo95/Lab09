package it.polito.tdp.metrodeparis.model;


import java.util.List;

import com.javadocmd.simplelatlng.LatLng;

public class Fermata {

	private int idFermata;
	private String nome;
	private LatLng coords;
	private int idconn;
	private int idlinea;
	public Fermata(int idFermata, String nome, LatLng coords,int idconn,int idlinea) {
		this.idFermata = idFermata;
		this.nome = nome;
		this.coords = coords;
		this.idconn=idconn;
		this.idlinea=idlinea;
	}
	
	public Fermata(int idFermata) {
		this.idFermata = idFermata;
	}

	public int getIdFermata() {
		return idFermata;
	}

	public void setIdFermata(int idFermata) {
		this.idFermata = idFermata;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LatLng getCoords() {
		return coords;
	}

	public void setCoords(LatLng coords) {
		this.coords = coords;
	}

	
	
	
	public String toString() {
		return nome;
	}
	
	

	public int getIdconn() {
		return idconn;
	}

	public void setIdconn(int idconn) {
		this.idconn = idconn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idFermata;
		result = prime * result + idlinea;
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
		Fermata other = (Fermata) obj;
		if (idFermata != other.idFermata)
			return false;
		if (idlinea != other.idlinea)
			return false;
		return true;
	}

	public int getIdlinea() {
		return idlinea;
	}

	public void setIdlinea(int idlinea) {
		this.idlinea = idlinea;
	}

	

	
	

	
}
