package com.sparta.PetApi.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.PetApi.AppConfig;
import com.sparta.PetApi.Pojos.Order;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class StoreUtils extends Utilities{

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

    public static <T> Response getOrderByID(T orderID){
        return RestAssured.given(getOrderByIDSpecification(AppConfig.getBaseUri(), AppConfig.getStoreByOrderIdPath(), AppConfig.getToken(), orderID))
                .when().get().thenReturn();
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

    public static Response placeOrder(String jsonBody){
        return RestAssured.given(postPlaceOrderSpecification(AppConfig.getBaseUri(), AppConfig.getStoreOrderPath(), AppConfig.getToken(), jsonBody))
                .when().post().thenReturn();
    }

    public static Order createOrderPOJO(){
        Order order = new Order();
        order.setId(12);
        order.setPetId(10);
        order.setQuantity(7);
        order.setShipDate("2024-06-11T12:42:09.959Z");
        order.setComplete(false);
        order.setStatus("approved");
        return order;
    }

    public static String createInvalidOrder() throws JsonProcessingException {
        return convertMapToJson(getInvalidRequestBodyMap());
    }

    public static Map<String, Object> getInvalidRequestBodyMap(){
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("Speed", 100);
        requestBody.put("Clarity", "Clear");
        requestBody.put("cake", true);
        return requestBody;
    }
}
