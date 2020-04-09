package com.cas2XB3.group8;

public class Edge implements Comparable<Edge>{
	int src;
	int dest;
	double weight;
	int time;
	
	public Edge(int source, int destination, double weight, int time) {
		this.src = source;
		this.dest = destination;
		this.weight = weight;
		this.time = time;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public int from() {
		return src;
	}
	
	public int  to(){
		return dest;
	}
	
	public void set(int source, int destination, double weight, int time) {
		this.src = source;
		this.dest = destination;
		this.weight = weight;
		this.time = time;
		
	}
	
	@Override
    public int compareTo(Edge that) {
        return Double.compare(this.weight, that.weight);
    }
	
	@Override
	public String toString() {
        return String.format("%d -> %d with weight: %d, time: %d", src, dest, weight, time);
    }
}
