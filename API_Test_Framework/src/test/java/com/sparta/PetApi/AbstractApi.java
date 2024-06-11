package com.sparta.PetApi;

import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AbstractApi {

    public static JSONObject parseResponseToJsonObject(Response response){
        JSONParser parser = new JSONParser();
        JSONObject responseBody = null;
        try {
            responseBody = (JSONObject) parser.parse(response.getBody().asString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return responseBody;
    }

    public static JSONArray parseResponseToJsonArray(Response response){

        JSONArray responseBody = null;
        JSONParser parser = new JSONParser();
        try{
            responseBody = (JSONArray) parser.parse(response.getBody().asString());
        }catch(ParseException e){
            e.printStackTrace();
        }
        return responseBody;
    }

}
