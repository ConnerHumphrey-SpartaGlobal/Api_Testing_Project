package com.sparta.PetApi.UserTests;

import com.sparta.PetApi.AbstractApiTests;
import com.sparta.PetApi.AppConfig;
import com.sparta.PetApi.utilities.UserUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;

public class DeleteUserTestsNegativeLoggedIn extends AbstractApiTests {
    private static Response response;
    private static final String BASE_URI = AppConfig.getBaseUri();
    private static final String DELETE_USER_PATH = AppConfig.getUserByUsernamePath();
    private static final String LOGIN_PATH = AppConfig.getUserLoginPath();
    private static final String LOGOUT_PATH = AppConfig.getUserLogoutPath();
    private static final String USERNAME = "Jeff";
    private static final String USERNAME_TO_DELETE = "wrong";
    private static final String PASSWORD = "1234";

    @BeforeAll
    public static void beforeAll(){
        //Logging in before test
        RestAssured
                .given(UserUtils.getRequestForLogin(
                        BASE_URI,
                        LOGIN_PATH,
                        USERNAME,
                        PASSWORD
                ))
                .when()
                .get()
                .then()
                .assertThat()
                .statusCode(200);
        //deleting user
        response = RestAssured
                .given(UserUtils.deleteRequestForUser(BASE_URI,
                        DELETE_USER_PATH,
                        USERNAME_TO_DELETE))
                .when()
                .delete()
                .thenReturn();
    }

    @Test
    @DisplayName("User deletion status code is 404")
    void userDeletion_CheckStatusCode(){
        MatcherAssert.assertThat(response.statusCode(), is(404));
    }

    @AfterAll
    public static void afterAll(){
        //logging out
        RestAssured
                .given(UserUtils.getRequestForLogout(
                        BASE_URI,
                        LOGOUT_PATH))
                .when()
                .get()
                .then()
                .assertThat()
                .statusCode(200);
    }

}
