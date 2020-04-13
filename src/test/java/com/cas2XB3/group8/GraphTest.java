/**
 * @brief test cases for WeightedDiGraph module
 * @author Bill Song
 * @Date April.11th, 2020
 */
package com.cas2XB3.group8;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class GraphTest {
	
	private static WeightedDiGraph g;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		g = new WeightedDiGraph();
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		g = null;
	}

	/**
	 * I used the result from getNumNodes method to verify the results from addNode method
	 * If a node is added successfully into the graph, then the number of nodes stored in the
	 * graph will increment by 1
	 */
	@Test
	public void testAddNode() {
		g.addNode(0, new Coordinates(0,0));
		assertTrue(g.getNumNodes() == 1);
		g.addNode(1, new Coordinates(1,1));
		assertTrue(g.getNumNodes() == 2);
		g.addNode(2, new Coordinates(2,2));
		assertTrue(g.getNumNodes() == 3);
	}
	
	@Test
	public void testContainsNode() {
		assertTrue(g.containsNode(new Node<Integer>(0)));
		assertFalse(g.containsNode(new Node<Integer>(3)));
	}
	
	@Test
	public void testGetEdgeList() {
		Edge e = new Edge(0, 1, 10.0);
		g.addEdge(e);
		assertEquals(g.getEdgeList(new Node<Integer>(0)).get(0).from(), e.from());
		assertEquals(g.getEdgeList(new Node<Integer>(0)).get(0).to(), e.to());
		assertTrue(g.getEdgeList(new Node<Integer>(0)).get(0).getWeight() == e.getWeight());
		e = new Edge(1, 2, 20.0);
		g.addEdge(e);
		assertEquals(g.getEdgeList(new Node<Integer>(1)).get(0).from(), e.from());
		assertEquals(g.getEdgeList(new Node<Integer>(1)).get(0).to(), e.to());
		assertTrue(g.getEdgeList(new Node<Integer>(1)).get(0).getWeight() == e.getWeight());
	}
	
	/**
	 * Node ID 5 does not exist in the graph, so this edge insertion will be invalid
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testAddEdge() {
		Edge e = new Edge(5, 4, 10.0);
		g.addEdge(e);
	}

}
