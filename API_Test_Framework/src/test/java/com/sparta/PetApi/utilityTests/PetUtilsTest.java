package com.sparta.PetApi.utilityTests;

import com.sparta.PetApi.Pojos.Pet;
import com.sparta.PetApi.utilities.PetUtils;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PetUtilsTest {

    @Test
    public void testCreatePetPOJO() {
        Pet pet = PetUtils.createPetPOJO();
        assertNotNull(pet);
        assertEquals(10, pet.getId());
        assertEquals("doggie", pet.getName());
    }

    @Test
    public void testDeletePet() {
        int petId = 10;
        Response mockedResponse = mock(Response.class);
        PetUtils utilsSpy = Mockito.spy(new PetUtils());
        doReturn(mockedResponse).when(utilsSpy).deletePet(petId);

        Response response = PetUtils.deletePet(petId);
        assertNotNull(response);
    }
    

    @Test
    @DisplayName("Pet not existing returns false")
    void isPetFoundReturnsFalse(){
        ResponseBody mockBody = mock(ResponseBody.class);
        Response mockResponse = mock(Response.class);
        when(mockResponse.getBody()).thenReturn(mockBody);
        when(mockBody.asString()).thenReturn("Pet not found");

        assertThat(PetUtils.isPetFound(mockResponse), is(false));
    }

    @Test
    @DisplayName("Name is updated correctly and not dependant on the original response")
    void updateNameIsCorrect(){
        ResponseBody mockBody = mock(ResponseBody.class);
        when(mockBody.as(Pet.class)).thenReturn(PetUtils.createPetPOJO());

        Response mockResponse = mock(Response.class);
        when(mockResponse.getBody()).thenReturn(mockBody);


        Response finalResponse = PetUtils.updatePetName(mockResponse,"TestName");

        Pet returnedPet = finalResponse.getBody().as(Pet.class);

        assertThat(returnedPet.getName(), is("TestName"));
    }
}
