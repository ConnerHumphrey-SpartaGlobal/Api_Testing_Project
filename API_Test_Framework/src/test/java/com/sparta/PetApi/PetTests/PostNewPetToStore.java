package com.sparta.PetApi.PetTests;

import com.sparta.PetApi.AbstractApiTests;
import com.sparta.PetApi.AppConfig;
import com.sparta.PetApi.Pojos.Category;
import com.sparta.PetApi.Pojos.Pet;
import com.sparta.PetApi.Pojos.TagsItem;
import com.sparta.PetApi.utilities.PetUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PostNewPetToStore extends AbstractApiTests {
    private static Response response;
    private static JSONObject responseBody;
    private static int ID;

    private static Pet postBody;

    @BeforeAll
    public static void beforeAll() {
        Pet pet = PetUtils.createPetPOJO();
        ID = pet.getId();
        response = PetUtils.addPet(pet);
    }

    @AfterAll
    static void afterAll(){
        PetUtils.deletePet(ID);
    }

    @Test
    @DisplayName("Validate the response status code")
    void validateResponseStatusCode() {
        MatcherAssert.assertThat(response.getStatusCode(), Matchers.is(200));
    }
}
