package com.sparta.PetApi.PetTests;

import com.sparta.PetApi.AppConfig.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeAll;

import java.util.Map;

public class FindPetByIdTests {

    private static Response response;
    private static JSONObject responseBody;
    private static int petID = 10;
    @BeforeAll
    static void beforeAll(){
        response = RestAssured.given().when().get().thenReturn();
    }

    public static RequestSpecification getSpecificGithubCommentRequestSpec(String baseUri, String path, String token, int petID) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setBasePath(path)
                .addHeaders(Map.of(
                        "Authorization", "Bearer " + token
                ))
                .addPathParams(Map.of(
                        "comment_id", petID
                ))
                .build();
    }

}
