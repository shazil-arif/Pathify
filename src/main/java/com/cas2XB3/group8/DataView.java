package com.cas2XB3.group8;

import java.util.LinkedList;

public class DataView {

	public void printGraph(Graph graph, int start, int end){
		for (int i = start; i < end ; i++) {
			LinkedList<Edge> list = graph.adjacencylist[i];
			for (int j = 0; j <list.size() ; j++) {
				System.out.println(list.get(j));
			}
		}
	}
}
