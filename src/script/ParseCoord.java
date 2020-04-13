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
import java.lang.StringBuilder;
import org.json.simple.JSONObject;

public class ParseCoord
{

	public static int line_count = 435666;
	
    static File jsonfile = new File("data/coord.json");
	static {
		try {
			jsonfile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }
    
    @SuppressWarnings("unchecked")
	public static void readCoord() throws IOException {
        JSONObject jsonObject = new JSONObject();
        PrintStream fileStream = new PrintStream(jsonfile);
        fileStream.println("[");
		File data = new File("data/USA-road-d.COL.co");
		
		try (BufferedReader br = new BufferedReader(new FileReader(data))) {
			line_count = 0;
            int x = 0; 
            int skip = 8;
            int latp = 4;
            int lonp = 2;
            while (br.ready()) {
                x++;
                
                String[] arr = br.readLine().split(" ");
                if(x < skip) continue;
                if(x > skip)  fileStream.println(",");

                StringBuilder lat = new StringBuilder(arr[2].toString()); 
                lat.insert(latp, '.');
                StringBuilder lon = new StringBuilder(arr[3].toString()); 
                lon.insert(lonp, '.');                

                jsonObject.put("id", arr[1]);
                jsonObject.put("latitude", lat.toString());  
                jsonObject.put("longitude", lon.toString());  
                line_count++;
                fileStream.print(jsonObject.toJSONString());
                
            }
            fileStream.println();
            fileStream.println("]");
            fileStream.close();
            System.out.println("Line count: " + line_count);
        }
	}

    public static int getLineCount() {
    	return line_count;
    }

    public static void main( String[] args ) throws IOException
    {
        readCoord();
        System.out.println("Done Coord JSON");
    }
}
