package com.sparta.PetApi.UserTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.PetApi.AbstractApiTests;
import com.sparta.PetApi.AppConfig;
import com.sparta.PetApi.Pojos.User;
import com.sparta.PetApi.utilities.UserUtils;
import io.restassured.RestAssured;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;

public class CreateUser_LoggedInFirstTests extends AbstractApiTests {

    private static final String BASE_URI = AppConfig.getBaseUri();
    private static final String CREATE_USER_PATH = AppConfig.getUserPath();
    private static final String LOGIN_PATH = AppConfig.getUserLoginPath();
    private static final String LOGOUT_PATH = AppConfig.getUserLogoutPath();
    private static final String USERNAME = "Conner";
    private static final String PASSWORD = "1234";
    private static  User defaultUser = User.getDefaultUser();

    @BeforeAll
    public static void beforeAll() throws JsonProcessingException {
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

        response = RestAssured
                .given(UserUtils.postRequestSpecForCreatingUser(
                        BASE_URI,
                        CREATE_USER_PATH,
                        defaultUser))
                .when()
                .post()
                .thenReturn();

        responseBody = parseResponseToJsonObject(response);
    }

    @Test
    @DisplayName("User creation status code is 200")
    void userCreation_CheckStatusCode(){
        MatcherAssert.assertThat(response.statusCode(), is(200));
    }

    @Test
    @DisplayName("User creation outputs correct ID in body")
    void userCreation_CheckBody_ForID(){
        MatcherAssert.assertThat(responseBody.get("id").toString(), is("1"));
    }

    @Test
    @DisplayName("User creation outputs correct Name in body")
    void userCreation_CheckBody_ForName(){
        MatcherAssert.assertThat(responseBody.get("firstName").toString(), is("Conner"));
    }

    @Test
    @DisplayName("User creation outputs correct Name in body")
    void userCreation_CheckBody_ForUsername(){
        MatcherAssert.assertThat(responseBody.get("username").toString(), is("ConnerHumphrey"));
    }

    @AfterAll
    public static void afterAll(){
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
