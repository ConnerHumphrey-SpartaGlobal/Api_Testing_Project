package com.sparta.PetApi.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface JsonSerializable {
    ObjectMapper objectMapper = new ObjectMapper();

    default String toJson() throws JsonProcessingException {
        return objectMapper.writeValueAsString(this);
    }
}