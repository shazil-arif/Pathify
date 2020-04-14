/**
 * @file ParserTests.java
 * @brief tests the public interfaces and functionailities in Parser.Java
 * @author Bill Song
 * @Date April.10th, 2020
 */
package com.cas2XB3.group8;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ParserTests {
	
	private static Map<Integer,Coordinates> coords;
	private static WeightedDiGraph g;
	private static Coordinates a;
	
	
	/**
	 * The data size is huge, it might take up to 1 minute to parse all data (edges and nodes)
	 * @throws IOException 
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		try {
			Parser.readLookup();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		coords = Parser.getLookUp();
		
		try {
			g = Parser.getGraph();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		a = null;
		coords = null;
		g = null;
	}
	
	
	/**
	 * Since the amount of nodes is large, the main idea to test if all the nodes parsed correctly
	 * is to check the size of the nodes list first, then check the key at a specific index. If the 
	 * size of the coords list matches with the amount of coordinates in the raw test file meaning all 
	 * data are being parsed correctly.
	 */
	@Test
	public void testCoordsSize() {
		assertEquals(435666,coords.size());
		
	}
	
	@Test
	public void testCoordsList() {
		a = coords.get(1);
		assertTrue(39.971728 == a.getX());
		assertTrue(-103.992898 == a.getY());
		a = coords.get(250);
		assertTrue(39.884893 == a.getX());
		assertTrue(-103.798542 == a.getY());
	}
	
	/**
	 * Test the size of the vertices in the graph first, it should be identical with the number of nodes.
	 * Then test each edge stored in a specific vertex. This can be done by comparing the src and dest node
	 * in the edge and weight with the data shown in the raw text file.
	 */
	@Test
	public void testGraphSize() {
		assertTrue(g.getNumNodes() == coords.size());
	}
	
	@Test
	public void getGraph() {
		ArrayList<Edge> list = g.getEdgeList(new Node<Integer>(7));
		assertEquals(list.size(),2);
		Edge e = list.get(0);
		assertEquals(e.from(),7);
		assertEquals(e.to(),6);
		assertTrue(Double.compare(e.getWeight(),487) == 0);
		e = list.get(1);
		assertEquals(e.from(),7);
		assertEquals(e.to(),8);
		assertTrue(Double.compare(e.getWeight(),449) == 0);
	}

}
