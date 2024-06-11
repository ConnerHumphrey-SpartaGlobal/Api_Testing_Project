package com.sparta.PetApi;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonUtils {
    private static final JSONParser PARSER = new JSONParser();

    public static JSONArray toJsonArray(String element) {
        try {

            return (JSONArray) PARSER.parse(element);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
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
