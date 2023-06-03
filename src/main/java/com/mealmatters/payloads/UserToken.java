package com.mealmatters.payloads;


public class UserToken {

	String email;
	String token;
	boolean isLogin;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public UserToken() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserToken(String email, String token, boolean isLogin) {
		super();
		this.email= email;
		this.token = token;
		this.isLogin = isLogin;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public boolean isLogin() {
		return isLogin;
	}
	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}
	
	
}
