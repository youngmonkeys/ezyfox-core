package com.tvd12.ezyfox.core.testing.config;

import java.util.Map;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.config.ServerEventHandlerProviderImpl;
import com.tvd12.ezyfox.core.testing.BaseTestCore;

import static org.testng.Assert.*;

public class ServerEventHandlerProviderTest extends BaseTestCore {

    @Test
    public void testValidCase() {
        Map<Object, Class<?>> handlers = ServerEventHandlerProviderImpl.builder()
                .contextClass(getClass())
                .build()
                .provide();
        assertEquals(1, handlers.size());
    }
    
    @Test(expectedExceptions = {RuntimeException.class})
    public void testInValidCase() {
        ServerEventHandlerProviderImpl.builder()
                .configFilePath("ezyfox/config/server-event-handlers2.properties")
                .contextClass(getClass())
                .build()
                .provide();
    }
    
}
