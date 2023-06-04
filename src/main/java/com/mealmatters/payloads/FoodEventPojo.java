package com.mealmatters.payloads;

import com.mealmatters.dao.Address;

public class FoodEventPojo implements Comparable<FoodEventPojo>{
	
	
	private String id;
	private String contact;
	private Address address;
	private String name;
	private double distance;
	private String timeremain;
	public FoodEventPojo(String id,  String contact, Address address, String name, double distance,
			String timeremain) {
		super();
		this.id = id;
	
		this.contact = contact;
		this.address = address;
		this.distance = distance;
		this.timeremain = timeremain;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FoodEventPojo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public String getTimeremain() {
		return timeremain;
	}
	public void setTimeremain(String timeremain) {
		this.timeremain = timeremain;
	}

	@Override
	public int compareTo(FoodEventPojo o) {
		// TODO Auto-generated method stub
		return (int)(this.distance - o.distance);
	}

	
	

}
