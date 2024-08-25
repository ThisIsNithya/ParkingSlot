package com.myparking.location;

public class Location {
	
	private int locationid;
	private String location_name;
	private String area;
	private int slots;
	
	public Location() {
		
	}
	
	public Location(int locationid,String location_name,String area,int slots) {
		
		this.locationid = locationid;
		this.location_name = location_name;
		this.area = area;
		this.slots = slots;
		
	}

	public int getLocationid() {
		return locationid;
	}

	public void setLocationid(int locationid) {
		this.locationid = locationid;
	}

	public String getLocation_name() {
		return location_name;
	}

	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getSlots() {
		return slots;
	}

	public void setSlots(int slots) {
		this.slots = slots;
	}
	
	

}
