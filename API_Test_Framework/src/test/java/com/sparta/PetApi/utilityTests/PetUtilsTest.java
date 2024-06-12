package com.sparta.PetApi.utilityTests;

import com.sparta.PetApi.Pojos.Pet;
import com.sparta.PetApi.utilities.PetUtils;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
    public void testUpdatePetName() {
        int petId = 10;
        String newName = "newName";
        Response mockedResponse = mock(Response.class);
        PetUtils utilsSpy = Mockito.spy(new PetUtils());
        doReturn(mockedResponse).when(utilsSpy).updatePetName(petId, newName);

        Response response = PetUtils.updatePetName(petId, newName);
        assertNotNull(response);
    }
}
