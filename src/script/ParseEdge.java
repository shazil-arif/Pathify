/**
 * @brief ParserEdge is a script to parse the graph database into JSON format
 * @file ParseEdge.java
 * @author Madhi
 * @date April 10th 2020
 */
package com.cas2XB3.group8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import org.json.simple.JSONObject;

public class ParseEdge
{

	public static int line_count = 1057066;
	
    static File jsonfile = new File("data/edge.json");
	static {
		try {
			jsonfile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }
    
    @SuppressWarnings("unchecked")
	public static void readRoads() throws IOException {
        JSONObject jsonObject = new JSONObject();
        PrintStream fileStream = new PrintStream(jsonfile);
        fileStream.println("[");
		File distdata = new File("data/USA-road-d.COL.gr");
        File timedata = new File("data/USA-road-t.COL.gr");
        
        try (BufferedReader br = new BufferedReader(new FileReader(distdata))) {
            try(BufferedReader br2 = new BufferedReader(new FileReader(timedata))) { 
            	line_count = 0;
            	int x = 0; 
                int skip = 8;
                while (br.ready() && br2.ready()) {
                    x++;
                    String[] arr = br.readLine().split(" ");
                    String[] arr2 = br2.readLine().split(" ");
                    if(x < skip) continue;
                    if(x > skip)  fileStream.println(",");             

                    jsonObject.put("id1", arr[1]);
                    jsonObject.put("id2", arr[2]);  
                    jsonObject.put("distance", arr[3]);
                    jsonObject.put("time", arr2[3]);
                    line_count++;
                    fileStream.print(jsonObject.toJSONString());
                }
                fileStream.println();
                fileStream.println("]");
                fileStream.close();
                System.out.println("Line count: " + line_count);
            }
        }

	}

    public static int getLineCount() {
    	return line_count;
    }


    public static void main( String[] args ) throws IOException
    {
        readRoads();
        System.out.println("Done dist_time JSON");
    }
}
