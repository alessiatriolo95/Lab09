package it.polito.tdp.metrodeparis.model;
import java.util.ArrayList;
import java.util.List;

import org.jgrapht.EdgeFactory;
import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.WeightedGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedMultigraph;
import org.jgrapht.graph.DirectedWeightedMultigraph;
import org.jgrapht.graph.DirectedWeightedPseudograph;
import org.jgrapht.graph.Multigraph;
import org.jgrapht.graph.WeightedMultigraph;

import it.polito.tdp.metrodeparis.dao.MetroDAO;



public class Model {

	private WeightedGraph<Fermata, DefaultWeightedEdge> graph;
	private List<Fermata> fermate;
	private List<Connessione> connessioni;
	
	/*public List<Fermata> getFermate(){
		if(this.fermate==null){
			MetroDAO m= new MetroDAO();
			fermate= m.getAllFermate();
		}
			return fermate;
	}*/
	public List<Fermata> getFermate(){
		if(this.fermate==null){
			MetroDAO m= new MetroDAO();
			fermate= m.getNewFermate();
		}
			return fermate;
	}
	public List<Connessione> getConn(){
		if(this.connessioni==null){
			MetroDAO m= new MetroDAO();
			connessioni= m.getConnessioni();
		}
			return connessioni;
	}
	
	/*public void CreaGrafo(){
		graph= new WeightedMultigraph<>(DefaultWeightedEdge.class );
		this.getConn();

		MetroDAO m= new MetroDAO();
		Graphs.addAllVertices(graph, this.getFermate());
		
		for(Fermata f1: graph.vertexSet()){
			for( Fermata f: f1.getFermateConnesse()){
				
				graph.addEdge(f1, f);
				graph.setEdgeWeight(graph.addEdge(f1, f), m.getTempo(f1, f));
			}
		}}*/
	
	public void CreaGrafo(){
		graph= new DirectedWeightedMultigraph<>(DefaultWeightedEdge.class );

		MetroDAO m= new MetroDAO();
		
		Graphs.addAllVertices(graph, this.getFermate());
		for(Fermata f: graph.vertexSet()){
			for(Fermata f1: graph.vertexSet() ){
				if(f1.getIdFermata()== f.getIdFermata() && f.getIdlinea()!= f1.getIdlinea()){
					DefaultWeightedEdge e3=graph.addEdge(f, f1);
					DefaultWeightedEdge e4=graph.addEdge(f1, f);
					graph.setEdgeWeight(e3, m.getIntervallo(f1.getIdlinea()));
					graph.setEdgeWeight(e4, m.getIntervallo(f.getIdlinea()));
				}
					
			}
		}
		for(Connessione c: this.getConn()){
			
			DefaultWeightedEdge e1=graph.addEdge(c.getF1(), c.getF2());
			DefaultWeightedEdge e2=graph.addEdge(c.getF2(), c.getF1());
			
			if(c.getF1().equals(c.getF2())){

				graph.setEdgeWeight(e1, m.getTempo(c.getF1(), c.getF2(),c.getF1().getIdlinea()));
				graph.setEdgeWeight(e2, m.getTempo(c.getF1(), c.getF2(),c.getF1().getIdlinea()));
			}
				
			}
		}
	
	
	
	public WeightedGraph<Fermata, DefaultWeightedEdge> getGrafo(){
		if(this.graph==null)
			this.CreaGrafo();
		
		return this.graph;
		
	}
	List<Fermata> camminomio= new ArrayList<>();
	private List<Linea> linea;
	
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
	/*public List<Linea>getLinea(){
		MetroDAO m= new MetroDAO();
		if(linea==null){
			linea.addAll(m.getNomeLinea());
		}
		return linea;
	}*/
	public Double calcolaTempoTot(Fermata Fa,Fermata Fp){
		double tot=0;
		DijkstraShortestPath<Fermata,DefaultWeightedEdge> cammino= new DijkstraShortestPath<Fermata,DefaultWeightedEdge>(this.getGrafo(), Fa, Fp);
		for(DefaultWeightedEdge d: cammino.getPathEdgeList()){
			tot+= this.getGrafo().getEdgeWeight(d);
		}
		return tot+ (camminomio.size()-1)*30;
	}
	
}
