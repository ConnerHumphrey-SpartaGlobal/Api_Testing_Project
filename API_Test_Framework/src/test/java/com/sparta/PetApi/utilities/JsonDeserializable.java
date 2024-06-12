package com.sparta.PetApi.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface JsonDeserializable {
     ObjectMapper objectMapper = new ObjectMapper();

     default  <T> T toObject(String jsonString, Class<T> object) throws JsonProcessingException {
        return objectMapper.readValue(jsonString, object);
    }
}
