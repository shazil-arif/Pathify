/**
 * @author Tamas Leung, Shazil Arif
 * @brief Pathify application API Controller
 * @date April 10th 2020
 * @file PathifyController.java
 */
package com.cas2XB3.group8;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.json.simple.parser.ParseException;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @brief Controller for the API requests
 */
@RestController
@EnableAutoConfiguration
public class PathifyController {
	
	WeightedDiGraph g;
	
	/**
	 * @brief call the Parser module to read the graph database, @PostConstruct runs this immediately at startup then the Graph is 
	 * available throughout the lifecycle of the program and does not have to be read for every request
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	@PostConstruct
    public void init() throws FileNotFoundException, IOException, ParseException {
		Parser.readLookup();
		g = Parser.getGraph();
    }
	
	/**
	 * @brief Base Landing page for Pathify
	 * @return a greeting message
	 */
	@GetMapping("/")
	public String index() {
		return "Pathify Backend";
	}
	
	
	/**
	 * For post requests in format:
	 * [
			{"lat":120, "long":143},
			{"lat":12.0, "long":15.3},
			{"lat":52.0, "long":34.3},
			{"lat":12.3, "long":21.3},
			{"lat":11.7, "long":133.3}
		]
	 * @brief Takes the input destinations and finds a shortest path using the graph.
	 * @details uses Dijkstras algorithm to calculate the shortest path. Calculates shortest paths between every pair of destinations since we want
	 * to visit them in order
	 * @param payload The Map/JSON object of destinations
	 * @return The path, JSON object containing lat,long points to visit to reach each destination. the same format as the input but for the shortest path.
	 */
	@CrossOrigin
	@PostMapping("/path")
	public ArrayList<Map<String,Double>> testPost(@RequestBody Map<String,Double>[] payload) {
		
		//response to send back to client
		ArrayList<Map<String,Double>> response = new ArrayList<Map<String,Double>>();
		
		
		/**
		 * main idea is to calculate shortest path from first destination to everywhere else and then get the path to 
		 * second destination. Then calculate shortest paths from second destination to everywhere else and get path to
		 * third destination and repeat this for all pairs of destinations in the payload
		 */
		
		for(int i = 1; i < payload.length; i++) { //first one is source, start from second
			
			/**
			 * get the source and find the closest node to the source since there is not necessarily a one to one
			 * correspondence between the input nodes and the nodes in the dataset, because they are identified by lat,long coordinates
			 */
			
			Coordinates source = new Coordinates(payload[i-1].get("lat"), payload[i-1].get("long"));
			int src = g.getClosestNode(source).getVal();
			
			Dijkstra d = new Dijkstra(g,src);
			Map<String,Double> temp = new HashMap<String,Double>();
			Coordinates location = g.getNodeCoord(g.getClosestNode(source));
			
			//add the source to the path
			temp.put("lat",location.getX());
			temp.put("long",location.getY());
			response.add(temp);
			
			Coordinates dest_coords = new Coordinates(payload[i].get("lat"),payload[i].get("long"));
			int dest = g.getClosestNode(dest_coords).getVal();
			
			//get the path to destination and add the nodes to visit in order to the response 
			ArrayList<Edge> path = d.pathTo(dest);
			for(Edge e : path) {
				temp = new HashMap<String,Double>();
				location = g.getNodeCoord(new Node<Integer>(e.to()));
				temp.put("lat",location.getX());
				temp.put("long",location.getY());
				response.add(temp);
			}
		}
		return response;
	}
}