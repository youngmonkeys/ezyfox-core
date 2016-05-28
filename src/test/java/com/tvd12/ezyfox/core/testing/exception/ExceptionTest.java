package com.tvd12.ezyfox.core.testing.exception;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.exception.AnnotationException;
import com.tvd12.ezyfox.core.exception.ExtensionException;

public class ExceptionTest {

    @Test(expectedExceptions = {AnnotationException.class})
    public void testAnnotationException1() throws AnnotationException {
        throw new AnnotationException();
    }
    
    @Test(expectedExceptions = {AnnotationException.class})
    public void testAnnotationException2() throws AnnotationException {
        throw new AnnotationException("msg");
    }
    
    @Test(expectedExceptions = {AnnotationException.class})
    public void testAnnotationException3() throws AnnotationException {
        throw new AnnotationException("msg", new NullPointerException());
    }
    
    @Test(expectedExceptions = {ExtensionException.class})
    public void testExtensionException1() throws ExtensionException {
        throw new ExtensionException();
    }
    
    @Test(expectedExceptions = {ExtensionException.class})
    public void testExtensionException2() throws ExtensionException {
        throw new ExtensionException("msg");
    }
    
    @Test(expectedExceptions = {ExtensionException.class})
    public void testExtensionException3() throws ExtensionException {
        throw new ExtensionException("msg", new NullPointerException());
    }
    
}
