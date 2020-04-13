/**
 * @brief Coordinates is an object to represent a latitude, longitude position
 * @author Shazil Arif
 * @file Coordinates.java
 * @date April 10th 2020
 */
package com.cas2XB3.group8;


/**
 * @brief Coordinates is an object to represent a latitude, longitude position
 */
public class Coordinates implements Comparable<Coordinates> {
	private Double xVal;
	private Double yVal;

    /**
     * @brief initializes the Coordinates model
     * @param x The x value of the coordinate  
     * @param y The y value of the coordinate  
     */
	public Coordinates(double x, double y) {
		xVal = x;
		yVal = y;
	}
	
    /**
     * @brief returns the x value of the coordinate
     * @return the x value of the coordinate    
     */
	public Double getX() { return xVal; }
	
    /**
     * @brief returns the y value of the coordinate
     * @return The y value of the coordinate    
     */
	public Double getY() { return yVal; }
	
    /**
     * @brief sets y of the coordinate to the given value
     * @param the double value to become the new y    
     */
	public void setY(Double yVal) { this.yVal = yVal; }
	
    /**
     * @brief sets x of the coordinate to the given value
     * @param the double value to become the new x    
     */
	public void setX(Double xVal) {	this.xVal = xVal;}
	
    /**
     * @brief calculates the distance between two coordinates
     * @param other another coordinate that's given
     * @return the distance between the current coordinate and the given coordinate
     */
	public double distTo(Coordinates other) {
		
		double radius = 6371; //earth's radius

		
        double lat_one = Math.toRadians(this.xVal);
        double lat_two = Math.toRadians(other.xVal);

		//spherical law of cosines formula
        double long_diff = Math.toRadians(other.getY() - this.getY());

        double distance = Math.acos(Math.sin(lat_one)*Math.sin(lat_two) + Math.cos(lat_one)*Math.cos(lat_two)*Math.cos(long_diff)) * radius;

        return  Math.round(distance * 100.0) / 100.0;

	}

	@Override
	public int compareTo(Coordinates o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
