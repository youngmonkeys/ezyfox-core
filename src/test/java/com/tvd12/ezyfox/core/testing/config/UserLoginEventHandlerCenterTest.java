/**
 * 
 */
package com.tvd12.ezyfox.core.testing.config;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.config.UserLoginEventHandlerCenter;
import com.tvd12.ezyfox.core.constants.ServerEvent;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.structure.UserLoginHandlerClass;
import com.tvd12.test.base.BaseTest;

/**
 * @author tavandung12
 *
 */
public class UserLoginEventHandlerCenterTest extends BaseTest {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void test() {
        UserLoginEventHandlerCenter center = new UserLoginEventHandlerCenter();
        List<UserLoginHandlerClass> classes = 
                center.addHandlers(
                        (List)Lists.newArrayList(ClassA.class, ClassB.class),
                        String.class,
                        String.class);
        assertEquals(classes.size(), 2);
    }
    
    @ServerEventHandler(event = ServerEvent.USER_LOGIN, priority = 2)
    public static class ClassA {
        
        public void handle(AppContext context, String usrname, String password) {
            
        }
        
    }
    
    @ServerEventHandler(event = ServerEvent.USER_LOGIN, priority = 1)
    public static class ClassB {
        
        public void handle(AppContext context, String username, String password) {
            
        }
        
    }
    
}
