package com.sparta.PetApi.PetTests;

import com.sparta.PetApi.AbstractApiTests;
import com.sparta.PetApi.AppConfig;
import com.sparta.PetApi.Pojos.Category;
import com.sparta.PetApi.Pojos.Pet;
import com.sparta.PetApi.Pojos.TagsItem;
import com.sparta.PetApi.utilities.PetUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PostNewPetToStore extends AbstractApiTests {
    private static Response response;
    private static Response invalidResponse;
    private static JSONObject responseBody;
    private static final String BASE_URI = AppConfig.getBaseUri();
    private static final String POST_PATH = AppConfig.getPetPath();
    private static Pet postBody;

    @BeforeAll
    public static void beforeAll() {
        Category category = new Category();
        category.setId(1);
        category.setName("Dogs");

        TagsItem tag = new TagsItem();
        tag.setId(0);
        tag.setName("string");

        List<String> photoUrls = new ArrayList<>();
        photoUrls.add("string");

        List<TagsItem> tags = new ArrayList<>();
        tags.add(tag);

        Pet pet = new Pet();
        pet.setId(10);
        pet.setName("doggie");
        pet.setCategory(category);
        pet.setPhotoUrls(photoUrls);
        pet.setTags(tags);
        pet.setStatus("available");

        response =
                RestAssured
                        .given(PetUtils.postRequestAddPet(
                                BASE_URI,
                                POST_PATH,
                                pet
                        ))
                        .when()
                        .post()
                        .thenReturn();

        pet.setId("ten");

        invalidResponse =
                RestAssured
                        .given(PetUtils.postRequestAddPet(
                                BASE_URI,
                                POST_PATH,
                                pet
                        ))
                        .when()
                        .post()
                        .thenReturn();
    }

    @Test
    @DisplayName("Validate the response status code")
    void validateResponseStatusCode() {
        MatcherAssert.assertThat(response.getStatusCode(), Matchers.is(200));
    }

    @Test
    @DisplayName("Validate the wrong response status code")
    void validateErrorResponseStatusCode() {
        MatcherAssert.assertThat(invalidResponse.getStatusCode(), Matchers.is(400));
    }

}
