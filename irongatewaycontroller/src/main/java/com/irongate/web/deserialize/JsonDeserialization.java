package com.irongate.web.deserialize;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.irongate.entities.BusinessEntity;

import java.io.IOException;

public class JsonDeserialization {

    private static ObjectMapper mapper = new ObjectMapper();

    public static BusinessEntity deserialize(String json, Class clazz) throws IOException {

        return (BusinessEntity) mapper.readValue(json, clazz);

    }
}
