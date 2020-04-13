/**
 * @brief WeightedDiGraph is a module to represent a edge weighted directed graph
 * @author Shazil Arif
 * @date April 10th 2020
 * @file WeightedDiGraph.java
 */
package com.cas2XB3.group8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @brief This module represents a edge Weighted directed graph
 */
public class WeightedDiGraph{
	
	private Map<Integer,Pair> adj;
	
	/**
	 * @brief WeightedDiGraph constructor
	 */
	public WeightedDiGraph() { adj = new HashMap<Integer,Pair>(); }
	
	
	/**
	 * @brief Add an edge to the weighted diGraph
	 * @param e The edge To add
	 * @throws Illegal Argument Exception
	 */
	public void addEdge(Edge e) {
		if(!containsNode(new Node<Integer>(e.from()))) throw new IllegalArgumentException("The node the edge comes from does not exist");
		adj.get(e.from()).addEdge(e);
	}
	
	
	/**
	 * @brief Check if node exists in graph with an existing adjacency list
	 * @param v The Node to check for
	 * @return True if the node is contained, false otherwise
	 */
	public boolean containsNode(Node<Integer> v) {
		return adj.get(v.getVal())!=null;
	}
	
	
	/**
	 * @brief Adding a Node to the edge
	 * @param v The Node ID to add
	 * @param coord The coordinates of the Node
	 */
	public void addNode(int v, Coordinates coord) {
		Pair p = new Pair(coord);
		adj.put(v, p);	
	}
	
	/**
	 * @brief Get the adjacency list/ which is a list of edges for a give node
	 * @param v The node
	 * @return
	 */
	public ArrayList<Edge> getEdgeList(Node<Integer> v){
		return adj.get(v.getVal()).getEdgeList();
	}
	
	
	/**
	 * @brief Gets the coordinates of a node
	 * @param v The node to get the coordinates for
	 * @return Coordinates of the node
	 */
	public Coordinates getNodeCoord(Node<Integer> v) {
		return adj.get(v.getVal()).getCoord();
	}
	
	/**
	 * @brief Get the closest node to a location given its coordinates
	 * @details Iterates through all nodes and linearly finds the node with minimum distance to the input coordinates
	 * @param coord The coordinates to check for
	 * @return Returns the node that is closest Node
	 */
	public Node<Integer> getClosestNode(Coordinates coord){
		Map<Integer,Coordinates> m = Parser.getLookUp();
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
	
	/**
	 * @brief Gets the number of nodes in the graph
	 * @return The number of nodes
	 */
	public int getNumNodes() { return adj.size(); }		
}

