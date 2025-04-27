package org.feuyeux.json.hello;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.JSONSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class FastJsonProcessor {
    private static final Logger logger = LogManager.getLogger(FastJsonProcessor.class);
    
    /**
     * Serialize a Fruit object to JSON string using FastJSON
     * 
     * @param fruit The fruit object to serialize
     * @return JSON string representation
     */
    public String serialize(Fruit fruit) {
        logger.debug("Serializing fruit object: {}", fruit);
        String json = JSON.toJSONString(fruit);
        logger.debug("Serialized JSON: {}", json);
        return json;
    }
    
    /**
     * Serialize a Fruit object to escaped JSON string using FastJSON
     * 
     * @param fruit The fruit object to serialize
     * @return Escaped JSON string representation
     */
    public String serializeWithEscape(Fruit fruit) {
        logger.debug("Serializing fruit object with escape: {}", fruit);
        String json = JSON.toJSONString(fruit);
        String escapedJson = escapeJson(json);
        logger.debug("Serialized escaped JSON: {}", escapedJson);
        String nativeEscapedJson = nativeSerializeWithEscape(fruit);
        logger.debug("Serialized escaped JSON: {}", nativeEscapedJson);
        return escapedJson;
    }
    
    /**
     * Serialize a list of Fruit objects to JSON string using FastJSON
     * 
     * @param fruits The list of fruit objects to serialize
     * @return JSON string representation
     */
    public String serializeList(List<Fruit> fruits) {
        logger.debug("Serializing list of {} fruit objects", fruits.size());
        String json = JSON.toJSONString(fruits);
        logger.debug("Serialized JSON list: {}", json);
        return json;
    }
    
    /**
     * Serialize a list of Fruit objects to escaped JSON string using FastJSON
     * 
     * @param fruits The list of fruit objects to serialize
     * @return Escaped JSON string representation
     */
    public String serializeListWithEscape(List<Fruit> fruits) {
        logger.debug("Serializing list of {} fruit objects with escape", fruits.size());
        String json = JSON.toJSONString(fruits);
        String escapedJson = escapeJson(json);
        logger.debug("Serialized escaped JSON list: {}", escapedJson);
        return escapedJson;
    }
    
    /**
     * Deserialize a JSON string to a Fruit object using FastJSON
     * 
     * @param json The JSON string to deserialize
     * @return Deserialized Fruit object
     */
    public Fruit deserialize(String json) {
        logger.debug("Deserializing JSON: {}", json);
        Fruit fruit = JSON.parseObject(json, Fruit.class);
        logger.debug("Deserialized fruit object: {}", fruit);
        return fruit;
    }
    
    /**
     * Deserialize an escaped JSON string to a Fruit object using FastJSON
     * 
     * @param escapedJson The escaped JSON string to deserialize
     * @return Deserialized Fruit object
     */
    public Fruit deserializeWithUnescape(String escapedJson) {
        logger.debug("Deserializing escaped JSON: {}", escapedJson);
        String json = unescapeJson(escapedJson);
        logger.debug("Unescaped JSON: {}", json);
        Fruit fruit = JSON.parseObject(json, Fruit.class);
        logger.debug("Deserialized fruit object: {}", fruit);
        return fruit;
    }
    
    /**
     * Deserialize a JSON string to a list of Fruit objects using FastJSON
     * 
     * @param json The JSON string to deserialize
     * @return List of deserialized Fruit objects
     */
    public List<Fruit> deserializeList(String json) {
        logger.debug("Deserializing JSON list: {}", json);
        List<Fruit> fruits = JSON.parseArray(json, Fruit.class);
        logger.debug("Deserialized {} fruit objects", fruits.size());
        return fruits;
    }
    
    /**
     * Deserialize an escaped JSON string to a list of Fruit objects using FastJSON
     * 
     * @param escapedJson The escaped JSON string to deserialize
     * @return List of deserialized Fruit objects
     */
    public List<Fruit> deserializeListWithUnescape(String escapedJson) {
        logger.debug("Deserializing escaped JSON list: {}", escapedJson);
        String json = unescapeJson(escapedJson);
        logger.debug("Unescaped JSON list: {}", json);
        List<Fruit> fruits = JSON.parseArray(json, Fruit.class);
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
    
    /**
     * Serialize a Fruit object to escaped JSON string using FastJSON's native features
     * 
     * @param fruit The fruit object to serialize
     * @return Escaped JSON string representation
     */
    public String nativeSerializeWithEscape(Fruit fruit) {
        logger.debug("Native serializing fruit object with escape: {}", fruit);
        String json = JSON.toJSONString(fruit);
        String escapedJson = escapeJson(json);
        logger.debug("Native serialized escaped JSON: {}", escapedJson);
        return escapedJson;
    }
    
    /**
     * Serialize a list of Fruit objects to escaped JSON string using FastJSON's native features
     * 
     * @param fruits The list of fruit objects to serialize
     * @return Escaped JSON string representation
     */
    public String nativeSerializeListWithEscape(List<Fruit> fruits) {
        logger.debug("Native serializing list of {} fruit objects with escape", fruits.size());
        String json = JSON.toJSONString(fruits);
        String escapedJson = escapeJson(json);
        logger.debug("Native serialized escaped JSON list: {}", escapedJson);
        return escapedJson;
    }
    
    /**
     * Deserialize an escaped JSON string to a Fruit object using FastJSON's native features
     * 
     * @param escapedJson The escaped JSON string to deserialize
     * @return Deserialized Fruit object
     */
    public Fruit nativeDeserializeWithUnescape(String escapedJson) {
        logger.debug("Native deserializing escaped JSON: {}", escapedJson);
        // For deserialization, we need to handle the escaping first since
        // FastJSON doesn't have a direct way to parse escaped JSON
        String json = unescapeJson(escapedJson);
        logger.debug("Unescaped JSON: {}", json);
        Fruit fruit = JSON.parseObject(json, Fruit.class);
        logger.debug("Native deserialized fruit object: {}", fruit);
        return fruit;
    }
    
    /**
     * Deserialize an escaped JSON string to a list of Fruit objects using FastJSON's native features
     * 
     * @param escapedJson The escaped JSON string to deserialize
     * @return List of deserialized Fruit objects
     */
    public List<Fruit> nativeDeserializeListWithUnescape(String escapedJson) {
        logger.debug("Native deserializing escaped JSON list: {}", escapedJson);
        String json = unescapeJson(escapedJson);
        logger.debug("Unescaped JSON list: {}", json);
        List<Fruit> fruits = JSON.parseArray(json, Fruit.class);
        logger.debug("Native deserialized {} fruit objects", fruits.size());
        return fruits;
    }
}