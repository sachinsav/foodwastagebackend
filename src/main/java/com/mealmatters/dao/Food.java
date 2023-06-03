package com.mealmatters.dao;

import org.springframework.stereotype.Component;

@Component
public class Food {

	private String name;
	private long quantity;
	private Unit unit;
	public Food() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Food(String name, long quantity, Unit unit) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.unit = unit;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public Unit getUnit() {
		return unit;
	}
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	
}
