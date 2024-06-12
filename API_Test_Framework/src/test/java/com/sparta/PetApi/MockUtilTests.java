package com.sparta.PetApi;

import com.sparta.PetApi.Pojos.Pet;
import com.sparta.PetApi.utilities.PetUtils;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockUtilTests {

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
