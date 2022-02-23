package com.tvd12.ezyfox.core.test;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.EzyEventHandler;
import com.tvd12.ezyfox.core.util.EzyEventHandlerLists;
import com.tvd12.test.assertion.Asserts;
import com.tvd12.test.base.BaseTest;

public class EzyEventHandlerListsTest extends BaseTest {
    
    @Test
    public void test() {
        // given
        A a = new A();
        Object o = new Object();
        B b = new B();
        List<Object> handlers = Arrays.asList(b, o, a);
        
        // when
        EzyEventHandlerLists.sortEventHandlersByPriority(handlers);
        
        // then
        Asserts.assertEquals(handlers, Arrays.asList(a, o, b));
    }

    public java.lang.Class<?> getTestClass() {
        return EzyEventHandlerLists.class;
    }
    
    @EzyEventHandler(priority = Integer.MIN_VALUE)
    public static class A {}
    
    @EzyEventHandler(priority = Integer.MAX_VALUE)
    public static class B {}
}
