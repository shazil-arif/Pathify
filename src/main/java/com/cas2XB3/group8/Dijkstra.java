package com.cas2XB3.group8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Dijkstra {
	
	//private variables
	private Edge[] edgeTo; //maintain shortest path tree
	private double[] distTo; //main distance of shortest known path
	private IndexMinPQ<Double> pq; //priority queue for quick retrieval of minimum cost edge
	
	/**
	 * @brief constructor method for class Dijkstra
	 * @param G the weighted graph to find shortest path tree for
	 * @param src node to calculate shortest paths from
	 */
	public Dijkstra(WeightedDiGraph G, int src){
		//System.out.println(src);
		int V = G.getNumNodes()+1; //number of nodes in input graph
		edgeTo = new Edge[V];
		distTo = new double[V];
		pq = new IndexMinPQ<Double>(V);	
		
				
		//initialize all distances to infinity
		for(int i = 0; i < V; i++) {
			distTo[i] = Double.POSITIVE_INFINITY;
		}
		//set distance to source as 0
		distTo[src] = 0;
		
				
		//find shortest paths
		pq.insert(src, 0.0);
		while(!pq.isEmpty()) {
			//relax every edge
			relax(G,pq.delMin());			
		}
	}
	
	/**
	 * private helper method, relax edges and update distances and shortest path tree
	 * @param G the graph 
	 * @param node the node to relax edges for
	 */
	private void relax(WeightedDiGraph G, int node) {
		
		//relax all outgoing edges from parameter node
		//Node<String> n = new Node<String>(map.get(node));
		Node<Integer> n = new Node<Integer>(node);
		for(Edge e : G.getEdgeList(n)) {
			int to = e.to();
			
			if(distTo[to] > distTo[node] + e.getWeight()) {
				distTo[to] = distTo[node] + e.getWeight();
				edgeTo[to] = e;
				
				//add remaining nodes to priority queue
				if(pq.contains(to)) pq.changeKey(to, distTo[to]);
				else pq.insert(to, distTo[to]);
			}
						
			
		}
	}
	
	public double distanceTo(int v) {
		return distTo[v];
	}
	
	//get the path to an edge
	public ArrayList<Edge> pathTo(int v){
		ArrayList<Edge> path = new ArrayList<Edge>();
		
		/*iteratively traverse the shortest path tree in Edge to
		 * since this backs up to the source, it is reversed. final output has to be reversed
		 */
		Edge x = edgeTo[v];
		while(x!=null) {
			path.add(x);
			x = edgeTo[x.from()];
		}
		Collections.reverse(path);
		return path;
	}
	
}

