package com.ddes.smart_meter_system_back_end.json;

import com.ddes.smart_meter_system_back_end.reading.Reading;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonService {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    //use to create json string
    public static ObjectNode createJson(String key, Object value) {
        ObjectNode jsonObject = objectMapper.createObjectNode();
        jsonObject.putPOJO(key, value);
        return jsonObject;
    }

    //convert reading json coming through from front end into reading object
    public static Reading fromJsonToReading(String clientId , String json) {
        double readingValue = Double.parseDouble(json.substring(json.indexOf("reading") + 9, json.length() - 1));
        return new Reading(clientId, readingValue);
    }
}
