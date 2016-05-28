package com.tvd12.ezyfox.core.testing.model;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.transport.impl.ConcurrentParameters;

public class ConcurrentParametersTest {

    @Test
    public void testValidCase() {
        ConcurrentParameters params = new ConcurrentParameters();
        params.set("Hello", "World");
        assertEquals(params.get("Hello"), "World");
        assertEquals(params.get("Hello", String.class), "World");
        assertTrue(params.contain("Hello"));
        assertFalse(params.contain("Hello1"));
        
        Map<Object, Object> values = new HashMap<>();
        values.put("I", "You");
        params.setAll(values);
        assertEquals(params.size(), 2);
        assertEquals(params.keys().size(), 2);
        assertEquals(params.values().size(), 2);
        assertFalse(params.isEmpty());
        params.clear();
        assertTrue(params.isEmpty());
        params.setIfAbsent("Hello", "World");
        assertFalse(params.isEmpty());
        
    }
    
}
