package com.sparta.PetApi.PetTests;

import com.sparta.PetApi.AbstractApiTests;
import com.sparta.PetApi.AppConfig;
import com.sparta.PetApi.utilities.PetUtils;
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

public class FindPetByIdTests extends AbstractApiTests {
    private static int validPetID = 10;
    private static String invalidPetID = "sheesh101";

    @BeforeAll
    static void beforeAll(){
        response = PetUtils.getPetByID(validPetID);
        responseBody = parseResponseToJsonObject(response);
        invalidResponse = PetUtils.getPetByID(invalidPetID);
        invalidResponseBody = parseResponseToJsonObject(invalidResponse);
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
