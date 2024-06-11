package com.sparta.PetApi.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.PetApi.Pojos.Category;
import com.sparta.PetApi.Pojos.Pet;
import com.sparta.PetApi.Pojos.TagsItem;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import com.sparta.PetApi.AppConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PetUtils {
    public static Pet createPetPOJO(){
        Category category = new Category();
        category.setId(1);
        category.setName("Dogs");

        TagsItem tag = new TagsItem();
        tag.setId(0);
        tag.setName("string");

        List<String> photoUrls = new ArrayList<>();
        photoUrls.add("string");

        List<TagsItem> tags = new ArrayList<>();
        tags.add(tag);


        Pet pet = new Pet();
        pet.setId(10);
        pet.setName("doggie");
        pet.setCategory(category);
        pet.setPhotoUrls(photoUrls);
        pet.setTags(tags);
        pet.setStatus("available");
        return pet;
    }

    public static RequestSpecification postRequestAddPet(Pet pet) {
        ObjectMapper objectMapper = new ObjectMapper();
        String petJson = "";
        try {
            petJson = objectMapper.writeValueAsString(pet);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new RequestSpecBuilder()
                .setBaseUri(AppConfig.getBaseUri())
                .setBasePath(AppConfig.getPetPath())
                .addHeaders(Map.of(
                        "Accept", "application/json"
                ))
                .setContentType(ContentType.JSON)
                .setBody(petJson)
                .build();
    }

    public static RequestSpecification getPetByIDSpec(int ID) {
        return new RequestSpecBuilder()
                .setBaseUri(AppConfig.getBaseUri())
                .setBasePath(AppConfig.getPetByIdPath())
                .addPathParam("petID", ID)
                .build();
    }

    public static RequestSpecification updatePetSpec(Pet pet) {
        ObjectMapper objectMapper = new ObjectMapper();
        String petJson = "";
        try {
            petJson = objectMapper.writeValueAsString(pet);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new RequestSpecBuilder()
                .setBaseUri(AppConfig.getBaseUri())
                .setBasePath(AppConfig.getPetPath())
                .addHeaders(Map.of(
                        "Accept", "application/json"
                ))
                .setContentType(ContentType.JSON)
                .setBody(petJson)
                .build();
    }


    public static Response deletePet(int id){
        RequestSpecification spec = new RequestSpecBuilder()
                .setBaseUri(AppConfig.getBaseUri())
                .addHeader("api_key", AppConfig.getToken())
                .setBasePath(AppConfig.getPetByIdPath())
                .addPathParam("petID", id)
                .build();
        return RestAssured
                .given(spec)
                .when()
                .delete()
                .thenReturn();
    }

    public static Response addPet(Pet pet){
        return RestAssured
                .given(postRequestAddPet(pet))
                .when()
                .post()
                .thenReturn();
    }

    public  static Response getPetById(int ID){
        return RestAssured
                .given(getPetByIDSpec(ID))
                .when().get().thenReturn();
    }


    public static Response updatePetName(int ID, String newName){
        Response response = getPetById(ID);
        Pet pet = response.body().as(Pet.class);

        pet.setName(newName);

        return RestAssured
                .given(updatePetSpec(pet))
                .when()
                .put()
                .thenReturn();
   }

    public static RequestSpecification postRequestAddPet(String baseUri, String path, String petId, String petName, String petStatus) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setBasePath(path)
                .addPathParam("petID", petId)
                .addQueryParam("name", petName)
                .addQueryParam("status", petStatus)
                .addHeaders(Map.of(
                        "Accept", "application/json"
                ))
                .setContentType(ContentType.JSON)
                .build();
    }


}
