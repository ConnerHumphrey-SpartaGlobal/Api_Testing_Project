package com.sparta.PetApi.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.PetApi.Pojos.Pet;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import com.sparta.PetApi.AppConfig;

import java.util.Map;

public class PetUtils {
    
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


    public static RequestSpecification postRequestAddPet(String baseUri, String path, Pet pet) {
        ObjectMapper objectMapper = new ObjectMapper();
        String petJson = "";
        try {
            petJson = objectMapper.writeValueAsString(pet);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setBasePath(path)
                .addHeaders(Map.of(
                        "Accept", "application/json"
                ))
                .setContentType(ContentType.JSON)
                .setBody(petJson)
                .build();
    }
}
