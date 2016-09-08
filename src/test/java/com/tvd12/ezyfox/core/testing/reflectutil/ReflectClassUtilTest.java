package com.tvd12.ezyfox.core.testing.reflectutil;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.exception.ExtensionException;
import com.tvd12.ezyfox.core.reflect.ReflectClassUtil;
import com.tvd12.test.base.BaseTest;

import static org.testng.Assert.*;

import java.lang.reflect.Constructor;

public class ReflectClassUtilTest extends BaseTest {
    
    @Test
    public void newInstanceWithNoParamValidCase() 
            throws ExtensionException {
        ClassA object = (ClassA) ReflectClassUtil.newInstance(ClassA.class);
        assertNotNull(object);
    }
    
    @Test(expectedExceptions = {ExtensionException.class})
    public void newInstanceWithNoParamInvalidCase() throws ExtensionException {
        ReflectClassUtil.newInstance(ClassB.class);
    }
    
    @Override
    public Class<?> getTestClass() {
        return ReflectClassUtil.class;
    }
    
    @Test
    public void testNewInstanceWithParamTypeValidCase() throws ExtensionException {
        assertEquals(ReflectClassUtil.newInstance(ClassC.class, 
                String.class, "Hello").getClass(), ClassC.class);
    }
    
    @Test
    public void testNewInstanceWithParamTypesValidCase() throws ExtensionException {
        assertEquals(ReflectClassUtil.newInstance(ClassC.class, 
                new Class[] {String.class, String.class}, 
                new Object[] {"Hello", "World"}).getClass(), ClassC.class);
    }
    
    @Test(expectedExceptions = {ExtensionException.class})
    public void testNewInstanceWithParamTypesInvalidCase1() throws ExtensionException {
        assertEquals(ReflectClassUtil.newInstance(ClassC.class, 
                new Class[] {String.class, String.class}, 
                new Object[] {"Hello", 1}).getClass(), ClassC.class);
    }
    
    @Test
    public void testGetDefaultConstructor() throws ExtensionException {
        Constructor<ClassA> constructor = 
                ReflectClassUtil.getDefaultConstructor(ClassA.class);
        assertNotNull(constructor);
        ClassA a = ReflectClassUtil.newInstance(constructor);
        assertNotNull(a);
    }
    
    @Test(expectedExceptions = {ExtensionException.class})
    public void testGetConstructorInvalidCase1() throws ExtensionException {
        ReflectClassUtil.getConstructor(ClassC.class, Integer.class);
    }
    
    @Test(expectedExceptions = {ExtensionException.class})
    public void testNewInstanceWithConstructor1() throws ExtensionException {
        
        Constructor<ClassA> constructor = 
                ReflectClassUtil.getDefaultConstructor(ClassA.class);
        assertNotNull(constructor);
        
        ReflectClassUtil.newInstance(constructor, Class.class);
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testGetClassByNameInvalidCase() {
        ReflectClassUtil.getClassByName("@");
    }

    public static class ClassA {
        
    }
    
    private static class ClassB {
        
    }
    
    public static class ClassC {
        public ClassC(String o) {
            
        }
        public ClassC(String o, String z) {
            
        }
    }
    
}
