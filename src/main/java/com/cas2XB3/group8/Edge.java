package com.cas2XB3.group8;

public class Edge implements Comparable<Edge>{
	int src;
	int dest;
	int weight;
	int time;
	
	public Edge(int source, int destination, int weight, int time) {
		this.src = source;
		this.dest = destination;
		this.weight = weight;
		this.time = time;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public int getSrc() {
		return src;
	}
	
	public int getDest() {
		return dest;
	}
	
	public int getTime() {
		return time;
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
