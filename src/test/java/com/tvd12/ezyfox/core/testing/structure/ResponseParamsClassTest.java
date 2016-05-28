/**
 * 
 */
package com.tvd12.ezyfox.core.testing.structure;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.ResponseParam;
import com.tvd12.ezyfox.core.annotation.ResponseParams;
import com.tvd12.ezyfox.core.structure.GetterMethodCover;
import com.tvd12.ezyfox.core.structure.ResponseParamsClass;

import lombok.Data;

/**
 * @author tavandung12
 *
 */
public class ResponseParamsClassTest {
    
    @Test
    public void testWrapperCase() {
        ResponseParamsClass clazz = new ResponseParamsClass(ClassA.class);
        assertEquals(clazz.methodCount(), 4);
        assertEquals(clazz.getMethod("value").getKey(), "value");
        assertEquals(clazz.getMethod("string").getKey(), "string");
        GetterMethodCover method = clazz.getMethod("classB");
        assertEquals(method.getReturnClass().methodCount(), 2);
    }
    
    @Test
    public void testNoWrapperCase() {
        ResponseParamsClass clazz = new ResponseParamsClass(ClassC.class);
        assertEquals(clazz.methodCount(), 2);
        assertEquals(clazz.getMethod("val").getKey(), "val");
        assertNull(clazz.getMethod("name"));
        assertEquals(clazz.getMethod("str").getMethodName(), "getString");
    }
    
    @Data
    @ResponseParams
    public static class ClassA {
        
        private ClassB classB;
        
        private String name;

        private String value;
        
        public String getString() {
            return "hello";
        }
    }
    
    @Data
    @ResponseParams
    public static class ClassB {
        
        @ResponseParam("val")
        private String value;
        
        private String name;
        
        @ResponseParam("str")
        public String getString() {
            return "hello";
        }
        
    }
    
    @Data
    public static class ClassC {
        
        @ResponseParam("val")
        private String value;
        
        private String name;
        
        @ResponseParam("str")
        public String getString() {
            return "hello";
        }
        
    }
    
}
