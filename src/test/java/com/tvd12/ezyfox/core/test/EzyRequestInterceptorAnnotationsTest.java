package com.tvd12.ezyfox.core.test;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.EzyRequestInterceptor;
import com.tvd12.ezyfox.core.util.EzyRequestInterceptorAnnotations;
import com.tvd12.test.base.BaseTest;

public class EzyRequestInterceptorAnnotationsTest extends BaseTest {

    @Override
    public Class<?> getTestClass() {
        return EzyRequestInterceptorAnnotations.class;
    }

    @Test
    public void test() {
        assert EzyRequestInterceptorAnnotations.getPriority(    InterceptorA.class) == 0;
        assert EzyRequestInterceptorAnnotations.getPriority(    InterceptorB.class) == 1;
    }

    public static class InterceptorA {}

    @EzyRequestInterceptor(priority = 1)
    public static class InterceptorB {}
}
