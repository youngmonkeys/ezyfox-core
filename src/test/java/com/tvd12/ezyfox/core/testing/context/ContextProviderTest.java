package com.tvd12.ezyfox.core.testing.context;

import static org.mockito.Mockito.mock;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.content.ContextProvider;
import com.tvd12.ezyfox.core.reflect.ReflectFieldUtil;
import com.tvd12.test.base.BaseTest;

public class ContextProviderTest extends BaseTest {

    @Test
    public void testValidCase() {
        assertNotNull(ContextProvider.getInstance());
        ContextProvider instance = ContextProvider.getInstance();
        AppContext context = mock(AppContext.class);
        assertNotNull(instance.addContext(ClassA.class, context));
        assertNotNull(instance.getContext(ClassA.class));
        assertEquals(context, instance.addContext(ClassA.class, context));
    }
    
    @Test(expectedExceptions = {RuntimeException.class})
    public void getContextInvalidCaseTest() {
        ContextProvider.getInstance().getContext(Class.class);
    }
    
//    @Test
    public void multiThreadTest() throws Exception {
        final Field field = ReflectFieldUtil.getField("INSTANCE", ContextProvider.class);
        field.setAccessible(true);
        Thread[] threads = new Thread[10];
        for(int i = 0 ; i < threads.length ; i++) {
            threads[i] = new Thread() {
                @Override
                public void run() {
                    try {
                        setFinalStatic(field, null);
                        ContextProvider.getInstance();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
        }
        threads[0].start();
        threads[1].start();
        threads[2].start();
        threads[3].start();
        threads[4].start();
        threads[5].start();
        threads[6].start();
        threads[7].start();
        threads[8].start();
        threads[9].start();
    }
    
    static void setFinalStatic(Field field, Object newValue) throws Exception {
        field.setAccessible(true);
   
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
   
        field.set(null, newValue);
     } 
    
    public static class ClassA {
        
    }
    
}
