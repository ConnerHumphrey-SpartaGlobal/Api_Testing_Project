package com.sparta.PetApi.StoreTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.PetApi.AbstractApiTests;
import com.sparta.PetApi.AppConfig;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;


public class PlaceOrderTests extends AbstractApiTests {
    private static Response response;
    private static JSONObject responseBody;
    private static Response invalidResponse;
    private static JSONObject invalidResponseBody;



    @BeforeAll
    public static void beforeAll() throws JsonProcessingException {
        initializeRequestBodyMap();
        response = RestAssured.given(postPlaceOrderSpecification(AppConfig.getBaseUri(), AppConfig.getStoreOrderPath(), AppConfig.getToken(), convertMapToJson(requestBody)))
                .when().post().thenReturn();
        responseBody = parseResponseToJsonObject(response);
        responseBody.clear();
        initializeInvalidRequestBodyMap();
        invalidResponse = RestAssured.given(postPlaceOrderSpecification(AppConfig.getBaseUri(), AppConfig.getStoreOrderPath(), AppConfig.getToken(), convertMapToJson(requestBody)))
                .when().post().thenReturn();
        invalidResponseBody = parseResponseToJsonObject(invalidResponse);
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

    public static void initializeRequestBodyMap(){
        requestBody.put("id", 12);
        requestBody.put("petId", 10);
        requestBody.put("quantity", 7);
        requestBody.put("shipDate", "2024-06-11T12:42:09.959Z");
        requestBody.put("status", "approved");
        requestBody.put("complete", false);
    }

    public static void initializeInvalidRequestBodyMap(){
        requestBody.put("Speed", 100);
        requestBody.put("Clarity", "Clear");
        requestBody.put("cake", true);
    }

    @Test
    @DisplayName("Given a valid request body, sending a post request to /store/order path successfully returns status code 200.")
    void testValidRequestBodyOrderPet_statusCode(){
        MatcherAssert.assertThat(response.statusCode(), Matchers.is(200));
    }

    // Notes to self: Updates the resource if created used than once before deleting, not good.

    @Test
    @DisplayName("Given an invalid request body, sending a post request to /store/order path sends status code 405")
    void testInvalidRequestBodyOrderPet_statusCode(){
        MatcherAssert.assertThat(invalidResponse.statusCode(), Matchers.is(405));
    }
    // Note to self: bad http status code, should be 400
    // returns 200
}
