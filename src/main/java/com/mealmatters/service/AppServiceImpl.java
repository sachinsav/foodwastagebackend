package com.mealmatters.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mealmatters.dao.Address;
import com.mealmatters.dao.Food;
import com.mealmatters.dao.FoodDetailEntity;
import com.mealmatters.dao.Location;
import com.mealmatters.dao.UserEntity;
import com.mealmatters.payloads.FoodEventPojo;
import com.mealmatters.repository.FoodDetailRepository;
import com.mealmatters.repository.UserRepository;


@Service
public class AppServiceImpl implements AppService{

	
	@Autowired
	private FoodDetailRepository foodRepository;
	
	@Autowired
	private UserRepository userRepository;
	@Override
	public void addnewfood(FoodDetailEntity food) {
		// TODO Auto-generated method stub
		
		food.setCurrentTime(System.currentTimeMillis() +  4 * 1000 * 60 * 60);
		
		foodRepository.save(food);
		
		
		
	}
	@Override
	public List<FoodEventPojo> getnearestdistance(Location location) {
		// TODO Auto-generated method stub
		
		List<FoodDetailEntity> list = foodRepository.findAll();
		List<FoodEventPojo> res = new ArrayList<>();
		double usrLat = location.getLatitude();
		double usrLong = location.getLongitude();
		System.out.println("88888" + list.toString());
		for(FoodDetailEntity entity : list) {
			
			double foodLat = entity.getLocation().getLatitude();
			double foodLong = entity.getLocation().getLongitude();
			
			if(System.currentTimeMillis() >= entity.getCurrentTime())
				continue;
			
			Address address = entity.getAddress();
			UserEntity user = userRepository.findUserEntityByUsername(entity.getUsername());
			String name = user.getName();
			String contact = user.getContact();
			
			long totaltime = entity.getCurrentTime() - System.currentTimeMillis();
			String remainingtime = getTimeinhrmin(totaltime);
			double distance = calculateDistance(usrLat, usrLong, foodLong, foodLat);
			
			FoodEventPojo fep = new FoodEventPojo();
			fep.setAddress(address);
			fep.setContact(contact);
			fep.setTimeremain(remainingtime);
			fep.setId(entity.getId());
			fep.setDistance(distance);
			fep.setName(name);
			res.add(fep);
			
			
		}
		Collections.sort(res);
		return res;
		
	}
	  
	public static String getTimeinhrmin(long milliseconds) {
		
		long seconds = milliseconds/1000;
		long hr = seconds/3600;
		
		long min= (seconds%3600)/60;
		
		return hr+" hr,"+min+" min";
		
		
	}

	public static double calculateDistance(double useLat, double usrLong, double foodLat, double foodLong) {
		
		System.out.println(useLat+ ", "+usrLong);
		System.out.println(foodLat+", "+foodLong);
		// Convert latitude and longitude to radians
		double lat1Rad = Math.toRadians(useLat);
		double lon1Rad = Math.toRadians(usrLong);
		double lat2Rad = Math.toRadians(foodLat);
		double lon2Rad = Math.toRadians(foodLong);

		// Calculate the differences
		double deltaLat = lat2Rad - lat1Rad;
		double deltaLon = lon2Rad - lon1Rad;

		// Haversine formula
		double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
				+ Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		// Calculate the distance
		double distance = 637 * c;

		return distance;
	}
	@Override
	public List<Food> getthefoodby(String id) {
		// TODO Auto-generated method stub
		
		FoodDetailEntity entity = foodRepository.findById(id).orElse(null);
		List<Food> list = entity.getFoods();
		return list;
	}
	   
	


}
/*
def calculate_distance(lat1, lon1, lat2, lon2):
# Convert coordinates to radians
lat1_rad = math.radians(lat1)
lon1_rad = math.radians(lon1)
lat2_rad = math.radians(lat2)
lon2_rad = math.radians(lon2)

# Haversine formula
dlon = lon2_rad - lon1_rad
dlat = lat2_rad - lat1_rad
a = math.sin(dlat/2)*2 + math.cos(lat1_rad) * math.cos(lat2_rad) * math.sin(dlon/2)*2
c = 2 * math.atan2(math.sqrt(a), math.sqrt(1-a))
distance = 6371 * c  # Earth's radius in kilometers

return distance

# Example usage
user_latitude = 37.7749
user_longitude = -122.4194
donor_latitude = 37.3352
donor_longitude = -121.8811

distance = calculate_distance(user_latitude, user_longitude, donor_latitude, donor_longitude)
print(f"The distance between the user and the donor is approximately {distance} km.")*/