package com.cas2XB3.group8;

import java.io.IOException;

public class DataController {

	private static DataController single_instance = null; 
	  
    private Graph graph_model;
    private DataView view;
  

    public DataController() { 
    	this.graph_model = new Graph();
    	this.view = new DataView();
    } 
  
    public static DataController getInstance() { 
        if (single_instance == null) 
            single_instance = new DataController(); 
        return single_instance; 
    } 
    
    public void viewGraph() {
    	view.printGraph(graph_model, 0, 10);
    }
    
	public void updateCoord() throws IOException {
		ParseCoord.readCoord();
	}
	
	public void updateEdge() throws IOException {
		ParseEdge.readRoads();
	}
	
	public int getCoordCount() {
		return ParseCoord.line_count;
	}
	
	public int getEdgeCount() {
		return ParseEdge.line_count;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		DataController controller = DataController.getInstance();
		//controller.updateCoord();
		controller.viewGraph();
	}

}
