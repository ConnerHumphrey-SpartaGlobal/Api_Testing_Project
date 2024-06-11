package com.sparta.PetApi.PetTests;


import com.sparta.PetApi.utilities.PetUtils;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;


public class DeletePetTests {
    private static Response response;
    private static int ID;

    @BeforeAll
    static void beforeAll(){
        ID = 1; //Create pet here and get it's ID
        response = PetUtils.deletePet(ID);
    }


    @Test
    @DisplayName("Got correct status code")
    void isCorrectStatusCode(){
        assertThat(response.getStatusCode(), anyOf(is(200), is(204)));
    }

    @Test
    @DisplayName("Delete pet that doesn't exist")
    void deleteNonExist(){
        Response badResponse = PetUtils.deletePet(ID);
        assertThat(badResponse.getStatusCode(), is(400));
    }

    @Test
    @DisplayName("Get deleted pet response")
    void petNoLongerExists(){
        //Get request for ID
        // Assert that body says "Pet not found"
        assertThat("Not implemented", is("Fail test"));
    }
}
