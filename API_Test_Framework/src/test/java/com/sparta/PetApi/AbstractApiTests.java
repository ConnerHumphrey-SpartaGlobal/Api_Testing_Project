package com.sparta.PetApi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.HashMap;
import java.util.Map;

public class AbstractApiTests {

    private static final JSONParser PARSER = new JSONParser();
    protected static Response response;
    protected static JSONObject responseBody;
    protected static Response invalidResponse;
    protected static JSONObject invalidResponseBody;


    public static JSONObject parseResponseToJsonObject(Response response){
        JSONObject responseBody = null;
        try {
            responseBody = (JSONObject) PARSER.parse(response.getBody().asString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return responseBody;
    }

    public static JSONArray parseResponseToJsonArray(Response response){

        JSONArray responseBody = null;
        try{
            responseBody = (JSONArray) PARSER.parse(response.getBody().asString());
        }catch(ParseException e){
            e.printStackTrace();
        }
        return responseBody;
    }

    public static JSONObject toJsonObject(String element) {
        try {

            return (JSONObject) PARSER.parse(element);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T getJsonValue(JSONObject object, String keyPath) {
        if (object == null || keyPath == null || keyPath.isEmpty()) {
            throw new IllegalArgumentException("JSONObject or keyPath cannot be null/empty");
        }

        String[] keys = keyPath.split("\\.");
        JSONObject currentObj = object;

        for (int i = 0; i < keys.length - 1; i++) {
            String key = keys[i];
            currentObj = toJsonObject(currentObj.get(key).toString());
            if (currentObj == null) {
                return null;
            }
        }

        String finalKey = keys[keys.length - 1];
        var value = currentObj.get(finalKey);
        return (T) value;
    }




}
