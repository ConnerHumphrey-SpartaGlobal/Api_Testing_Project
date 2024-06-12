package com.sparta.PetApi.UserTests;

import com.sparta.PetApi.AppConfig;
import com.sparta.PetApi.Pojos.User;
import com.sparta.PetApi.utilities.UserUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;

public class UpdateUser_IncorrectUserNameGiven {

    private static Response response;
    private static User responseUser;
    private static final String BASE_URI = AppConfig.getBaseUri();
    private static final String UPDATE_PATH = AppConfig.getUserByUsernamePath();
    private static final String CREATE_USER_PATH = AppConfig.getUserPath();
    private static final String LOGIN_PATH = AppConfig.getUserLoginPath();
    private static final String LOGOUT_PATH = AppConfig.getUserLogoutPath();
    private static final String USERNAME = "ConnerHumphre";
    private static final String PASSWORD = "1234";
    private static User defaultUser = User.getDefaultUser();
    private static User modifiedUser = User.getModifiedDefaultUser();


    @BeforeAll
    public static void beforeAll(){
        //Logging in
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

        //creating user
        RestAssured
                .given(UserUtils.postRequestSpecForCreatingUser(
                        BASE_URI,
                        CREATE_USER_PATH,
                        defaultUser))
                .when()
                .post()
                .then()
                .assertThat()
                .statusCode(200);

        //Modifying the user
        response = RestAssured
                .given(UserUtils.putRequestForUser(
                        BASE_URI,
                        UPDATE_PATH,
                        USERNAME,
                        modifiedUser))
                .when()
                .put()
                .thenReturn();

    }

    @Test
    @DisplayName("User Modification status code is 404")
    void userModification_CheckStatusCode(){
        MatcherAssert.assertThat(response.statusCode(), is(404));
    }

    @Test
    @DisplayName("Testing correct error message in response")
    void userModificationCorrectErrorMessage(){
        MatcherAssert.assertThat(response.asString().contains("User not found"), is(true));
    }

    @AfterAll
    public static void afterAll(){
        //logging out after logging in to maintain testability

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
