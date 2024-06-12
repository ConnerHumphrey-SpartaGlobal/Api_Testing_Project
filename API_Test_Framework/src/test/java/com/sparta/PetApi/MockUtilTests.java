package com.sparta.PetApi;

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
}
