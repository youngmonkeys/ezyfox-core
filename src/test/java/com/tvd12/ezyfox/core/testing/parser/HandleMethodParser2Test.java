/**
 * 
 */
package com.tvd12.ezyfox.core.testing.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.HandleMethod;
import com.tvd12.ezyfox.core.annotation.parser.HandleMethodParser;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.entities.ApiUser;
import com.tvd12.test.base.BaseTest;

/**
 * @author tavandung12
 *
 */
public class HandleMethodParser2Test extends BaseTest {

    private Class<?> userClass = Class.class;
    private List<Class<?>> gameUserClasses = new ArrayList<>();
    private List<Class<?>> roomClasses = new ArrayList<>();

    @Test(expectedExceptions = {RuntimeException.class})
    public void test() {
        HandleMethodParser.getServerHandleMethod(ClassA.class, 
                roomClasses, userClass, gameUserClasses);
    }
    
    @Test(expectedExceptions = {RuntimeException.class})
    public void test1() {
        HandleMethodParser.getServerHandleMethod(ClassA.class, 
                userClass, gameUserClasses);
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Test
    public void test2() {
        HandleMethodParser.getServerHandleMethod(ClassC.class, 
                userClass, (List)Arrays.asList(MyGameUser.class));
    }
    
    public static class MyGameUser extends ApiUser {
        
    }
    
    public static class ClassA {
        public void exec() {}
    }
    
    public static class ClassB {
        @HandleMethod
        public void execute(AppContext context, ApiUser user) {
            
        }
    }
    
    public static class ClassC extends ClassB {
        
    }
    
}
