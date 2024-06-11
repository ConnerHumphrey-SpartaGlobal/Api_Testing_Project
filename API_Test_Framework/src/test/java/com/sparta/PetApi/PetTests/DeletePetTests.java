package com.sparta.PetApi.PetTests;


import com.sparta.PetApi.Pojos.Pet;
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
        Pet pet = PetUtils.createPetPOJO();
        ID = (int) pet.getId();
        PetUtils.addPet(pet);
        response = PetUtils.deletePet(ID);
    }


    @Test
    @DisplayName("Got correct status code")
    void isCorrectStatusCode(){
        assertThat(response.getStatusCode(), anyOf(is(200), is(204)));
    }

    @Test
    @DisplayName("Delete pet that doesn't exist returns 400 response code")
    void deleteNonExist(){
        Response badResponse = PetUtils.deletePet(ID);
        assertThat(badResponse.getStatusCode(), is(400));
    }

    @Test
    @DisplayName("Get deleted pet response")
    void petNoLongerExists(){
        Response badResponse = PetUtils.getPetById(ID);
        assertThat(badResponse.getBody().asString(), is("Pet not found"));
    }
}
