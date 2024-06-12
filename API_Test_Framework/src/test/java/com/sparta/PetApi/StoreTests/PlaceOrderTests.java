package com.sparta.PetApi.StoreTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.PetApi.AbstractApiTests;
import com.sparta.PetApi.AppConfig;
import com.sparta.PetApi.utilities.StoreUtils;
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


public class PlaceOrderTests extends AbstractApiTests {

    @BeforeAll
    public static void beforeAll() throws JsonProcessingException {
        response = StoreUtils.placeOrder(StoreUtils.createOrderPOJO().toJson());
        responseBody = parseResponseToJsonObject(response);
        invalidResponse = StoreUtils.placeOrder(StoreUtils.createInvalidOrder());
        invalidResponseBody = parseResponseToJsonObject(invalidResponse);
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
