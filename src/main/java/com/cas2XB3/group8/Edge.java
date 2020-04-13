/**
 * @brief Edge is a class to represent a directed and weighted edge
 * @file Edge.java
 * @author Shazil Arif
 * @date April 10th 2020
 */
package com.cas2XB3.group8;


/**
 * @brief Edge is a class to represent a directed and weighted edge
 */
public class Edge{
	
	// State variables
	private int src;
	private int dest;
	private double weight;
	
	/**
	 * @brief Constructor for edge
	 * @param source - the starting position of the edge
	 * @param destination - the destination
	 * @param weight - the distance between two points
	 * @param time - estimated time
	 */
	public Edge(int source, int destination, double weight) {
		this.src = source;
		this.dest = destination;
		this.weight = weight;
	}
	
	/**
	 * @brief get the weight associated with the edge
	 * @return the weight on the edge
	 */
	public double getWeight() { return weight; }
	
	/**
	 * @brief get the Node ID that the edge is directed from
	 * @return the starting position of the edge
	 */
	public int from() { return src; }
	
	/**
	 * @brief get the Node ID that the edge is directed to
	 * @return the starting position of the edge
	 */
	public int  to() { return dest; }
}
