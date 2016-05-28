package com.tvd12.ezyfox.core.testing.commandprovider;

import static org.testng.Assert.assertEquals;

import java.util.Map;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.config.CommandProvider;
import com.tvd12.ezyfox.core.testing.BaseTestCore;

public class CommandProviderTest extends BaseTestCore {

    @Test
    public void testValidCase() {
        Map<Object, Class<?>> classes = CommandProvider.provide(getClass());
        assertEquals(1, classes.size());
    }
    
    @Test(expectedExceptions = {RuntimeException.class})
    public void testInValidCase() {
        CommandProvider.provide(getClass(), 
                "ezyfox/config/command-implementations2.properties");
    }
    
    @Override
    public Class<?> getTestClass() {
        return CommandProvider.class;
    }
    
    
    
}
