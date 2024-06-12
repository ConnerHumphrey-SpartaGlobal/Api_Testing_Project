package com.sparta.PetApi.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.PetApi.Pojos.User;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class UserUtils {

    public static RequestSpecification postRequestSpecForCreatingUser(String baseUri, String path, User userToCreate) throws JsonProcessingException {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setBasePath(path)
                .addHeaders(Map.of(
                        "Accept", "application/json"
                ))
                .setContentType(ContentType.JSON)
                .setBody(userToCreate.toJson())
                .build();
    }

    public static RequestSpecification getRequestForLogin(String baseUri, String path, String username, String password){
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setBasePath(path)
                .addHeaders(Map.of(
                        "Accept", "application/json"
                ))
                .addPathParams(Map.of(
                        "username", username,
                        "password", password
                ))
                .build();

    }

    public static RequestSpecification getRequestForLogout(String baseUri, String path){
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setBasePath(path)
                .addHeaders(Map.of(
                        "Accept", "application/json"
                ))
                .build();
    }

    public static RequestSpecification deleteRequestForUser(String baseUri, String path, String username){
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setBasePath(path)
                .addHeaders(Map.of(
                        "Accept", "application/json"
                ))
                .addPathParams(Map.of(
                        "username", username
                ))
                .build();
    }

    public static RequestSpecification putRequestForUser(String baseUri, String path, String username, User modifiedUser) throws JsonProcessingException {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setBasePath(path)
                .addHeaders(Map.of(
                        "Accept", "application/json"
                ))
                .setContentType(ContentType.JSON)
                .addPathParams(Map.of(
                        "username", username
                ))
                .setBody(modifiedUser.toJson())
                .build();
    }
}
