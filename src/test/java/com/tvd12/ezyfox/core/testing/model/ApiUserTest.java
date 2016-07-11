/**
 * 
 */
package com.tvd12.ezyfox.core.testing.model;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.command.UserInfo;
import com.tvd12.ezyfox.core.entities.ApiGameUser;
import com.tvd12.ezyfox.core.entities.ApiUser;
import com.tvd12.test.base.BaseTest;
import static org.mockito.Mockito.*;

/**
 * @author tavandung12
 *
 */
public class ApiUserTest extends BaseTest {
    
    @Test
    public void test() {
        UserInfo command = mock(UserInfo.class);
        User1 user = new User1();
        user.setCommand(command);
        user.addChild(new BUser1());
        user.addChild(new BUser2());
        assertEquals(user.getChild(BUser2.class).getClass(), BUser2.class);
        when(command.getPlayerId()).thenReturn(1);
        assertEquals(user.getCommand().getPlayerId(), 1);
    }
    
    @Test(expectedExceptions = {IllegalStateException.class})
    public void test1() {
        User1 user = new User1();
        user.addChild(new BUser1());
        user.addChild(new BUser2());
        user.getChild(BUser3.class);
    }
    
    public static class User1 extends ApiUser {
        
    }
    
    public static class BUser1 extends ApiGameUser {
        
    }
    
    public static class BUser2 extends ApiGameUser {
        
    }
    
    public static class BUser3 extends ApiGameUser {
        
    }
    
}
