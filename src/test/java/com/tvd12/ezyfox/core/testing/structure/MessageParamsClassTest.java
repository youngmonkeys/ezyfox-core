/**
 * 
 */
package com.tvd12.ezyfox.core.testing.structure;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.MessageParam;
import com.tvd12.ezyfox.core.annotation.MessageParams;
import com.tvd12.ezyfox.core.structure.GetterMethodCover;
import com.tvd12.ezyfox.core.structure.MessageParamsClass;
import com.tvd12.ezyfox.core.structure.MessageParamsClassUnwrapper;
import com.tvd12.ezyfox.core.structure.MessageParamsClassWrapper;
import com.tvd12.ezyfox.core.structure.SetterMethodCover;

import lombok.Data;

/**
 * @author tavandung12
 *
 */
public class MessageParamsClassTest {
    
    @Test
    public void testWrapperCase() {
        MessageParamsClass clazz = new MessageParamsClass(ClassA.class);
        MessageParamsClassUnwrapper unwrapper = clazz.getUnwrapper();
        assertEquals(unwrapper.methodCount(), 4);
        assertEquals(unwrapper.getMethod("value").getKey(), "value");
        assertEquals(unwrapper.getMethod("string").getKey(), "string");
        GetterMethodCover method = unwrapper.getMethod("classB");
        assertEquals(method.getReturnClass().methodCount(), 1);
        
        MessageParamsClassWrapper wrapper = clazz.getWrapper();
        assertEquals(wrapper.methodCount(), 4);
        assertEquals(wrapper.getMethod("value").getKey(), "value");
        assertEquals(wrapper.getMethod("string").getKey(), "string");
        SetterMethodCover method1 = wrapper.getMethod("classB");
        assertEquals(method1.getParameterClass().methodCount(), 1);
    }
    
    @Test
    public void testNoWrapperCase() {
        MessageParamsClass clazz = new MessageParamsClass(ClassC.class);
        MessageParamsClassUnwrapper unwrapper = clazz.getUnwrapper();
        assertEquals(unwrapper.methodCount(), 1);
        assertNull(unwrapper.getMethod("name"));
        assertEquals(unwrapper.getMethod("str").getMethodName(), "getString");
        
        MessageParamsClassWrapper wrapper = clazz.getWrapper();
        assertEquals(wrapper.methodCount(), 1);
        assertNull(wrapper.getMethod("name"));
        assertEquals(wrapper.getMethod("str").getMethodName(), "setString");
    }
    
    @Data
    @MessageParams
    public static class ClassA {
        
        private ClassB classB;
        
        private String name;

        private String value;
        
        public String getString() {
            return "hello";
        }
        
        public void setString(String str) {
        }
    }
    
    @Data
    @MessageParams
    public static class ClassB {
        
        @MessageParam("val")
        private String value;
        
        private String name;
        
        public String getString() {
            return "hello";
        }
        
    }
    
    @Data
    public static class ClassC {
        
        private String value;
        
        private String name;
        
        @MessageParam("str")
        public String getString() {
            return "hello";
        }
        
        @MessageParam("str")
        public void setString(String str) {
        }
        
    }
    
}
