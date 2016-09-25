package com.tvd12.ezyfox.core.testing.config;

import java.util.Map;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.config.ServerEventHandlerProvider;
import com.tvd12.ezyfox.core.testing.BaseTestCore;

import static org.testng.Assert.*;

public class ServerEventHandlerProviderTest extends BaseTestCore {

    @Test
    public void testValidCase() {
        Map<Object, Class<?>> handlers = ServerEventHandlerProvider
                .provide(getClass());
        assertEquals(1, handlers.size());
    }
    
    @Test(expectedExceptions = {RuntimeException.class})
    public void testInValidCase() {
        ServerEventHandlerProvider
                .provide(getClass(), "ezyfox/config/server-event-handlers2.properties");
    }
    
    @Override
    public Class<?> getTestClass() {
        return ServerEventHandlerProvider.class;
    }
    
}
