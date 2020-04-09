package com.cas2XB3.group8;

public class Coordinates {
	private Double xVal;
	private Double yVal;
	
	public Coordinates(double x, double y) {
		xVal = x;
		yVal = y;
	}
	
	public Double getX() {
		return xVal;
	}
	public Double getY() {
		return yVal;
	}
	public void setY(Double yVal) {
		this.yVal = yVal;
	}
	public void setX(Double xVal) {
		this.xVal = xVal;
	}
	public double distTo(Coordinates other) {
		
		//haversine formula
		double radius = 6371;

        double lat_one = Math.toRadians(this.xVal);
        double lat_two = Math.toRadians(other.xVal);

        double long_diff = Math.toRadians(other.getY() - other.getY());

        double distance = Math.acos(Math.sin(lat_one)*Math.sin(lat_two) + Math.cos(lat_one)*Math.cos(lat_two)*Math.cos(long_diff)) * radius;

        return  Math.round(distance * 100.0) / 100.0;

	}
	
	
}
