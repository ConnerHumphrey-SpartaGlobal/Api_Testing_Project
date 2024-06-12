package com.sparta.PetApi.PetTests;


import com.sparta.PetApi.AbstractApiTests;
import com.sparta.PetApi.Pojos.Pet;
import com.sparta.PetApi.utilities.PetUtils;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class UpdatePetTests extends AbstractApiTests {
    private static final String NEW_NAME = "Nish";

    private static String oldName;
    private static String newName;

    @BeforeAll
    static void beforeAll(){
        Pet pet = PetUtils.createPetPOJO();
        int ID = (Integer) pet.getId();

        response = PetUtils.addPet(pet);
        oldName = response.getBody().jsonPath().getString("name");

        Response getResponse = PetUtils.getPetByID(ID);

        response = PetUtils.updatePetName(getResponse, NEW_NAME);
        newName = response.getBody().jsonPath().getString("name");
    }

    @Test
    @DisplayName("Does name update")
    void doesNameUpdate(){
        assertThat(newName, allOf(is(NEW_NAME), not(oldName)));
    }
}
