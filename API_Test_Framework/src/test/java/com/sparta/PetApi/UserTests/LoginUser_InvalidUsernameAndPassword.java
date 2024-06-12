package com.sparta.PetApi.UserTests;

import com.sparta.PetApi.AbstractApiTests;
import com.sparta.PetApi.AppConfig;
import com.sparta.PetApi.utilities.UserUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;

public class LoginUser_InvalidUsernameAndPassword extends AbstractApiTests {

    private static final String BASE_URI = AppConfig.getBaseUri();
    private static final String LOGIN_PATH = AppConfig.getUserLoginPath();
    private static final String LOGOUT_PATH = AppConfig.getUserLogoutPath();
    private static final String USERNAME = " ";
    private static final String PASSWORD = " ";


    @BeforeAll
    public static void beforeAll(){
        response = RestAssured
                .given(UserUtils.getRequestForLogin(
                        BASE_URI,
                        LOGIN_PATH,
                        USERNAME,
                        PASSWORD
                ))
                .when()
                .get()
                .thenReturn();

    }

    @Test
    @DisplayName("User login status code is 400")
    void userLogin_CheckStatusCode(){
        MatcherAssert.assertThat(response.statusCode(), is(400));
    }

    @Test
    @DisplayName("Check User Login response string contains \"Logged in user\"")
    void userLogin_CheckStringResponse(){
        MatcherAssert.assertThat(response.asString().contains("Logged in user session:"), is(false));
    }
}
