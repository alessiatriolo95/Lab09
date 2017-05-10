package it.polito.tdp.metrodeparis.model;
import java.util.List;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.Multigraph;
import it.polito.tdp.metrodeparis.dao.MetroDAO;


public class Model {

	private UndirectedGraph <Fermata, DefaultWeightedEdge> graph;
	private List<Fermata> fermate;
	
	public List<Fermata> getFermate(){
		if(this.fermate==null){
			MetroDAO m= new MetroDAO();
			fermate= m.getAllFermate();
		}
			return fermate;
	}
	
	public void CreaGrafo(){
		graph= new Multigraph<>(DefaultWeightedEdge.class );
	}
}
