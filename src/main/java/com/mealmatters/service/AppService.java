package com.mealmatters.service;

import java.util.List;

import com.mealmatters.dao.FoodDetailEntity;
import com.mealmatters.dao.Location;
import com.mealmatters.payloads.FoodEventPojo;

public interface AppService {

	public void addnewfood(FoodDetailEntity food);
	public List<FoodEventPojo> getnearestdistance(Location location);
	
}
