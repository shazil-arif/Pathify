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
	 * available throughout the life cycle of the program and does not have to be read for every request
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	@PostConstruct
    public void init() throws FileNotFoundException, IOException, ParseException {
		Parser.readLookup();
		Parser.sortLookUp();
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
	 * @brief Takes the input destinations and finds a shortest path using the graph.
	 * @details uses Dijkstras algorithm to calculate the shortest path. Calculates shortest paths between every pair of destinations since we want
	 * to visit them in order
	 * @param payload The Map/JSON object of destinations
	 * @return The path, JSON object containing lat,long points to visit to reach each destination. 
	 */
	@CrossOrigin
	@PostMapping("/api/path")
	public ArrayList<Map<String,ArrayList<Map<String,Double>>>> testPost(@RequestBody Map<String,Double>[] payload) {
		
		/**
		 * response to send back to client, looks like:
		 * [
		 * 		{"points": [
		 *				{"lat": 10.5,
		 *				"long": 1.56	
		 *				},
		 *
		 *				{"lat": 10.5,
		 *				"long": 1.56	
		 *				},
		 *			]
		 *		}
		 * 
		 *	 	{"distance": [
		 *				{"lat": 10.5,
		 *				"long": 1.56,
		 *				"distance": 12.5
		 *				},
		 *
		 *				{"lat": 10.5,
		 *				"long": 1.56,
		 *				"distance": 13.5
		 *				},
		 *			]
		 *		}
		 * ]
		 * 
		 * Note: distances are in km
		 */
		
		ArrayList<Map<String,ArrayList<Map<String,Double>>>> response = new ArrayList<Map<String,ArrayList<Map<String,Double>>>>();
		
		Map<String,ArrayList<Map<String,Double>>> points = new HashMap<String,ArrayList<Map<String,Double>>>();
		
		points.put("points", new ArrayList<Map<String,Double>>());
		
		response.add(points);
		
		
		Map<String,ArrayList<Map<String,Double>>> dist = new HashMap<String,ArrayList<Map<String,Double>>>();
		
		dist.put("distances", new ArrayList<Map<String,Double>>());
		
		response.add(dist);
		

		
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
			
			
			Coordinates dest_coords = new Coordinates(payload[i].get("lat"),payload[i].get("long"));
			int dest = g.getClosestNode(dest_coords).getVal();
			
			//get the path to destination and add the nodes to visit in order to the response 
			ArrayList<Edge> path = d.pathTo(dest);
			for(Edge e : path) {
				
				temp = new HashMap<String,Double>();
				location = g.getNodeCoord(new Node<Integer>(e.from()));
				
				temp.put("lat",location.getX());
				temp.put("long",location.getY());
				
				response.get(0).get("points").add(temp);
			}
			
			//add destination with its distance from the source
			temp = new HashMap<String,Double>();
			location = g.getNodeCoord(new Node<Integer>(dest));
			
			temp.put("lat",location.getX());
			temp.put("long",location.getY());
			temp.put("distance",d.distanceTo(dest)/10000); //distances in dataset are 10^5 times a kilometre
			
			response.get(1).get("distances").add(temp);
			
		}
		return response;
	}
}