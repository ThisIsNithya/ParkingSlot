package com.myparking.slot;

public class Slot {
	
	
	private int slotid;
	private int locationid;
	private String slotno;
	private int status;
	private String location;
	public Slot () {
		
	}
	
	public Slot(  int slotid, int locationid,String slotno,int status,String location ) {
		
		this.slotid = slotid;
		this.locationid = locationid;
		this.slotno = slotno;
		this.status = status;
		this.location = location;

	}

	public int getSlotid() {
		return slotid;
	}

	public void setSlotid(int slotid) {
		this.slotid = slotid;
	}

	public int getLocationid() {
		return locationid;
	}

	public void setLocationid(int locationid) {
		this.locationid = locationid;
	}

	public String getSlotno() {
		return slotno;
	}

	public void setSlotno(String slotno) {
		this.slotno = slotno;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	

}
