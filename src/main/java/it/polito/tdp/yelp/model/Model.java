package it.polito.tdp.yelp.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.yelp.db.YelpDao;

public class Model {
	
	private YelpDao dao;
	private double max;
	private Graph<Business, DefaultWeightedEdge> graph;
	private Map<String, Business> idMap;
	
	public Model() {
		this.dao = new YelpDao();
		this.idMap = dao.getAllBusiness();
	}
	
	public void creaGrafo(String city) {
		this.graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.graph, dao.getAllBusinessByCity(city));
		
		for(Arco arco : dao.getArchi(idMap, city))
			Graphs.addEdgeWithVertices(this.graph, arco.getB1(), arco.getB2(), arco.getDist());
		
		System.out.println("#VERTICI: " + this.getNVetici());
		System.out.println("#ARCHI: " + this.getNArchi());
	}
	
	public int getNVetici() {
		return this.graph.vertexSet().size();
	}
	
	public int getNArchi() {
		return this.graph.edgeSet().size();
	}
	
	public Set<Business> getVertici() {
		return this.graph.vertexSet();
	}
	
	public List<String> getAllCities(){
		return this.dao.getAllCities();
	} 
	
	public Business getLocaleAdiacentePiuLontano(Business b1) {
		Business b = null;
		max = 0;
		
		for(DefaultWeightedEdge e : this.graph.edgesOf(b1)) {
			if(this.graph.getEdgeWeight(e) > max )
				max = this.graph.getEdgeWeight(e);
		}
		
		for(DefaultWeightedEdge e : this.graph.edgesOf(b1)) {
			if(this.graph.getEdgeWeight(e) == max)
				b = this.graph.getEdgeTarget(e);
		}
		
		System.out.println("LOCALE PIU' DISTANTE!");
		System.out.println(b.getBusinessName() + " = " + this.max);
		
		return b;
		
	}
	
	public double getMaxDist() {
		return this.max;
	}
	
	public Map<String, Business> getMap(){
		return this.idMap;
	}
}
