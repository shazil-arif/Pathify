package com.cas2XB3.group8;

import java.util.ArrayList;

public class Pair {
	private ArrayList<Edge> edgeList;
	private Coordinates coord;
	public Pair(Coordinates coord) {
		this.coord = coord;
		edgeList = new ArrayList<Edge>();
	}
	public Coordinates getCoord() { return coord; }
	public void addEdge(Edge e) { edgeList.add(e); }
	public ArrayList<Edge> getEdgeList() { return edgeList; }
	
}
