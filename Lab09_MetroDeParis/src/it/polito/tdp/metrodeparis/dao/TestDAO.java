package it.polito.tdp.metrodeparis.dao;

import java.util.List;

import org.jgrapht.Graphs;

import it.polito.tdp.metrodeparis.model.Connessione;
import it.polito.tdp.metrodeparis.model.Fermata;
import it.polito.tdp.metrodeparis.model.Model;

public class TestDAO {

	public static void main(String[] args) {
		
		MetroDAO metroDAO = new MetroDAO();
		Model model= new Model();
		System.out.println("Lista fermate");
		List<Fermata> fermate = metroDAO.getNewFermate();
		System.out.println(fermate);
		//System.out.println("Connessioni di: 87" + metroDAO.getConnessioni(87));
	/*	for(Connessione c: metroDAO.getConnessioni()){
			System.out.println(c.getF1() +"("+c.getF1().getIdlinea()+")" + "----- "+c.getF2()+"("+c.getF2().getIdlinea()+")" +"----CONN"+ c.getId());
			
			if(model.getGrafo().vertexSet().contains(c.getF1())){
				System.out.println("true1");
			}
			if(model.getGrafo().vertexSet().contains(c.getF2())){
				System.out.println("true2");
			}
		}*/
		//System.out.println("int di 1076 "+ metroDAO.getIntervallo(1076));
		System.out.println( metroDAO.getNomeLinea(1056));
		
		
		
	}

}
