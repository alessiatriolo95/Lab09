package it.polito.tdp.metrodeparis.model;

import com.javadocmd.simplelatlng.LatLng;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		
		Fermata f= new Fermata(15,"Argentine",new LatLng(2.29011, 48.87537),575,1056);

		Fermata Fp= new Fermata(20,"Athis Mons",new LatLng(2.40332, 48.71245),333,1046);
		for(Fermata f1: model.CamminoMinimo(f, Fp)){
			System.out.println(f1);
			
		}
		System.out.println();
	//	System.out.println(model.getGrafo());
		
		//System.out.println(model.getGrafo());
		
	}

}
