package com.tvd12.ezyfox.core.test;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.EzyEventHandler;
import com.tvd12.ezyfox.core.util.EzyEventHandlerAnnotations;
import com.tvd12.test.base.BaseTest;

public class EzyEventHandlerAnnotationsTest extends BaseTest {

    @Override
    public Class<?> getTestClass() {
        return EzyEventHandlerAnnotations.class;
    }
    
    @Test
    public void test() {
        assert EzyEventHandlerAnnotations.getEvent(
                A.class.getAnnotation(EzyEventHandler.class)
            ).equals("hello");
        assert EzyEventHandlerAnnotations.getEvent(
                B.class.getAnnotation(EzyEventHandler.class)
            ).equals("world");
    }
    
    @EzyEventHandler("hello")
    public static class A {
        
    }
    
    @EzyEventHandler(event = "world")
    public static class B {
        
    }
    
}
