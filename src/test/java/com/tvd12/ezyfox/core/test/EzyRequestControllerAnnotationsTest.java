package com.tvd12.ezyfox.core.test;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.EzyRequestController;
import com.tvd12.ezyfox.core.util.EzyRequestControllerAnnotations;

public class EzyRequestControllerAnnotationsTest {

    @Test
    public void test() {
        assert "a".equals(EzyRequestControllerAnnotations
            .getGroup(TestClientRequestController1.class));
        assert "b".equals(EzyRequestControllerAnnotations
            .getGroup(TestClientRequestController2.class));
    }

    @EzyRequestController("a")
    public static class TestClientRequestController1 {}

    @EzyRequestController(value = " ", group = "b")
    public static class TestClientRequestController2 {}
}
