package com.tvd12.ezyfox.core.testing.model;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.transport.Parameters;
import com.tvd12.ezyfox.core.transport.impl.ParameterWrapper;

import static org.testng.Assert.*;

import java.util.HashMap;
import java.util.Map;

public class ParametersTest {

    @Test
    public void testValidCase() {
        Parameters params = new ParameterWrapper();
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
    }
    
}
