package com.tvd12.ezyfox.core.testing.responsehandlerclasss;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.ResponseParam;
import com.tvd12.ezyfox.core.structure.ResponseHandlerClass;
import com.tvd12.test.base.BaseTest;

import lombok.Data;

import static org.testng.Assert.*;

import java.util.List;

public class ResponseHandlerClassTest extends BaseTest {

    @Test
    public void testValidCase() {
        ResponseHandlerClass clazz = new ResponseHandlerClass(ClassA.class);
        assertEquals(clazz.methodCount(), 5);
    }
    
    @Override
    public Class<?> getTestClass() {
        return ResponseHandlerClass.class;
    }
    
    @Data
    public static class ClassA {
        
        @ResponseParam
        private int value1;
        
        @ResponseParam
        private int value2;
        
        @ResponseParam
        private ClassB classB;
        
        @ResponseParam
        public String getDream() {
            return "dream";
        }
        
        @ResponseParam
        public String getWar() {
            return "war";
        }
        
    }
    
    @Data
    public static class ClassB {
        @ResponseParam
        private ClassA classA;
        
        @ResponseParam
        public List<Integer> getValue() {return null;};
    }
    
}
