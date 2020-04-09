package com.cas2XB3.group8;

public class Edge implements Comparable<Edge>{
	
	// State variables
	int src;
	int dest;
	double weight;
	int time;
	
	/**
	 * @brief Constructor for edge
	 * @param source - the starting position of the edge
	 * @param destination - the destination
	 * @param weight - the distance between two points
	 * @param time - estimated time
	 */
	public Edge(int source, int destination, double weight, int time) {
		this.src = source;
		this.dest = destination;
		this.weight = weight;
		this.time = time;
	}
	
	/**
	 * 
	 * @return the weight of the graph
	 */
	public double getWeight() {
		return weight;
	}
	
	/**
	 * 
	 * @return the starting position of the edge
	 */
	public int from() {
		return src;
	}
	
	/**
	 * 
	 * @return the destination position of the edge
	 */
	public int  to(){
		return dest;
	}
	
	/**
	 * 
	 * @param source
	 * @param destination
	 * @param weight
	 * @param time
	 */
	public void set(int source, int destination, double weight, int time) {
		this.src = source;
		this.dest = destination;
		this.weight = weight;
		this.time = time;
		
	}
	
	/**
	 * @brief compares two edges based on their weights
	 * @return the result of the comparison
	 */
	@Override
    public int compareTo(Edge that) {
        return Double.compare(this.weight, that.weight);
    }
	
	/**
	 * @brief allows edges to printed using System.out.print method
	 * @return a formatted string
	 */
	@Override
	public String toString() {
        return String.format("%d -> %d with weight: %d, time: %d", src, dest, weight, time);
    }
}
