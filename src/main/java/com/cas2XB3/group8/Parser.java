package com.cas2XB3.group8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;

import java.io.IOException;
import java.lang.StringBuilder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Parser {
    private static Map<Integer,Coordinates> lookup = new HashMap<Integer,Coordinates>();
    
    public static void main(String[] args) {
		WeightedDiGraph g;
    	try {
			g = getGraph();
			Node<Integer> n = new Node<Integer>(1);
			ArrayList<Edge> l = g.getEdgeList(n);
//			for(Edge e : l) {
//				System.out.println(g.getNodeCoord(e.to()).getX() + " " + g.getNodeCoord(e.to()).getY());
//				System.out.println(e.from().getVal() + "->" + e.to().getVal());
//			}
	    	//test(g);
			Node<Integer> k = g.getClosestNode(new Coordinates(25.7742658, -80.1936589));
	    	System.out.println(k.getVal());
		} catch (IOException | org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
//    public static void test(WeightedDiGraph g) {
//    	Dijkstra d = new Dijkstra(g,new Coordinates(30.620944, -87.398204)); //node id 1
//    	ArrayList<Edge> k = d.pathTo(new Coordinates(30.618002, -87.397805)); //node id 176
//    	for(Edge e : k) {
//			System.out.println(g.getNodeCoord(e.to()).getX() + " " + g.getNodeCoord(e.to()).getY());
//			System.out.println(e.from().getVal() + "->" + e.to().getVal());
//		}
//    }

	public static WeightedDiGraph getGraph() throws FileNotFoundException, IOException, org.json.simple.parser.ParseException {
        WeightedDiGraph g = new WeightedDiGraph();
	    try {
	        BufferedReader reader = new BufferedReader(new FileReader("data/edge.json"));
		    JSONObject obj;
		    Node<Integer> from = new Node<Integer>(0);
		    Node<Integer> to = new Node<Integer>(0);
		    Edge e = new Edge(from,to,0,0);
		    reader.readLine();
	        for(String line = reader.readLine(); line != null; line = reader.readLine()) {
	        	if(!line.equals("]")) {
					if(line.substring(line.length()-1).equals(","))
						line = line.substring(0,line.length()-1);
					
		            obj = (JSONObject) new JSONParser().parse(line);
		            
		            int from_key = Integer.parseInt((String)obj.get("id1"));
		            int to_key = Integer.parseInt((String)obj.get("id2"));
		            
		            from.set(from_key);
		            to.set(to_key);
		            
		            double dist = Double.parseDouble((String)obj.get("distance"));
		            e.set(from,to, dist,0);
		            
		            if(!g.containsNode(from)) {
		            	g.addNode(from,lookup.get(from_key));
		            }
		            g.addEdge(e);	
		            //System.gc(); //call garbage collector
	        	}
	        		
			}	       
	        reader.close();     
	    }
	    catch(FileNotFoundException ex) {
	        System.out.println("Unable to open file");                
	    }
	    
	    return g;
	}
	
	public static void getLookup() throws FileNotFoundException, org.json.simple.parser.ParseException {
		FileReader fileReader = new FileReader("data/coord.json");
        BufferedReader reader = new BufferedReader(fileReader);
        JSONObject temp;
        try {
        	reader.readLine();
			for(String line = reader.readLine(); line != null; line = reader.readLine()) {
				if(!line.equals("]")) {
					if(line.substring(line.length()-1).equals(","))
						line = line.substring(0,line.length()-1);
					//System.out.println(line);
		            temp = (JSONObject) new JSONParser().parse(line);
//		            double lat = Double.parseDouble((String)temp.get("latitude"));
//		            double lng = Double.parseDouble((String)temp.get("longitude"));
		            
		            double lat = Double.parseDouble((String)temp.get("longitude")); //swap temporarily
		            double lng = Double.parseDouble((String)temp.get("latitude")); //swap temporarily
		            lookup.put(Integer.parseInt((String)temp.get("id")), new Coordinates(lat,lng));
				}
			}
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static Map<Integer,Coordinates> getMapping(){
		return lookup;
	}

}
