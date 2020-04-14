/**
 * @brief Parser is a module to read the graph database
 * @file Parser.java
 * @author Shazil Arif
 * @date April 10th 2020
 */
package com.cas2XB3.group8;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



/**
 * @brief Parser modules reads in the graph database
 */
public class Parser {
    private static Map<Integer,Coordinates> lookup = new HashMap<Integer,Coordinates>();
   
    /**
     * @brief Creates the initial graph and initializes the data by reading the files
     * @return A WeightedGraph
     * @throws FileNotFoundException
     * @throws IOException
     * @throws org.json.simple.parser.ParseException
     */
	public static WeightedDiGraph getGraph() throws FileNotFoundException, IOException, org.json.simple.parser.ParseException {
        WeightedDiGraph g = new WeightedDiGraph();
	    try {
	        BufferedReader reader = new BufferedReader(new FileReader("data/edge.json"));
		    JSONObject obj;
		    Node<Integer> from = new Node<Integer>(0);
		    Edge e;
		    reader.readLine();
	        for(String line = reader.readLine(); line != null; line = reader.readLine()) {
	        	if(!line.equals("]")) {
					if(line.substring(line.length()-1).equals(","))
						line = line.substring(0,line.length()-1);
					
		            obj = (JSONObject) new JSONParser().parse(line);
		            
		            int from_key = Integer.parseInt((String)obj.get("id1"));
		            int to_key = Integer.parseInt((String)obj.get("id2"));
		            
		            from.set(from_key);
		            
		            double dist = Double.parseDouble((String)obj.get("distance"));
		            e = new Edge(from_key,to_key, dist);
		            
		            if(!g.containsNode(from)) {
		            	g.addNode(from_key,lookup.get(from_key));
		            }
		            g.addEdge(e);	
	        	}
	        		
			}	       
	        reader.close();     
	    }
	    catch(FileNotFoundException ex) {
	        System.out.println("Unable to open file");                
	    }
	    
	    return g;
	}
	
	/**
	 * @brief Reads coordinates data set and adds new nodes and to the lookup by reading the coordinates data
	 * @throws org.json.simple.parser.ParseException for other issues while parsing/e.g malformed JSON data etc.
	 * @throws IOException 
	 */
	public static void readLookup() throws org.json.simple.parser.ParseException, IOException {
		FileReader fileReader = new FileReader("data/coord.json");
        BufferedReader reader = new BufferedReader(fileReader);
        JSONObject temp;
        try {
        	reader.readLine();
			for(String line = reader.readLine(); line != null; line = reader.readLine()) {
				if(!line.equals("]")) {
					if(line.substring(line.length()-1).equals(","))
						line = line.substring(0,line.length()-1);
		            temp = (JSONObject) new JSONParser().parse(line);
		            
		            double lat = Double.parseDouble((String)temp.get("longitude")); //the dataset provided the data flipped, hence the swap
		            double lng = Double.parseDouble((String)temp.get("latitude"));  //the dataset provided the data flipped, hence the swap
		            
		            lookup.put(Integer.parseInt((String)temp.get("id")), new Coordinates(lat,lng));
				}
			}
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException();
		}
		
	}

	/**
	 * @brief Get the mapping of nodes to coordinates
	 * @return The lookup mapping
	 */
	public static Map<Integer,Coordinates> getLookUp(){
		return lookup;
	}
	
	/**
	 * @brief sorted the lookup table of node ID to coordinates
	 */
	public static void sortLookUp() {
		ArrayList<Integer> temp = new ArrayList<Integer>(lookup.keySet());
		QuickSort.sort(temp);
		for(int key : temp) 
			lookup.put(key,lookup.get(key));
	}
}
