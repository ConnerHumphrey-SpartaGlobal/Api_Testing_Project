package com.sparta.PetApi.Pojos;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class User{

	private int id;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String phone;
	private int userStatus;


	public User(int id, String username, String firstName, String lastName, String email, String password, String phone, int userStatus) {
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.userStatus = userStatus;
	}

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


	public String getUsername(){
		return username;
	}

	@Override
	public String toString() {
		return "{" +
				"\"id\":" + id + "," +
				"\"username\":\"" + username + "\"," +
				"\"firstName\":\"" + firstName + "\"," +
				"\"lastName\":\"" + lastName + "\"," +
				"\"email\":\"" + email + "\"," +
				"\"password\":\"" + password + "\"," +
				"\"phone\":" + phone + "," +
				"\"userStatus\":" + userStatus +
				"}";
	}

	public static User getDefaultUser(){
		return new User(1, "ConnerHumphrey", "Conner","Humphrey", "Email", "Password", "1", 1);
	}

}
