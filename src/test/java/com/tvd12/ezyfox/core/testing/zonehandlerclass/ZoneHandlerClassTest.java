package com.tvd12.ezyfox.core.testing.zonehandlerclass;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.annotation.ZoneName;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.entities.ApiZone;
import com.tvd12.ezyfox.core.structure.ZoneHandlerClass;

import lombok.Data;

public class ZoneHandlerClassTest {

    @Test
    public void testInvalidCase() {
        ZoneHandlerClass clazz = new ZoneHandlerClass(
                ClassA.class, ExampleUser.class, new ArrayList<Class<?>>());
        assertEquals(clazz.getZoneName(), "");
        assertEquals(clazz.getPropertiesClassWrapper().getClazz(), ClassA.class);
        assertNotNull(clazz.newInstance());
        
        clazz = new ZoneHandlerClass(
                ClassB.class, ExampleUser.class, new ArrayList<Class<?>>());
        assertEquals(clazz.getZoneName(), "zone");
    }
    
    @Test(expectedExceptions = {IllegalStateException.class})
    public void testInvalidCase1() {
        ZoneHandlerClass clazz = new ZoneHandlerClass(
                ClassC.class, ExampleUser.class, new ArrayList<Class<?>>());
        clazz.newInstance();
    }
    
    @Test(expectedExceptions = {IllegalStateException.class})
    public void testInvalidCase2() {
        ZoneHandlerClass clazz = new ZoneHandlerClass(
                ClassD.class, ExampleUser.class, new ArrayList<Class<?>>());
        clazz.newInstance();
    }
    
    
    @Data
    @ServerEventHandler(event = "ZONE")
    public static class ClassA {
        private String value;
        
        public void handle(AppContext context, ApiZone zone, ExampleUser user) {
            
        }
    }
    
    @Data
    @ServerEventHandler(event = "ZONE")
    @ZoneName("zone")
    public static class ClassB {
        private String value;
        
        public void handle(AppContext context, ApiZone zone, ExampleUser user) {
            
        }
    }
    
    @Data
    @ServerEventHandler(event = "ZONE")
    @ZoneName("zone")
    public static class ClassC {
        private String value;
        
        public ClassC(String str) {}
        
        public void handle(AppContext context, ApiZone zone, ExampleUser user) {
            
        }
    }
    
    @Data
    @ServerEventHandler(event = "ZONE")
    @ZoneName("zone")
    public static class ClassD {
        private String value;
        
        private ClassD() {}
        
        public void handle(AppContext context, ApiZone zone, ExampleUser user) {
            
        }
    }
    
    public static class ExampleUser {
        
    }
}
