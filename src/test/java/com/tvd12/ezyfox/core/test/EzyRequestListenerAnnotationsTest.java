package com.tvd12.ezyfox.core.test;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.EzyRequestListener;
import com.tvd12.ezyfox.core.util.EzyRequestListenerAnnotations;
import com.tvd12.test.base.BaseTest;

public class EzyRequestListenerAnnotationsTest extends BaseTest {

    @Override
    public Class<?> getTestClass() {
        return EzyRequestListenerAnnotations.class;
    }

    @Test
    public void test() {
        assert EzyRequestListenerAnnotations.getCommand(
            A.class.getAnnotation(EzyRequestListener.class)
        ).equals("hello");
        assert EzyRequestListenerAnnotations.getCommand(
            B.class.getAnnotation(EzyRequestListener.class)
        ).equals("world");
    }

    @EzyRequestListener("hello")
    public static class A {}

    @EzyRequestListener(command = "world")
    public static class B {}
}
