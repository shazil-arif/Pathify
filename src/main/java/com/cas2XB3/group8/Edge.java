package com.cas2XB3.group8;

public class Edge implements Comparable<Edge>{
	Node<Integer> src;
	Node<Integer> dest;
	double weight;
	int time;
	
	public Edge(Node<Integer> source, Node<Integer> destination, double weight, int time) {
		this.src = source;
		this.dest = destination;
		this.weight = weight;
		this.time = time;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public Node<Integer> from() {
		return src;
	}
	
	public Node<Integer> to(){
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
