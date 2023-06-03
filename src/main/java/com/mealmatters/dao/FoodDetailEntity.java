package com.mealmatters.dao;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "food_detail")
public class FoodDetailEntity {
	
	   @Id
	    private String id;
	    private String username;
	    private long contact;
	    private String description;
	    private String image;
	    private long currentTime;
	    private Address address;
	    private List<Food> foods;
	    private Location location;
		public FoodDetailEntity() {
			super();
			// TODO Auto-generated constructor stub
		}
		public FoodDetailEntity(String id, String email, long contact, String type, String description, String image,
				long currentTime, Address address, List<Food> foods, Location location) {
			super();
			this.id = id;
			this.username = email;
			this.contact = contact;
	
			this.description = description;
			this.image = image;
			this.currentTime = currentTime;
			this.address = address;
			this.foods = foods;
			this.location = location;
		}
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public long getContact() {
			return contact;
		}
		public void setContact(long contact) {
			this.contact = contact;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getImage() {
			return image;
		}
		public void setImage(String image) {
			this.image = image;
		}
		public long getCurrentTime() {
			return currentTime;
		}
		public void setCurrentTime(long currentTime) {
			this.currentTime = currentTime;
		}
		public Address getAddress() {
			return address;
		}
		public void setAddress(Address address) {
			this.address = address;
		}
		public List<Food> getFoods() {
			return foods;
		}
		public void setFoods(List<Food> foods) {
			this.foods = foods;
		}
		public Location getLocation() {
			return location;
		}
		public void setLocation(Location location) {
			this.location = location;
		}
		@Override
		public String toString() {
			return "FoodDetailEntity [id=" + id + ", email=" + username + ", contact=" + contact + ", description="
					+ description + ", image=" + image + ", currentTime=" + currentTime + ", address=" + address
					+ ", foods=" + foods + ", location=" + location + "]";
		}
	   
		
	    
	    
}
