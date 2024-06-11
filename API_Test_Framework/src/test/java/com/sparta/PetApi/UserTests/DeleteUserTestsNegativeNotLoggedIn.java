package com.sparta.PetApi.UserTests;

import com.sparta.PetApi.AppConfig;
import com.sparta.PetApi.utilities.UserUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;

public class DeleteUserTestsNegativeNotLoggedIn {
    private static Response response;
    private static final String BASE_URI = AppConfig.getBaseUri();
    private static final String DELETE_USER_PATH = AppConfig.getUserByUsernamePath();
    private static final String USERNAME = "Jeff";

    @BeforeAll
    public static void beforeAll(){


        //deleting user
        response = RestAssured
                .given(UserUtils.deleteRequestForUser(BASE_URI,
                        DELETE_USER_PATH,
                        USERNAME))
                .when()
                .delete()
                .thenReturn();
    }

    @Test
    @DisplayName("User deletion status code is 400")
    void userDeletion_CheckStatusCode(){
        MatcherAssert.assertThat(response.statusCode(), is(400));
    }
}
