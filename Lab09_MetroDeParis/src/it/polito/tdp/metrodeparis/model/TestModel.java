package it.polito.tdp.metrodeparis.model;

import com.javadocmd.simplelatlng.LatLng;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		
		Fermata f= new Fermata(15,"Argentine",new LatLng(2.29011, 48.87537));

		Fermata Fp= new Fermata(20,"Athis Mons",new LatLng(2.40332, 48.71245));
		//System.out.println(model.CamminoMinimo(f, Fp));
		System.out.println(model.calcolaTempoTot(f,Fp));
	
	}

}
