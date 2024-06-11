package com.sparta.PetApi.Pojos;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Pet{

	@JsonProperty("photoUrls")
	private List<String> photoUrls;

	@JsonProperty("name")
	private String name;

	@JsonProperty("id")
	private Object id;

	@JsonProperty("category")
	private Category category;

	@JsonProperty("tags")
	private List<TagsItem> tags;

	@JsonProperty("status")
	private String status;

	public List<String> getPhotoUrls(){
		return photoUrls;
	}

	public String getName(){
		return name;
	}

	public Object getId(){
		return id;
	}

	public Category getCategory(){
		return category;
	}

	public List<TagsItem> getTags(){
		return tags;
	}

	public String getStatus(){
		return status;
	}

	public void setPhotoUrls(List<String> photoUrls) {
		this.photoUrls = photoUrls;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(Object id) {
		this.id = id;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setTags(List<TagsItem> tags) {
		this.tags = tags;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}