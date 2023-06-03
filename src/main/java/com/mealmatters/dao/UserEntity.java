package com.mealmatters.dao;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Document(collection = "users")
public class UserEntity {

	@Id
	private String usesId;
	@NotNull
	private String username;
	
	@NotNull
	private String name;
	@NotNull
	private String password;
	@NotNull
	private String contact;
	public UserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserEntity(String usesId, @NotNull String username, @NotNull String name, @NotNull String password,
			@NotNull String contact) {
		super();
		this.usesId = usesId;
		this.username = username;
		this.name = name;
		this.password = password;
		this.contact = contact;
	}
	public String getUsesId() {
		return usesId;
	}
	public void setUsesId(String usesId) {
		this.usesId = usesId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}

	
	
	
	
}
