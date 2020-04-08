package com.cas2XB3.group8;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.LinkedList;

public class Graph {
	
	private int vertices;
	LinkedList<Edge> [] adjacencylist;
	
	@SuppressWarnings("unchecked")
	public Graph() {
		this.vertices = ParseEdge.line_count;
		            adjacencylist = new LinkedList[vertices];
		//initialize adjacency lists for all the vertices
		for (int i = 0; i <vertices ; i++) {
		                adjacencylist[i] = new LinkedList<>();
		}
		this.readEdge();
	}
	
	private void addEdge(int source, int destination, int weight, int time) {
		Edge edge = new Edge(source, destination, weight, time);
		adjacencylist[source].add(edge); //for directed graph
	}
	
	private void readEdge() {
		JSONParser jsonParser = new JSONParser();
        
        try (FileReader reader = new FileReader("data/edge.json"))
        {
            
            Object obj = jsonParser.parse(reader);
            JSONArray edgeList = (JSONArray) obj;
            for(Object i : edgeList) {
            	parseEdgeObject((JSONObject) i);
            }
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}
	
	private void parseEdgeObject(JSONObject edge) {
		int src = Integer.parseInt((String) edge.get("id1"));    
        int dest = Integer.parseInt((String) edge.get("id2"));
        int distance = Integer.parseInt((String) edge.get("distance"));    
        int time = Integer.parseInt((String) edge.get("time"));    
        this.addEdge(src,dest,distance,time);
	}
}
