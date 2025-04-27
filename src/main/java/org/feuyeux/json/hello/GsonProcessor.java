package org.feuyeux.json.hello;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.lang.reflect.Type;
import java.util.List;

public class GsonProcessor {
    private static final Logger logger = LogManager.getLogger(GsonProcessor.class);
    private final Gson gson = new Gson();
    
    /**
     * Serialize a Fruit object to JSON string using Gson
     * 
     * @param fruit The fruit object to serialize
     * @return JSON string representation
     */
    public String serialize(Fruit fruit) {
        logger.debug("Serializing fruit object: {}", fruit);
        String json = gson.toJson(fruit);
        logger.debug("Serialized JSON: {}", json);
        return json;
    }
    
    /**
     * Serialize a Fruit object to escaped JSON string using Gson
     * 
     * @param fruit The fruit object to serialize
     * @return Escaped JSON string representation
     */
    public String serializeWithEscape(Fruit fruit) {
        logger.debug("Serializing fruit object with escape: {}", fruit);
        String json = gson.toJson(fruit);
        String escapedJson = escapeJson(json);
        logger.debug("Serialized escaped JSON: {}", escapedJson);
        return escapedJson;
    }
    
    /**
     * Serialize a list of Fruit objects to JSON string using Gson
     * 
     * @param fruits The list of fruit objects to serialize
     * @return JSON string representation
     */
    public String serializeList(List<Fruit> fruits) {
        logger.debug("Serializing list of {} fruit objects", fruits.size());
        String json = gson.toJson(fruits);
        logger.debug("Serialized JSON list: {}", json);
        return json;
    }
    
    /**
     * Serialize a list of Fruit objects to escaped JSON string using Gson
     * 
     * @param fruits The list of fruit objects to serialize
     * @return Escaped JSON string representation
     */
    public String serializeListWithEscape(List<Fruit> fruits) {
        logger.debug("Serializing list of {} fruit objects with escape", fruits.size());
        String json = gson.toJson(fruits);
        String escapedJson = escapeJson(json);
        logger.debug("Serialized escaped JSON list: {}", escapedJson);
        return escapedJson;
    }
    
    /**
     * Deserialize a JSON string to a Fruit object using Gson
     * 
     * @param json The JSON string to deserialize
     * @return Deserialized Fruit object
     */
    public Fruit deserialize(String json) {
        logger.debug("Deserializing JSON: {}", json);
        Fruit fruit = gson.fromJson(json, Fruit.class);
        logger.debug("Deserialized fruit object: {}", fruit);
        return fruit;
    }
    
    /**
     * Deserialize an escaped JSON string to a Fruit object using Gson
     * 
     * @param escapedJson The escaped JSON string to deserialize
     * @return Deserialized Fruit object
     */
    public Fruit deserializeWithUnescape(String escapedJson) {
        logger.debug("Deserializing escaped JSON: {}", escapedJson);
        String json = unescapeJson(escapedJson);
        logger.debug("Unescaped JSON: {}", json);
        Fruit fruit = gson.fromJson(json, Fruit.class);
        logger.debug("Deserialized fruit object: {}", fruit);
        return fruit;
    }
    
    /**
     * Deserialize a JSON string to a list of Fruit objects using Gson
     * 
     * @param json The JSON string to deserialize
     * @return List of deserialized Fruit objects
     */
    public List<Fruit> deserializeList(String json) {
        logger.debug("Deserializing JSON list: {}", json);
        Type listType = new TypeToken<List<Fruit>>(){}.getType();
        List<Fruit> fruits = gson.fromJson(json, listType);
        logger.debug("Deserialized {} fruit objects", fruits.size());
        return fruits;
    }
    
    /**
     * Deserialize an escaped JSON string to a list of Fruit objects using Gson
     * 
     * @param escapedJson The escaped JSON string to deserialize
     * @return List of deserialized Fruit objects
     */
    public List<Fruit> deserializeListWithUnescape(String escapedJson) {
        logger.debug("Deserializing escaped JSON list: {}", escapedJson);
        String json = unescapeJson(escapedJson);
        logger.debug("Unescaped JSON list: {}", json);
        Type listType = new TypeToken<List<Fruit>>(){}.getType();
        List<Fruit> fruits = gson.fromJson(json, listType);
        logger.debug("Deserialized {} fruit objects", fruits.size());
        return fruits;
    }
    
    /**
     * Escape JSON string by adding backslashes before double quotes
     * 
     * @param json Original JSON string
     * @return Escaped JSON string
     */
    private String escapeJson(String json) {
        return json.replace("\"", "\\\"");
    }
    
    /**
     * Unescape JSON string by removing backslashes before double quotes
     * 
     * @param escapedJson Escaped JSON string
     * @return Original JSON string
     */
    private String unescapeJson(String escapedJson) {
        return escapedJson.replace("\\\"", "\"");
    }
}