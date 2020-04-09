package com.cas2XB3.group8;

import org.springframework.web.bind.annotation.RestController;

import java.awt.PageAttributes.MediaType;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@EnableAutoConfiguration
public class PathifyController {
	WeightedDiGraph g;
	
	@PostConstruct
    public void init() throws FileNotFoundException, IOException, ParseException {
		Parser.getLookup();
    }
	
	@GetMapping("/")
	public String index() {
		//Graph graph = new Graph();
		return "Pathify Backend";
	}
	
	@GetMapping("/test")
	public String tester() {
		return "Pathify Test";
	}
	
	@GetMapping("/load")
	public String load() throws FileNotFoundException, IOException, ParseException {
		g = Parser.getGraph();
		return "Loaded Successfully";
	}
	
	/**
	 * For post requests in format 
	 * [
			{"xVal":120, "yVal":143},
			{"xVal":12.0, "yVal":15.3},
			{"xVal":52.0, "yVal":34.3},
			{"xVal":12.3, "yVal":21.3},
			{"xVal":11.7, "yVal":133.3}
		]
		
	 * @param coords
	 * @return
	 */
	@CrossOrigin
	@PostMapping("/path")
	public ArrayList<Map<String,Double>> testPost(@RequestBody Map<String,Double>[] payload) {
		ArrayList<Map<String,Double>> response = new ArrayList<Map<String,Double>>();
		for(int i = 1; i < payload.length; i++) { //first one is source, start from second
			Coordinates source = new Coordinates(payload[i-1].get("lat"), payload[i-1].get("long"));
			int src = g.getClosestNode(source).getVal();
			Dijkstra d = new Dijkstra(g,src);
			Map<String,Double> temp = new HashMap<String,Double>();
			Coordinates location = g.getNodeCoord(g.getClosestNode(source));
			temp.put("lat",location.getX());
			temp.put("long",location.getY());
			response.add(temp);
			
			Coordinates dest_coords = new Coordinates(payload[i].get("lat"),payload[i].get("long"));
			int dest = g.getClosestNode(dest_coords).getVal();
			ArrayList<Edge> path = d.pathTo(dest);
			for(Edge e : path) {
				temp = new HashMap<String,Double>();
				location = g.getNodeCoord(e.to());
				temp.put("lat",location.getX());
				temp.put("long",location.getY());
				response.add(temp);
			}
			
			
			
		}
		return response;
	}
	
	

}