package com.sparta.PetApi.PetTests;

import com.sparta.PetApi.AbstractApiTests;
import com.sparta.PetApi.AppConfig;
import com.sparta.PetApi.Pojos.Pet;
import com.sparta.PetApi.utilities.PetUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UpdatePetWithFormDataTests extends AbstractApiTests {
    private static final String BASE_URI = AppConfig.getBaseUri();
    private static final String POST_PATH = AppConfig.getPetByIdPath();

    @BeforeAll
    public static void beforeAll() {
        String petId = "1";
        String petName = "doggie";
        String petStatus = "sold";

        response = RestAssured
                .given(PetUtils.postRequestAddPet(BASE_URI, POST_PATH, petId, petName, petStatus))
                .get()
                .thenReturn();

        responseBody = parseResponseToJsonObject(response);

        String invalidPetId = "10000";

        invalidResponse = RestAssured
                .given(PetUtils.postRequestAddPet(BASE_URI, POST_PATH, invalidPetId, petName, petStatus))
                .get()
                .thenReturn();
    }

    @Test
    @DisplayName("Validate the response status code")
    void validateResponseStatusCode() {
        MatcherAssert.assertThat(response.getStatusCode(), Matchers.is(200));
    }

    @Test
    @DisplayName("Validate the response status code")
    void validateErrorResponseStatusCode() {
        MatcherAssert.assertThat(invalidResponse.getStatusCode(), Matchers.is(404));
    }
}
