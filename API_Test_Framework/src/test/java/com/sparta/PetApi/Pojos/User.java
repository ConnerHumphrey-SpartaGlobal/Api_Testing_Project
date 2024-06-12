package com.sparta.PetApi.Pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sparta.PetApi.utilities.JsonSerializable;

public class User implements JsonSerializable {

	@JsonProperty("id")
	private int id;

	@JsonProperty("username")
	private String username;

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("email")
	private String email;

	@JsonProperty("password")
	private String password;

	@JsonProperty("phone")
	private String phone;

	@JsonProperty("userStatus")
	private int userStatus;


	public String getFirstName(){
		return firstName;
	}


	public String getLastName(){
		return lastName;
	}


	public String getPassword(){
		return password;
	}


	public int getUserStatus(){
		return userStatus;
	}


	public String getPhone(){
		return phone;
	}


	public int getId(){
		return id;
	}


	public String getEmail(){
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername(){
		return username;
	}

	public void setUser(int id, String username, String firstName, String lastName, String email, String password, String phone, int userStatus) {
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.userStatus = userStatus;
	}


	public static User getDefaultUser(){
		User user = new User();
		user.setUser(1, "ConnerHumphrey", "Conner","Humphrey", "Email@sparta.com", "Password", "1", 1);
		return user;
	}

	public static User getModifiedDefaultUser(){
		User modifiedUser = getDefaultUser();
		modifiedUser.setEmail("ChangedEmail@sparta.com");
		return modifiedUser;
	}
}
