package it.polito.tdp.metrodeparis.model;
import java.util.ArrayList;
import java.util.List;

import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.Multigraph;
import org.jgrapht.graph.WeightedMultigraph;

import it.polito.tdp.metrodeparis.dao.MetroDAO;



public class Model {

	private WeightedMultigraph <Fermata, DefaultWeightedEdge> graph;
	private List<Fermata> fermate;
	
	public List<Fermata> getFermate(){
		if(this.fermate==null){
			MetroDAO m= new MetroDAO();
			fermate= m.getAllFermate();
		}
			return fermate;
	}
	
	
	public void CreaGrafo(){
		graph= new WeightedMultigraph<>(DefaultWeightedEdge.class );
		this.getConn();

		MetroDAO m= new MetroDAO();
		Graphs.addAllVertices(graph, this.getFermate());
		
		for(Fermata f1: graph.vertexSet()){
			for( Fermata f: f1.getFermateConnesse()){
				
				graph.addEdge(f1, f);
				graph.setEdgeWeight(graph.addEdge(f1, f), m.getTempo(f1, f));
			}
		}
	
	}
	
	public void getConn(){

		MetroDAO m= new MetroDAO();
		for(Fermata f: this.getFermate()){
			f.setConnessioni(m.getConnessioni(f.getIdFermata()));
		}
	}
	public UndirectedGraph<Fermata,DefaultWeightedEdge> getGrafo(){
		if(this.graph==null)
			this.CreaGrafo();
		
		return this.graph;
		
	}
	List<Fermata> camminomio= new ArrayList<>();
	
	public List<Fermata> CamminoMinimo(Fermata Fa, Fermata Fp){

		DijkstraShortestPath<Fermata,DefaultWeightedEdge> cammino= new DijkstraShortestPath<Fermata,DefaultWeightedEdge>(this.getGrafo(), Fa, Fp);
		for(DefaultWeightedEdge d: cammino.getPathEdgeList()){
			if(!camminomio.contains(graph.getEdgeSource(d))){
				camminomio.add(graph.getEdgeSource(d));
			}
			if(!camminomio.contains(graph.getEdgeTarget(d))){
				camminomio.add(graph.getEdgeTarget(d));
			}
		}
		
		return camminomio; 
		
		
	}
	public Double calcolaTempoTot(Fermata Fa,Fermata Fp){
		double tot=0;
		DijkstraShortestPath<Fermata,DefaultWeightedEdge> cammino= new DijkstraShortestPath<Fermata,DefaultWeightedEdge>(this.getGrafo(), Fa, Fp);
		for(DefaultWeightedEdge d: cammino.getPathEdgeList()){
			tot+= this.getGrafo().getEdgeWeight(d);
		}
		return tot+ camminomio.size()*30;
	}
	
}
