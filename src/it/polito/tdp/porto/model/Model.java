package it.polito.tdp.porto.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.porto.db.PortoDAO;

public class Model {
	
	private PortoDAO dao;
	private Map<Integer, Author> idMap;
	private Graph<Author, DefaultEdge> graph;
	
	public Model() {
		idMap = new HashMap<Integer, Author>();
		dao = new PortoDAO();
		graph = new SimpleGraph<>(DefaultEdge.class);
	}
	
	public void createGraph() {
		dao.getAutori(idMap);
		Graphs.addAllVertices(graph, idMap.values());
		dao.createEdge(graph, idMap);
	}
	
	public Set<Author> getNeighbours(Author author) {
		Set<Author> edgesConn = new HashSet<Author>(Graphs.neighborSetOf(graph, author));
		return edgesConn;
	}
	
	public String printNeighbours(Author author) {
		String print = new String();
		
		for(Author a : this.getNeighbours(author)) {
			print += a.toString()+"\n";
		}
		
		return print;
	}

	public Map<Integer, Author> getIdMap() {
		return idMap;
	}

	public Graph<Author, DefaultEdge> getGraph() {
		return graph;
	}

	
	
}
