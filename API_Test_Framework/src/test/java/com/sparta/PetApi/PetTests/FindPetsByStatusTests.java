package com.sparta.PetApi.PetTests;

import com.sparta.PetApi.AppConfig;
import com.sparta.PetApi.utilities.PetUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.sparta.PetApi.AbstractApiTests.parseResponseToJsonObject;

public class FindPetsByStatusTests {
    private static Response availableResponse;
    private static Response pendingResponse;
    private static Response soldResponse;
    private static Response invalidResponse;
    private static final String validPetStatusAvailable = "available";
    private static final String validPetStatusPending = "pending";
    private static final String validPetStatusSold = "sold";
    private static final String invalidPetStatus = "unavailable";
    private static final String BASE_URI = AppConfig.getBaseUri();
    private static final String POST_PATH = AppConfig.getPetByStatPath();

    @BeforeAll
    static void beforeAll(){
        availableResponse = RestAssured.given(
                PetUtils.getPetByStatusSpec(
                        BASE_URI,
                        POST_PATH,
                        validPetStatusAvailable
                ))
                .when()
                .get()
                .thenReturn();

        pendingResponse = RestAssured.given(
                        PetUtils.getPetByStatusSpec(
                                BASE_URI,
                                POST_PATH,
                                validPetStatusPending
                        ))
                .when()
                .get()
                .thenReturn();

        soldResponse = RestAssured.given(
                        PetUtils.getPetByStatusSpec(
                                BASE_URI,
                                POST_PATH,
                                validPetStatusSold
                        ))
                .when()
                .get()
                .thenReturn();


        invalidResponse = RestAssured.given(
                PetUtils.getPetByStatusSpec(
                        BASE_URI,
                        POST_PATH,
                        invalidPetStatus
                ))
                .when()
                .get()
                .thenReturn();
    }

    @Test
    @DisplayName("Validate the Available response status code")
    void validateAvailableResponseStatusCode() {
        MatcherAssert.assertThat(availableResponse.getStatusCode(), Matchers.is(200));
    }

    @Test
    @DisplayName("Validate the Pending response status code")
    void validatePendingResponseStatusCode() {
        MatcherAssert.assertThat(pendingResponse.getStatusCode(), Matchers.is(200));
    }

    @Test
    @DisplayName("Validate the Sold response status code")
    void validateSoldResponseStatusCode() {
        MatcherAssert.assertThat(soldResponse.getStatusCode(), Matchers.is(200));
    }

    @Test
    @DisplayName("Validate the Unavailable response status code")
    void validateResponseStatusCode() {
        MatcherAssert.assertThat(invalidResponse.getStatusCode(), Matchers.is(400));
    }
}
