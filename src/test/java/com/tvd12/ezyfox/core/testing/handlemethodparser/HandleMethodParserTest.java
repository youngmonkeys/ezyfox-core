package com.tvd12.ezyfox.core.testing.handlemethodparser;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.lang.reflect.Method;
import java.util.List;

import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.tvd12.ezyfox.core.annotation.HandleMethod;
import com.tvd12.ezyfox.core.annotation.parser.HandleMethodParser;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.entities.ApiGameUser;
import com.tvd12.ezyfox.core.entities.ApiRoom;
import com.tvd12.ezyfox.core.entities.ApiUser;
import com.tvd12.test.reflect.ReflectMethodUtil;

public class HandleMethodParserTest {
    
    @Test
    public void getServerHandleMethodCustomCase() {
        Method method = HandleMethodParser.getServerHandleMethod(ClassA.class, String.class);
        assertEquals("perform", method.getName());
    }
    
    @Test
    public void getServerHandleMethodDefaultCase() {
        Method method = HandleMethodParser.getServerHandleMethod(ClassB.class, String.class);
        assertEquals("handle", method.getName());
    }
    
    @Test(expectedExceptions = {RuntimeException.class})
    public void getServerHandleMethodNotFoundCase() {
        HandleMethodParser.getServerHandleMethod(Class.class, String.class);
    }
    
    @Test(expectedExceptions = {RuntimeException.class})
    public void getServerHandleMethodInvalidArgumentTest() {
        HandleMethodParser.getServerHandleMethod(ClassA.class, Integer.class);
    }
    
    @Test
    public void testConstructor() {
        Object object = ReflectMethodUtil.invokeConstructor(HandleMethodParser.class);
        assertNotNull(object);
    }
    
    @Test
    public void testInvalidArgumentLengthCase() {
        Method method = HandleMethodParser.getServerHandleMethod(ClassC.class, String.class, String.class);
        assertEquals("perform", method.getName());
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void testGetServerHandleMethodWithModelClassesAndUserClassesValidCase() {
        Method method = HandleMethodParser.getServerHandleMethod(ClassH.class, 
                (List)Lists.newArrayList(ExRoom.class), 
                ExUser.class, 
                (List)Lists.newArrayList(ExGameUser.class));
        assertEquals(method.getName(), "perform");
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test(expectedExceptions = {RuntimeException.class})
    public void testGetServerHandleMethodWithModelClassesAndUserClassesInvalidCase() {
        HandleMethodParser.getServerHandleMethod(ClassD.class, 
                (List)Lists.newArrayList(ExRoom.class), 
                ExUser.class, 
                (List)Lists.newArrayList(ExGameUser.class));
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void testGetServerHandleMethodWithUserClassesValidCase() {
        Method method = HandleMethodParser.getServerHandleMethod(ClassE.class, 
                ExUser.class, 
                (List)Lists.newArrayList(ExGameUser.class));
        assertEquals(method.getName(), "perform");
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void testGetServerHandleMethodWithUserClassesValidCase1() {
        Method method = HandleMethodParser.getServerHandleMethod(ClassG.class, 
                ExUser.class, 
                (List)Lists.newArrayList(ExGameUser.class));
        assertEquals(method.getName(), "perform");
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test(expectedExceptions = {RuntimeException.class})
    public void testGetServerHandleMethodWithUserClassesInvalidCase() {
        HandleMethodParser.getServerHandleMethod(ClassF.class, 
                ExUser.class, 
                (List)Lists.newArrayList(ExGameUser.class));
    }
    
    //============= with model class and user classes ========
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void testGetServerHandleMethodWithModelClassAndUserClassesValidCase() {
        Method method = HandleMethodParser.getServerHandleMethod(ClassI.class, 
                ExZone.class, ExUser.class, 
                (List)Lists.newArrayList(ExGameUser.class));
        assertEquals(method.getName(), "perform");
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void testGetServerHandleMethodWithModelClassAndUserClassesValidCase1() {
        Method method = HandleMethodParser.getServerHandleMethod(ClassJ.class, 
                ExZone.class, ExUser.class, 
                (List)Lists.newArrayList(ExGameUser.class));
        assertEquals(method.getName(), "perform");
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test(expectedExceptions = {RuntimeException.class})
    public void testGetServerHandleMethodWithModelClassAndUserClassesInvalidCase() {
        Method method = HandleMethodParser.getServerHandleMethod(ClassK.class, 
                ExZone.class, ExUser.class, 
                (List)Lists.newArrayList(ExGameUser.class));
        assertEquals(method.getName(), "perform");
    }
    
    public static class ExRoom extends ApiRoom {
        
    }
    
    public static class ExUser extends ApiUser {
        
    }
    
    public static class ExZone {
        
    }
    
    public static class ExGameUser extends ApiGameUser {
        
    }

    public static class ClassA {
        @HandleMethod
        public void perform1(String name, String value) {
            
        }
        @HandleMethod
        public void perform(String name) {
            
        }
        
    }
    
    public static class ClassB {
        public void handle(String name) {
            
        }
    }
    
    public static class ClassC {
        
        @HandleMethod
        public void perform(String name) {
            
        }
        
        @HandleMethod
        public void perform(String name, String pass) {
            
        }
        
    }
    
    public static class ClassD {
        
        @HandleMethod
        public void hello() {}
        
        @HandleMethod
        public void hello(AppContext context, String name) {
            
        }
        
        public void handle(AppContext context, String name, String value) {
            
        }
        
        public void handle(String name, String value, String hello) {
            
        }
        
        public void handle(AppContext context, ExRoom room, String value) {
            
        }
        
    }
    
    public static class ClassE {
        
        @HandleMethod
        public void hello() {}
        
        @HandleMethod
        public void hello(AppContext context) {
            
        }
        
        public void handle(AppContext context, String name) {
            
        }
        
        public void handle(String name, String value) {
            
        }
        
        @HandleMethod
        public void perform(AppContext context, ExUser user) {
            
        }
    }
    
    public static class ClassF {
        
        @HandleMethod
        public void hello() {}
        
        @HandleMethod
        public void hello(AppContext context) {
            
        }
        
        public void handle(AppContext context, String name) {
            
        }
        
        public void handle(String name, String value) {
            
        }
        
    }
    
    public static class ClassG {
        @HandleMethod
        public void perform(AppContext context, ExGameUser user) {
            
        }
    }
    
    public static class ClassH {
        @HandleMethod
        public void perform(AppContext context, ExRoom room, ExGameUser user) {
            
        }
    }
    
    public static class ClassI {
        @HandleMethod
        public void perform(AppContext context, ExZone zone, ExGameUser user) {
            
        }
    }
    
    public static class ClassJ {
        @HandleMethod
        public void perform(AppContext context, ExZone zone, ExUser user) {
            
        }
    }
    
    public static class ClassK {
        @HandleMethod
        public void perform() {}
        
        @HandleMethod
        public void perform(String name) {}
        
        public void handle(AppContext context) {}
        
        public void handle(AppContext context, ExZone zone) {}
        
        public void handle(AppContext context, ExZone zone, String name) {}
        
        public void handle(AppContext context, String zone, String name) {}
        
        public void handle(String context, ExZone zone, String name) {}
    }
    
}
