package com.sparta.PetApi.utilityTests;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.sparta.PetApi.utilities.StoreUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StoreUtilsTests {

    private static WireMockServer wireMockServer;

    @BeforeAll
    public static void setUp() {
        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().dynamicPort());
        wireMockServer.start();
        RestAssured.port = wireMockServer.port();
    }

    @AfterAll
    public static void tearDown() {
        wireMockServer.stop();
    }

    @Test
    public void testGetOrderByIDSpecification() {
        String baseUri = "http://localhost:" + wireMockServer.port();
        String path = "/store/order/{orderID}";
        String token = "fake-token";
        int orderID = 10;

        RequestSpecification spec = StoreUtils.getOrderByIDSpecification(baseUri, path, token, orderID);

        wireMockServer.stubFor(WireMock.get(urlPathEqualTo("/store/order/10"))
                .withHeader("Authorization", equalTo("fake-token"))
                .willReturn(aResponse().withStatus(200)));

        Response response = RestAssured.given(spec).get();
        assertNotNull(response);
        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void testGetOrderByID() {
        wireMockServer.stubFor(WireMock.get(urlPathEqualTo("/store/order/10"))
                .withHeader("Authorization", equalTo("fake-token"))
                .willReturn(aResponse().withStatus(200)));

        Response response = StoreUtils.getOrderByID(10);
        assertNotNull(response);
        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void testPostPlaceOrderSpecification() {
        String baseUri = "http://localhost:" + wireMockServer.port();
        String path = "/store/order";
        String token = "fake-token";
        String jsonBody = "{\"id\":10,\"petId\":10,\"quantity\":7,\"shipDate\":\"2024-06-11T12:42:09.959Z\",\"status\":\"approved\",\"complete\":false}";

        RequestSpecification spec = StoreUtils.postPlaceOrderSpecification(baseUri, path, token, jsonBody);

        wireMockServer.stubFor(post(urlPathEqualTo("/store/order"))
                .withHeader("Authorization", equalTo("fake-token"))
                .withRequestBody(equalTo(jsonBody))
                .willReturn(aResponse().withStatus(200)));

        Response response = RestAssured.given(spec).post();
        assertNotNull(response);
        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void testPlaceOrder() {
        String jsonBody = "{\"id\":10,\"petId\":10,\"quantity\":7,\"shipDate\":\"2024-06-11T12:42:09.959Z\",\"status\":\"approved\",\"complete\":false}";

        wireMockServer.stubFor(post(urlPathEqualTo("/store/order"))
                .withHeader("Authorization", equalTo("fake-token"))
                .withRequestBody(equalTo(jsonBody))
                .willReturn(aResponse().withStatus(200)));

        Response response = StoreUtils.placeOrder(jsonBody);
        assertNotNull(response);
        assertEquals(200, response.getStatusCode());
    }
}
