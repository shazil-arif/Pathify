package com.cas2XB3.group8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
 * @brief represents a WeightedDiGraph 
 */
public class WeightedDiGraph{
	
	private Map<Integer,Pair> adj;
	private int num_edges;
	
	public WeightedDiGraph() {
		num_edges = 0;
		adj = new HashMap<Integer,Pair>();
	}
	
	
	//add an edge in the graph
	public void addEdge(Edge e) {
		if(!containsNode(e.from())) throw new IllegalArgumentException("The node the edge comes from does not exist");
		Node<Integer> from = e.from();
		adj.get(from.getVal()).addEdge(e);
		num_edges++;
	}
	
	
	//check if node exists in graph with an existing adjacency list
	public boolean containsNode(Node<Integer> v) {
		//return adj.keySet().contains(v.getVal());
		return adj.get(v.getVal())!=null;
	}
	
	
	//add node
	public void addNode(Node<Integer> v, Coordinates coord) {
		Pair p = new Pair(coord);
		adj.put(v.getVal(), p);	
	}
	
	//get the adjacency list/ which is a list of edges for a give node
	public ArrayList<Edge> getEdgeList(Node<Integer> v){
		return adj.get(v.getVal()).getEdgeList();
	}
	
	public Coordinates getNodeCoord(Node<Integer> v) {
		return adj.get(v.getVal()).getCoord();
	}
	
	//return a list of all nodes in the graph
//	public ArrayList<Node<Integer>> getNodeList(){
//		ArrayList<Node<String>> temp =  new ArrayList<Node<String>>();
//		for(String item : adj.keySet()) {
//			temp.add(new Node<String>(item));
//		}
//		return temp;
//	}
	
	//get number of nodes in graph, which is just size of adj list
	
	public Node<Integer> getClosestNode(Coordinates coord){
		Map<Integer,Coordinates> m = Parser.getMapping();
		int src = 0;
		double minDist = Double.POSITIVE_INFINITY;
		for(int key : m.keySet()) {
			Coordinates temp = m.get(key);
			if(coord.distTo(temp) <= minDist) {
				minDist = coord.distTo(temp);
				src = key;
			}
		}
		return new Node<Integer>(src);
		
	}
	public int getNumNodes() { return adj.size(); }
	
	public int getNumEdges() { return num_edges; }
		
}

