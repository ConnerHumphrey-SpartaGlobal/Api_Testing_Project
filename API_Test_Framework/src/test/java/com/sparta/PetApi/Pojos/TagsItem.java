package com.sparta.PetApi.Pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sparta.PetApi.utilities.JsonSerializable;

public class TagsItem implements JsonSerializable {

	@JsonProperty("name")
	private String name;

	@JsonProperty("id")
	private int id;

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}
}