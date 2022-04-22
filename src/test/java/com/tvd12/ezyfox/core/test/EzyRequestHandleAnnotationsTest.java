package com.tvd12.ezyfox.core.test;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.EzyDoHandle;
import com.tvd12.ezyfox.core.annotation.EzyRequestController;
import com.tvd12.ezyfox.core.util.EzyDoHandleAnnotations;

public class EzyRequestHandleAnnotationsTest {

    @Test
    public void test() throws Exception {
        assert "a".equals(EzyDoHandleAnnotations.getCommand(
            TestClientRequestController.class.getDeclaredMethod("handle1")
                .getAnnotation(EzyDoHandle.class)));
        assert "b".equals(EzyDoHandleAnnotations.getCommand(
            TestClientRequestController.class.getDeclaredMethod("handle2")
                .getAnnotation(EzyDoHandle.class)));
    }

    @EzyRequestController
    public static class TestClientRequestController {

        @EzyDoHandle("a")
        public void handle1() {}

        @EzyDoHandle(command = "b")
        public void handle2() {}
    }
}
