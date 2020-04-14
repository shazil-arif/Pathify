/**
 * @brief class Dijkstra calculates shortest paths for a given graph
 * @author Shazil Arif
 * @file Coordinates.java
 * @date April 10th 2020
 */
package com.cas2XB3.group8;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @brief class Dijkstra calculates shortest paths for a given graph
 */
public class Dijkstra {
	
	//private variables
	private Edge[] edgeTo; //maintain shortest path tree
	private double[] distTo; //main distance of shortest known path
	private MinPQ pq;
	
	/**
	 * @brief constructor method for class Dijkstra
	 * @param G the weighted graph to find shortest path tree from the source
	 * @param src the source node to calculate shortest paths from
	 **/
	public Dijkstra(WeightedDiGraph G, int src){
		
		int V = G.getNumNodes()+1; //number of nodes in input, add 1 since nodes are indexed starting from 1
		edgeTo = new Edge[V];
		distTo = new double[V];
		pq = new MinPQ(V);	

		
		//initialize all distances to infinity
		for(int i = 0; i < V; i++) 
			distTo[i] = Double.POSITIVE_INFINITY;
		
		distTo[src] = 0; 		//set distance to source as 0
	
	
		//find shortest paths
		pq.insert(src, 0.0);
		while(!pq.isEmpty()) {
			relax(G,pq.deleteMin()); //relax every edge			
		}
	}
	
	/**
	 * @brief private helper method, relax edges and update distances and shortest path tree
	 * @param G the graph 
	 * @param node the node to relax edges for
	 **/
	private void relax(WeightedDiGraph G, int node) {
		
		//relax all outgoing edges from node
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
	
	/**
	 * @brief method that returns the distance from source to the given vertex
	 * @param v the given vertex 
	 * @return the distance from source to the given vertex
	 **/
	public double distanceTo(int v) { return distTo[v]; }
	
	/**
	 * @brief method that finds the path between the src to the given vertex
	 * @param v the given vertex 
	 * @return an array of edges that forms the path between src and the given vertex
	 **/
	public ArrayList<Edge> pathTo(int v){
		ArrayList<Edge> path = new ArrayList<Edge>();
		
		/**
		 * iteratively traverse the shortest path tree in Edge to
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

