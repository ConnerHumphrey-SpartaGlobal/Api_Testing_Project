package com.sparta.PetApi.StoreTests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeAll;

import java.util.Map;

public class PlaceOrderTests {
    private static Response response;
    private static JSONObject responseBody;
    private static Response invalidResponse;
    private static JSONObject invalidResponseBody;



    @BeforeAll
    public static void beforeAll(){

    }

    public static RequestSpecification postPlaceOrderSpecification(String baseUri, String path, String token, String jsonBody) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setBasePath(path)
                .addHeaders(Map.of(
                        "Authorization", token
                ))
                .addPathParams(Map.of(

                ))
                .setContentType(ContentType.JSON)
                .setBody(jsonBody)
                .build();
    }

}
