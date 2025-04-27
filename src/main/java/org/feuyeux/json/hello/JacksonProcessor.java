package org.feuyeux.json.hello;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.io.SegmentedStringWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.util.List;

public class JacksonProcessor {
    private static final Logger logger = LogManager.getLogger(JacksonProcessor.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    /**
     * Serialize a Fruit object to JSON string using Jackson
     * 
     * @param fruit The fruit object to serialize
     * @return JSON string representation
     */
    public String serialize(Fruit fruit) throws JsonProcessingException {
        logger.debug("Serializing fruit object: {}", fruit);
        String json = objectMapper.writeValueAsString(fruit);
        logger.debug("Serialized JSON: {}", json);
        return json;
    }
    
    /**
     * Serialize a Fruit object to escaped JSON string using Jackson
     * 
     * @param fruit The fruit object to serialize
     * @return Escaped JSON string representation
     */
    public String serializeWithEscape(Fruit fruit) throws IOException {
        logger.debug("Serializing fruit object with escape: {}", fruit);
        String json = objectMapper.writeValueAsString(fruit);
        String escapedJson = escapeJson(json);
        logger.debug("Serialized escaped JSON: {}", escapedJson);
        String nativeEscapedJson = nativeSerializeWithEscape(fruit);
        logger.debug("Serialized escaped JSON: {}", nativeEscapedJson);
        return escapedJson;
    }
    
    /**
     * Serialize a list of Fruit objects to JSON string using Jackson
     * 
     * @param fruits The list of fruit objects to serialize
     * @return JSON string representation
     */
    public String serializeList(List<Fruit> fruits) throws JsonProcessingException {
        logger.debug("Serializing list of {} fruit objects", fruits.size());
        String json = objectMapper.writeValueAsString(fruits);
        logger.debug("Serialized JSON list: {}", json);
        return json;
    }
    
    /**
     * Serialize a list of Fruit objects to escaped JSON string using Jackson
     * 
     * @param fruits The list of fruit objects to serialize
     * @return Escaped JSON string representation
     */
    public String serializeListWithEscape(List<Fruit> fruits) throws JsonProcessingException {
        logger.debug("Serializing list of {} fruit objects with escape", fruits.size());
        String json = objectMapper.writeValueAsString(fruits);
        String escapedJson = escapeJson(json);
        logger.debug("Serialized escaped JSON list: {}", escapedJson);
        return escapedJson;
    }
    
    /**
     * Deserialize a JSON string to a Fruit object using Jackson
     * 
     * @param json The JSON string to deserialize
     * @return Deserialized Fruit object
     */
    public Fruit deserialize(String json) throws JsonProcessingException {
        logger.debug("Deserializing JSON: {}", json);
        Fruit fruit = objectMapper.readValue(json, Fruit.class);
        logger.debug("Deserialized fruit object: {}", fruit);
        return fruit;
    }
    
    /**
     * Deserialize an escaped JSON string to a Fruit object using Jackson
     * 
     * @param escapedJson The escaped JSON string to deserialize
     * @return Deserialized Fruit object
     */
    public Fruit deserializeWithUnescape(String escapedJson) throws JsonProcessingException {
        logger.debug("Deserializing escaped JSON: {}", escapedJson);
        String json = unescapeJson(escapedJson);
        logger.debug("Unescaped JSON: {}", json);
        Fruit fruit = objectMapper.readValue(json, Fruit.class);
        logger.debug("Deserialized fruit object: {}", fruit);
        return fruit;
    }
    
    /**
     * Deserialize a JSON string to a list of Fruit objects using Jackson
     * 
     * @param json The JSON string to deserialize
     * @return List of deserialized Fruit objects
     */
    public List<Fruit> deserializeList(String json) throws JsonProcessingException {
        logger.debug("Deserializing JSON list: {}", json);
        List<Fruit> fruits = objectMapper.readValue(json, new TypeReference<List<Fruit>>() {});
        logger.debug("Deserialized {} fruit objects", fruits.size());
        return fruits;
    }
    
    /**
     * Deserialize an escaped JSON string to a list of Fruit objects using Jackson
     * 
     * @param escapedJson The escaped JSON string to deserialize
     * @return List of deserialized Fruit objects
     */
    public List<Fruit> deserializeListWithUnescape(String escapedJson) throws JsonProcessingException {
        logger.debug("Deserializing escaped JSON list: {}", escapedJson);
        String json = unescapeJson(escapedJson);
        logger.debug("Unescaped JSON list: {}", json);
        List<Fruit> fruits = objectMapper.readValue(json, new TypeReference<List<Fruit>>() {});
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
     * Custom Character Escapes to ensure quotes are always escaped
     */
    private static class CustomCharacterEscapes extends CharacterEscapes {
        private final int[] asciiEscapes;
        
        public CustomCharacterEscapes() {
            // Start with standard escapes
            asciiEscapes = standardAsciiEscapesForJSON();
            // Always escape double quotes
            asciiEscapes['"'] = CharacterEscapes.ESCAPE_STANDARD;
        }
        
        @Override
        public int[] getEscapeCodesForAscii() {
            return asciiEscapes;
        }
        
        @Override
        public SerializableString getEscapeSequence(int ch) {
            return null; // Use standard escaping mechanism
        }
    }
    
    /**
     * Serialize a Fruit object to escaped JSON string using Jackson's native features
     * 
     * @param fruit The fruit object to serialize
     * @return Escaped JSON string representation
     */
    public String nativeSerializeWithEscape(Fruit fruit) throws IOException {
        logger.debug("Native serializing fruit object with escape: {}", fruit);
        
        // Create a custom ObjectMapper with appropriate escaping
        ObjectMapper customMapper = new ObjectMapper();
        customMapper.getFactory().setCharacterEscapes(new CustomCharacterEscapes());
        
        // Use a StringWriter to collect the output
        SegmentedStringWriter writer = new SegmentedStringWriter(customMapper.getFactory()._getBufferRecycler());
        JsonGenerator gen = customMapper.getFactory().createGenerator(writer);
        
        // Write the object
        customMapper.writeValue(gen, fruit);
        
        String escapedJson = writer.getAndClear();
        logger.debug("Native serialized escaped JSON: {}", escapedJson);
        return escapedJson;
    }
    
    /**
     * Serialize a list of Fruit objects to escaped JSON string using Jackson's native features
     * 
     * @param fruits The list of fruit objects to serialize
     * @return Escaped JSON string representation
     */
    public String nativeSerializeListWithEscape(List<Fruit> fruits) throws IOException {
        logger.debug("Native serializing list of {} fruit objects with escape", fruits.size());
        
        // Create a custom ObjectMapper with appropriate escaping
        ObjectMapper customMapper = new ObjectMapper();
        customMapper.getFactory().setCharacterEscapes(new CustomCharacterEscapes());
        
        // Use a StringWriter to collect the output
        SegmentedStringWriter writer = new SegmentedStringWriter(customMapper.getFactory()._getBufferRecycler());
        JsonGenerator gen = customMapper.getFactory().createGenerator(writer);
        
        // Write the object
        customMapper.writeValue(gen, fruits);
        
        String escapedJson = writer.getAndClear();
        logger.debug("Native serialized escaped JSON list: {}", escapedJson);
        return escapedJson;
    }
    
    /**
     * Deserialize an escaped JSON string to a Fruit object using Jackson's native features
     * 
     * @param escapedJson The escaped JSON string to deserialize
     * @return Deserialized Fruit object
     */
    public Fruit nativeDeserializeWithUnescape(String escapedJson) throws JsonProcessingException {
        logger.debug("Native deserializing escaped JSON: {}", escapedJson);
        // Jackson doesn't have a direct way to parse already-escaped JSON
        // We need to unescape it manually before parsing
        String json = unescapeJson(escapedJson);
        logger.debug("Unescaped JSON: {}", json);
        Fruit fruit = objectMapper.readValue(json, Fruit.class);
        logger.debug("Native deserialized fruit object: {}", fruit);
        return fruit;
    }
    
    /**
     * Deserialize an escaped JSON string to a list of Fruit objects using Jackson's native features
     * 
     * @param escapedJson The escaped JSON string to deserialize
     * @return List of deserialized Fruit objects
     */
    public List<Fruit> nativeDeserializeListWithUnescape(String escapedJson) throws JsonProcessingException {
        logger.debug("Native deserializing escaped JSON list: {}", escapedJson);
        // Jackson doesn't have a direct way to parse already-escaped JSON
        // We need to unescape it manually before parsing
        String json = unescapeJson(escapedJson);
        logger.debug("Unescaped JSON list: {}", json);
        List<Fruit> fruits = objectMapper.readValue(json, new TypeReference<List<Fruit>>() {});
        logger.debug("Native deserialized {} fruit objects", fruits.size());
        return fruits;
    }
}