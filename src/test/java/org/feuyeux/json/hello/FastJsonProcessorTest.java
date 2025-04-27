package org.feuyeux.json.hello;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class FastJsonProcessorTest {
    
    private final FastJsonProcessor processor = new FastJsonProcessor();
    
    @Test
    public void testSerializeAndDeserialize() {
        // Create a test fruit
        Fruit apple = new Fruit("Apple", "Pomme", "Яблоко");
        
        // Serialize the fruit to JSON
        String json = processor.serialize(apple);
        assertNotNull(json);
        
        // Deserialize the JSON back to a Fruit object
        Fruit deserializedApple = processor.deserialize(json);
        assertNotNull(deserializedApple);
        
        // Verify the deserialized object matches the original
        assertEquals(apple.getEnglish(), deserializedApple.getEnglish());
        assertEquals(apple.getFrench(), deserializedApple.getFrench());
        assertEquals(apple.getRussian(), deserializedApple.getRussian());
        assertEquals(apple, deserializedApple);
    }
    
    @Test
    public void testSerializeAndDeserializeList() {
        // Create a list of fruits
        List<Fruit> fruits = new ArrayList<>();
        fruits.add(new Fruit("Apple", "Pomme", "Яблоко"));
        fruits.add(new Fruit("Banana", "Banane", "Банан"));
        fruits.add(new Fruit("Orange", "Orange", "Апельсин"));
        
        // Serialize the list to JSON
        String json = processor.serializeList(fruits);
        assertNotNull(json);
        
        // Deserialize the JSON back to a list of Fruit objects
        List<Fruit> deserializedFruits = processor.deserializeList(json);
        assertNotNull(deserializedFruits);
        
        // Verify the list size
        assertEquals(fruits.size(), deserializedFruits.size());
        
        // Verify each fruit in the list
        for (int i = 0; i < fruits.size(); i++) {
            assertEquals(fruits.get(i), deserializedFruits.get(i));
        }
    }
    
    @Test
    public void testSerializeAndDeserializeWithEscape() {
        // Create a test fruit
        Fruit apple = new Fruit("Apple", "Pomme", "Яблоко");
        
        // Serialize the fruit to escaped JSON
        String escapedJson = processor.serializeWithEscape(apple);
        assertNotNull(escapedJson);
        assertTrue(escapedJson.contains("\\\"english\\\""));
        
        // Deserialize the escaped JSON back to a Fruit object
        Fruit deserializedApple = processor.deserializeWithUnescape(escapedJson);
        assertNotNull(deserializedApple);
        
        // Verify the deserialized object matches the original
        assertEquals(apple.getEnglish(), deserializedApple.getEnglish());
        assertEquals(apple.getFrench(), deserializedApple.getFrench());
        assertEquals(apple.getRussian(), deserializedApple.getRussian());
        assertEquals(apple, deserializedApple);
    }
    
    @Test
    public void testSerializeAndDeserializeListWithEscape() {
        // Create a list of fruits
        List<Fruit> fruits = new ArrayList<>();
        fruits.add(new Fruit("Apple", "Pomme", "Яблоко"));
        fruits.add(new Fruit("Banana", "Banane", "Банан"));
        fruits.add(new Fruit("Orange", "Orange", "Апельсин"));
        
        // Serialize the list to escaped JSON
        String escapedJson = processor.serializeListWithEscape(fruits);
        assertNotNull(escapedJson);
        assertTrue(escapedJson.contains("\\\"english\\\""));
        
        // Deserialize the escaped JSON back to a list of Fruit objects
        List<Fruit> deserializedFruits = processor.deserializeListWithUnescape(escapedJson);
        assertNotNull(deserializedFruits);
        
        // Verify the list size
        assertEquals(fruits.size(), deserializedFruits.size());
        
        // Verify each fruit in the list
        for (int i = 0; i < fruits.size(); i++) {
            assertEquals(fruits.get(i), deserializedFruits.get(i));
        }
    }

    @Test
    public void testNativeSerializeAndDeserializeWithEscape() {
        // Create a test fruit
        Fruit apple = new Fruit("Apple", "Pomme", "Яблоко");
        
        // Serialize the fruit to escaped JSON using native method
        String escapedJson = processor.nativeSerializeWithEscape(apple);
        assertNotNull(escapedJson);
        // Check if quotes are properly escaped
        assertTrue(escapedJson.contains("\\\"english\\\""));
        
        // Deserialize the escaped JSON back to a Fruit object
        Fruit deserializedApple = processor.nativeDeserializeWithUnescape(escapedJson);
        assertNotNull(deserializedApple);
        
        // Verify the deserialized object matches the original
        assertEquals(apple.getEnglish(), deserializedApple.getEnglish());
        assertEquals(apple.getFrench(), deserializedApple.getFrench());
        assertEquals(apple.getRussian(), deserializedApple.getRussian());
        assertEquals(apple, deserializedApple);
    }
    
    @Test
    public void testNativeSerializeAndDeserializeListWithEscape() {
        // Create a list of fruits
        List<Fruit> fruits = new ArrayList<>();
        fruits.add(new Fruit("Apple", "Pomme", "Яблоко"));
        fruits.add(new Fruit("Banana", "Banane", "Банан"));
        fruits.add(new Fruit("Orange", "Orange", "Апельсин"));
        
        // Serialize the list to escaped JSON using native method
        String escapedJson = processor.nativeSerializeListWithEscape(fruits);
        assertNotNull(escapedJson);
        // Check if quotes are properly escaped
        assertTrue(escapedJson.contains("\\\"english\\\""));
        
        // Deserialize the escaped JSON back to a list of Fruit objects
        List<Fruit> deserializedFruits = processor.nativeDeserializeListWithUnescape(escapedJson);
        assertNotNull(deserializedFruits);
        
        // Verify the list size
        assertEquals(fruits.size(), deserializedFruits.size());
        
        // Verify each fruit in the list
        for (int i = 0; i < fruits.size(); i++) {
            assertEquals(fruits.get(i), deserializedFruits.get(i));
        }
    }
}