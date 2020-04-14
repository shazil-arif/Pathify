/**
 * @brief This java file is used for testing methods from Dijkstra.java
 * @author Bill Song
 * @Date April 11th, 2020
 */
package com.cas2XB3.group8;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DijkstraShortestPathTests {
	
	private static WeightedDiGraph g;
	private static Dijkstra d;
	private ArrayList<Double> allPathDistances;
	private ArrayList<Edge> path;
	
	/**
	 * The data size is huge, it might take up to 1 minute to complete all tests
	 * @throws IOException 
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		try {
			Parser.readLookup();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			g = Parser.getGraph();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		d = new Dijkstra(g,1);
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		d = null;
		g = null;
	}
	
	@Before
	public void setUp() {
		allPathDistances = new ArrayList<Double>();
		path = new ArrayList<Edge>();
	}
	
	@After
	public void tearDown() {
		allPathDistances.clear();;
		path.clear();
	}
	
	/**
	 * There is no direct edge that goes from node #1 to node #36.
	 * The path from #1 to #36 is 1 -> 2 -> 31 -> 36
	 * Distance from 1->2 is 12406
	 * Distance from 2->31 is 14007
	 * Distance from 31->33 is 1976
	 */
	@Test
	public void testDistTo() {
		double distance = d.distanceTo(33);
		assertTrue(Double.compare(distance, 28389) == 0);
	}
	
	/**
	 * For testing whether the path computed from Dijkstra is the shortest
	 * First validate each edge in the path by checking if the edge actually exists in the graph
	 * Then find all possible paths from src to dest, then find the optimal path out of all paths based on distance
	 * and then compare with the path computed by Dijkstra.
	 */
	@Test
	public void testPathTo() {
		int src = 1;
		boolean correct = true;
		path = d.pathTo(36);
		for(Edge e : path) {
			if (!testEdgeExists(src,e.to()))
				correct = false;
			src = e.to();
		}
		assertTrue(correct == true);
		findAllPaths(1, 36);
		assertTrue(Double.compare(Collections.min(allPathDistances), d.distanceTo(36)) >= 0);
	}
	
	@Test
	public void testPathTo1() {
		int src = 1;
		boolean correct = true;
		path = d.pathTo(22);
		for(Edge e : path) {
			if (!testEdgeExists(src,e.to()))
				correct = false;
			src = e.to();
		}
		assertTrue(correct == true);
		findAllPaths(1, 22);
		assertTrue(Double.compare(Collections.min(allPathDistances), d.distanceTo(22)) >= 0);
	}
	
	@Test
	public void testPathTo2() {
		int src = 1;
		boolean correct = true;
		ArrayList<Edge> path = d.pathTo(53);
		for(Edge e : path) {
			if (!testEdgeExists(src,e.to()))
				correct = false;
			src = e.to();
		}
		assertTrue(correct == true);
		findAllPaths(1, 53);
		assertTrue(Double.compare(Collections.min(allPathDistances), d.distanceTo(53)) >= 0);
	}
	
	@Test
	public void testPathTo3() {
		int src = 1;
		boolean correct = true;
		ArrayList<Edge> path = d.pathTo(67);
		for(Edge e : path) {
			if (!testEdgeExists(src,e.to()))
				correct = false;
			src = e.to();
		}
		assertTrue(correct == true);
		findAllPaths(1, 67);
		assertTrue(Double.compare(Collections.min(allPathDistances), d.distanceTo(67)) >= 0);
	}
	
	@Test
	public void testPathTo4() {
		int src = 1;
		boolean correct = true;
		ArrayList<Edge> path = d.pathTo(4);
		for(Edge e : path) {
			if (!testEdgeExists(src,e.to()))
				correct = false;
			src = e.to();
		}
		assertTrue(correct == true);
		findAllPaths(1, 4);
		assertTrue(Double.compare(Collections.min(allPathDistances), d.distanceTo(4)) >= 0);
	}
	
	@Test
	public void testPathTo5() {
		int src = 1;
		boolean correct = true;
		ArrayList<Edge> path = d.pathTo(47);
		for(Edge e : path) {
			if (!testEdgeExists(src,e.to()))
				correct = false;
			src = e.to();
		}
		assertTrue(correct == true);
		findAllPaths(1, 47);
		assertTrue(Double.compare(Collections.min(allPathDistances), d.distanceTo(47)) >= 0);
	}
	
	@Test
	public void testPathTo6() {
		int src = 1;
		boolean correct = true;
		ArrayList<Edge> path = d.pathTo(19);
		for(Edge e : path) {
			if (!testEdgeExists(src,e.to()))
				correct = false;
			src = e.to();
		}
		assertTrue(correct == true);
		findAllPaths(1, 19);
		assertTrue(Double.compare(Collections.min(allPathDistances), d.distanceTo(19)) >= 0);
	}
	
	@Test
	public void testPathTo7() {
		int src = 1;
		boolean correct = true;
		ArrayList<Edge> path = d.pathTo(76);
		for(Edge e : path) {
			if (!testEdgeExists(src,e.to()))
				correct = false;
			src = e.to();
		}
		assertTrue(correct == true);
		findAllPaths(1, 76);
		assertTrue(Double.compare(Collections.min(allPathDistances), d.distanceTo(76)) >= 0);
	}
	
	@Test
	public void testPathTo8() {
		int src = 1;
		boolean correct = true;
		ArrayList<Edge> path = d.pathTo(66);
		for(Edge e : path) {
			if (!testEdgeExists(src,e.to()))
				correct = false;
			src = e.to();
		}
		assertTrue(correct == true);
		findAllPaths(1, 66);
		assertTrue(Double.compare(Collections.min(allPathDistances), d.distanceTo(66)) >= 0);
	}
	
	/**
	 * @brief checks if the edge exists in the graph
	 * @param src - the starting node of the edge
	 * @param dest - the destination node of the edge
	 * @return true if the edge exists in the graph, false for otherwise
	 */
	private boolean testEdgeExists(int src, int dest) {
		Node<Integer> n = new Node<Integer>(src);
		for (Edge e : g.getEdgeList(n)) {
			if (e.to() == dest)
				return true;
		}
		return false;
	}
	
	/**
	 * @brief Computes the total distance of current path from src to dest
	 * @param path - a path from src to dest
	 * @return the distance
	 */
	private double findTotalDistance(ArrayList<Integer> path) {
		int src, dest;
		Node<Integer> n;
		double totalDistance = 0.0;
		for(int i = 0; i < path.size()-1; i++) {
			src = path.get(i);
			dest = path.get(i+1);
			n = new Node<Integer>(src);
			for (Edge e : g.getEdgeList(n)) {
				if (e.to() == dest)
					totalDistance = totalDistance + e.getWeight();
			}
		}
		return totalDistance;
	}
	
	/**
	 * Finds all the paths from a node to another node
	 * @param src - starting position
	 * @param dest - destination position
	 */
	private void findAllPaths(int src, int dest)  
    { 
        boolean[] visited = new boolean[g.getNumNodes()+1]; 
        ArrayList<Integer> allPaths = new ArrayList<>(); 
        allPaths.add(src); 
        findAllPathsHelper(src, dest, visited, allPaths); 
    } 
	
	private void findAllPathsHelper(int src, int dest, boolean[] visited, 
            			ArrayList<Integer> currentPath) { 
		visited[src] = true; 

		if (src == dest)  
		{ 
			allPathDistances.add(findTotalDistance(currentPath));
			// if match found then no need to traverse more till depth 
			visited[src]= false; 
			return ; 
		} 
		
		// Test with 100 nodes first since the data set is huge
		if (src > 100)
			return;

		// Iterate through all vertices adjacent to current node
		Node<Integer> n = new Node<Integer>(src);
		for (Edge e : g.getEdgeList(n))  
		{ 
			Integer i = e.to();
			if (!visited[i]) 
			{ 
				currentPath.add(i); // Store the node in current path 
				findAllPathsHelper(i, dest, visited, currentPath); 
				currentPath.remove(i); // remove current node in current path
			} 
		} 
		visited[src] = false; 
	} 
}
