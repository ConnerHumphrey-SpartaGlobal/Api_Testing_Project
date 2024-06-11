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

public class LogOutUserPositive {

    private static Response response;
    private static final String BASE_URI = AppConfig.getBaseUri();
    private static final String LOGIN_PATH = AppConfig.getUserLoginPath();
    private static final String LOGOUT_PATH = AppConfig.getUserLogoutPath();
    private static final String USERNAME = "Conner";
    private static final String PASSWORD = "1234";

    @BeforeAll
    public static void beforeAll(){
        RestAssured
                .given(UserUtils.getRequestForLogin(
                        BASE_URI,
                        LOGIN_PATH,
                        USERNAME,
                        PASSWORD
                ))
                .when()
                .get()
                .thenReturn();

        response = RestAssured
                .given(UserUtils.getRequestForLogout(
                        BASE_URI,
                        LOGOUT_PATH))
                .when()
                .get()
                .thenReturn();

    }

    @Test
    @DisplayName("User logout status code is 200")
    void userLogin_CheckStatusCode(){
        MatcherAssert.assertThat(response.statusCode(), is(200));
    }

    @Test
    @DisplayName("Check User Login response string contains \"User logged out\"")
    void userLogin_CheckStringResponse(){
        MatcherAssert.assertThat(response.asString().contains("User logged out"), is(true));
    }
}
