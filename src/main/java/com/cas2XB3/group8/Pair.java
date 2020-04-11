/**
 * @brief Pair is a object that contain a node's coordinates and adjacency list
 * @author Shazil Arif
 * @date April 10th 2020
 * @file Pair.java
 */
package com.cas2XB3.group8;
import java.util.ArrayList;

/**
 * @brief Pair is a object that contain coordinates and an adjacency list
 * @details this is used to encapsulate information of a node including its geographical coordinates and other nodes it connects to
 */
public class Pair {
	private ArrayList<Edge> edgeList;
	private Coordinates coord;
	
	/**
	 * @brief Constructor for class Pair
	 * @param coord the coordinates to set for this pair
	 */
	public Pair(Coordinates coord) {
		this.coord = coord;
		edgeList = new ArrayList<Edge>();
	}
	
	/**
	 * @brief getter for the coordinates encapsulated by the Pair object
	 * @return Coordinates object
	 */
	public Coordinates getCoord() { return coord; }
	
	/**
	 * @brief add an edge to the adajacency list contained in the Pair object
	 */
	public void addEdge(Edge e) { edgeList.add(e); }
	
	/**
	 * @brief getter for the adjacency list
	 * @return array list of edge objects
	 */
	public ArrayList<Edge> getEdgeList() { return edgeList; }
	
}
