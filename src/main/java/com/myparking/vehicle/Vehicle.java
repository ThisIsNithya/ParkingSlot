package com.myparking.vehicle;

public class Vehicle {
    private int vehicleid;
    private String vehicle_type;
    private float cost;
    
    public Vehicle () {
    	
    }
    
    public Vehicle (int vehicleid , String vehicle_type , float cost ) {
    	this.vehicleid = vehicleid;
    	this.vehicle_type = vehicle_type;
    	this.cost = cost;
    }
    
	public int getVehicleid() {
		return vehicleid;
	}
	public void setVehicleid(int vehicleid) {
		this.vehicleid = vehicleid;
	}
	public String getVehicle_type() {
		return vehicle_type;
	}
	public void setVehicle_type(String vehicle_type) {
		this.vehicle_type = vehicle_type;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
    
    
    
    
}
