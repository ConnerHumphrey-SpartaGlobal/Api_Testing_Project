package com.sparta.PetApi.PetTests;

import com.sparta.PetApi.AbstractApi;
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

public class FindPetByIdTests extends AbstractApi {

    private static Response response;
    private static JSONObject responseBody;
    private static Response invalidResponse;
    private static JSONObject invalidResponseBody;

    private static int validPetID = 10;
    private static String invalidPetID = "sheesh101";

    @BeforeAll
    static void beforeAll(){
        response = RestAssured.given(getPetByIDSpecification(AppConfig.getBaseUri(), AppConfig.getPetByIdPath(), AppConfig.getToken(), validPetID))
                .when().get().thenReturn();
        responseBody = parseResponseToJsonObject(response);

        invalidResponse = RestAssured.given(getPetByIDSpecification(AppConfig.getBaseUri(), AppConfig.getPetByIdPath(), AppConfig.getToken(), invalidPetID))
                .when().get().thenReturn();
        invalidResponseBody = parseResponseToJsonObject(invalidResponse);
    }

    public static <T> RequestSpecification getPetByIDSpecification(String baseUri, String path, String token, T petID) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setBasePath(path)
                .addHeaders(Map.of(
                        "Authorization", token
                ))
                .addPathParams(Map.of(
                        "petID", petID
                ))
                .build();
    }


   @Test
   @DisplayName("Given a valid ID pet/{petID} path returns pet by id successfuly, with a code 200")
   void testValidIdPetByID(){
       MatcherAssert.assertThat(response.statusCode(), Matchers.is(200));
   }

   @Test
   @DisplayName("Given an invalid ID pet/{petID} path returns a 400 satus code")
    void testInvalidIdPetByID(){
        MatcherAssert.assertThat(invalidResponse.statusCode(), Matchers.is(400));
   }

}
