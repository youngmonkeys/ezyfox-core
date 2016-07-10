/**
 * 
 */
package com.tvd12.ezyfox.core.testing.structure;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.RequestParam;
import com.tvd12.ezyfox.core.annotation.ResponseParam;
import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.constants.ServerEvent;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.structure.UserLoginHandlerClass;
import com.tvd12.test.base.BaseTest;

import lombok.Data;

import static org.testng.Assert.*;

/**
 * @author tavandung12
 *
 */
public class UserLoginHandlerClassTest extends BaseTest {
    
    @Test
    public void test() {
        UserLoginHandlerClass handler = new UserLoginHandlerClass(
                ClassA.class, String.class, String.class);
        assertEquals(handler.getRequestListenerClass().methodCount(), 1);
        assertEquals(handler.getResponseHandlerClass().methodCount(), 1);
    }

    @Data
    @ServerEventHandler(event = ServerEvent.USER_LOGIN)
    public static class ClassA {
        @RequestParam("1")
        @ResponseParam("1")
        private String name = "dung";
        
        public void handle(AppContext context, String username, String password) {
            
        }
    }
    
}
