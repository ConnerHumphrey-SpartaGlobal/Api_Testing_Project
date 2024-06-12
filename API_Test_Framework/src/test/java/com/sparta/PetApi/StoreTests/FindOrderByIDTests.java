package com.sparta.PetApi.StoreTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.PetApi.AbstractApiTests;
import com.sparta.PetApi.AppConfig;
import com.sparta.PetApi.Pojos.Order;
import com.sparta.PetApi.Pojos.Pet;
import com.sparta.PetApi.utilities.StoreUtils;
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
    private static int validOrderID = 10;
    private static String invalidOrderID = "cookie";
    private static Response emptyResponse;

    @BeforeAll
    public static void beforeAll(){
        response = StoreUtils.getOrderByID(validOrderID);
        responseBody = parseResponseToJsonObject(response);
        invalidResponse = StoreUtils.getOrderByID(invalidOrderID);
        invalidResponseBody = parseResponseToJsonObject(invalidResponse);
        emptyResponse = StoreUtils.getOrderByID("");
    }


    @Test
    @DisplayName("Given a valid order ID, when sending GET request to /store/order/{orderID}, return status code 200")
    void testValidID_GetOrderByID(){
        MatcherAssert.assertThat(response.statusCode(), Matchers.is(200));
    }

    @Test
    @DisplayName("Given an invalid order ID, when sending GET request to /store/order/{orderID}, return status code 400")
    void testInvalidID_GetOrderByID(){
        MatcherAssert.assertThat(invalidResponse.statusCode(), Matchers.is(400));
    }

    // another test case needed here to test 404 code
    @Test
    @DisplayName("Given an empty order ID, when sending GET request to /store/order/{orderID}, returns status code 404")
    void testEmptyID_GetOrderByID() { MatcherAssert.assertThat(emptyResponse.statusCode(), Matchers.is(404));}
    // another test case needed here to validate json body

    @Test
    @DisplayName("Given a valid Order ID, when sending GET request to /store/order/{orderID}, returns a valid json object")
    void testValidID_ValidJSONObject_GetOrderByID() throws JsonProcessingException {
        Order order = toObject(responseBody.toString(), Order.class);

        MatcherAssert.assertThat(order.getId(), Matchers.is(10));
        MatcherAssert.assertThat(order.getPetId(), Matchers.is(10));
        MatcherAssert.assertThat(order.getQuantity(), Matchers.is(7));
        MatcherAssert.assertThat(order.getStatus(), Matchers.is("approved"));
        MatcherAssert.assertThat(order.getShipDate(), Matchers.is("2024-06-11T12:42:09.959+00:00"));

    }
}
