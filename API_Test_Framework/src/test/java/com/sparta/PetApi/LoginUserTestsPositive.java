package com.sparta.PetApi;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;

public class LoginUserTestsPositive extends AbstractApi{

    private static Response response;
    private static JSONObject responseBody;
    private static final String BASE_URI = AppConfig.getBaseUri();
    private static final String PATH = AppConfig.getUserLoginPath();
    private static final String USERNAME = "Conner";
    private static final String PASSWORD = "1234";

    @BeforeAll
    public static void beforeAll(){
        response = RestAssured
                .given(UserUtils.getRequestForLogin(
                        BASE_URI,
                        PATH,
                        USERNAME,
                        PASSWORD
                ))
                .when()
                    .get()
                .thenReturn();

    }

    @Test
    @DisplayName("User login status code is 200")
    void userLogin_CheckStatusCode(){
        MatcherAssert.assertThat(response.statusCode(), is(200));
    }

    @Test
    @DisplayName("Check User Login response string contains \"Logged in user\"")
    void userLogin_CheckStringResponse(){
        MatcherAssert.assertThat(response.asString().contains("Logged in user session:"), is(true));
    }

}
