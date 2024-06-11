package com.sparta.PetApi.utilities;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import com.sparta.PetApi.AppConfig;

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


}
