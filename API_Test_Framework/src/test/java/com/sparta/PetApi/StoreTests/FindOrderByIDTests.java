package com.sparta.PetApi.StoreTests;

import com.sparta.PetApi.AbstractApiTests;
import com.sparta.PetApi.AppConfig;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class FindOrderByIDTests extends AbstractApiTests {
    private static Response response;
    private static JSONObject responseBody;
    private static Response invalidResponse;
    private static JSONObject invalidResponseBody;
    private static int validOrderID = 10;
    private static String invalidOrderID = "cookie";

    @BeforeAll
    public static void beforeAll(){
        response =  RestAssured.given(getOrderByIDSpecification(AppConfig.getBaseUri(), AppConfig.getStoreByOrderIdPath(), AppConfig.getToken(), validOrderID))
                .when().get().thenReturn();
        responseBody = parseResponseToJsonObject(response);

        invalidResponse = RestAssured.given(getOrderByIDSpecification(AppConfig.getBaseUri(), AppConfig.getStoreByOrderIdPath(), AppConfig.getToken(), invalidOrderID))
                .when().get().thenReturn();
        invalidResponseBody = parseResponseToJsonObject(invalidResponse);
    }

    public static <T> RequestSpecification getOrderByIDSpecification(String baseUri, String path, String token, T orderID) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setBasePath(path)
                .addHeaders(Map.of(
                        "Authorization", token
                ))
                .addPathParams(Map.of(
                        "orderID", orderID
                ))
                .build();
    }

    @Test
    @DisplayName("Given a valid order ID, when sending GET request to /store/order/{orderID}, return status code 200")
    void testValidID_GetOrderByID(){
        MatcherAssert.assertThat(response.statusCode(), Matchers.is(200));
    }

    @Test
    @DisplayName("Given an invalid order ID, when sending GET request to /store/order/{orderID}, return status code 404")
    void testInvalidID_GetOrderByID(){
        MatcherAssert.assertThat(invalidResponse.statusCode(), Matchers.is(400));
    }
}
