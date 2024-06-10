package com.sparta.PetApi;

import com.sparta.PetApi.Pojos.User;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class UserUtils {

    public static RequestSpecification postRequestSpecForCreatingUser(String baseUri, String path, User userToCreate){
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setBasePath(path)
                .addHeaders(Map.of(
                        "Accept", "application/json"
                ))
                .setContentType(ContentType.JSON)
                .setBody(userToCreate.toString())
                .build();
    }


}
