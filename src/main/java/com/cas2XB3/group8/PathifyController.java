package com.cas2XB3.group8;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@EnableAutoConfiguration
public class PathifyController {

	@GetMapping("/")
	public String index() {
		//Graph graph = new Graph();
		return "Pathify Backend";
	}
	
	@GetMapping("/test")
	public String tester() {
		return "Pathify Test";
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
	@RequestMapping("/test")
	@ResponseBody
	public String testPost(@RequestBody ArrayList<Coordinates> coords) {
		String test = "";
		for (Coordinates coord: coords) {
			test += coord.getxVal() + ":" + coord.getyVal() + "\n";
		}
		return test;
	}
	
	

}