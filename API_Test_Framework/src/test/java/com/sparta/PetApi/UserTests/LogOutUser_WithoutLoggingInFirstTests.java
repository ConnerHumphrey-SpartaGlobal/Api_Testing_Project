package com.sparta.PetApi.UserTests;

import com.sparta.PetApi.AbstractApiTests;
import com.sparta.PetApi.AppConfig;
import com.sparta.PetApi.utilities.UserUtils;
import io.restassured.RestAssured;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class LogOutUser_WithoutLoggingInFirstTests extends AbstractApiTests {
    private static final String BASE_URI = AppConfig.getBaseUri();
    private static final String LOGOUT_PATH = AppConfig.getUserLogoutPath();

    @BeforeAll
    public static void beforeAll(){
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
        MatcherAssert.assertThat(response.statusCode(), is(not(200)));
    }

    @Test
    @DisplayName("Check User Login response string contains \"User logged out\"")
    void userLogin_CheckStringResponse(){
        MatcherAssert.assertThat(response.asString().contains("User logged out"), is(false));
    }
}
