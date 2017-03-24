/**
 * 
 */
package com.tvd12.ezyfox.core.testing.entities;

import static org.testng.Assert.*;

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
        user.setName("user1");
        user.setCommand(command);
        BUser1 b1 = new BUser1();
        BUser2 b2 = new BUser2();
        b1.setParent(user);
        b2.setParent(user);
        user.addChild(b1);
        user.addChild(b2);
        assertEquals(user.getChild(BUser2.class).getClass(), BUser2.class);
        when(command.getPlayerId()).thenReturn(1);
        assertEquals(user.getCommand().getPlayerId(), 1);

        assertTrue(user.equals(user));
        assertFalse(user.equals(b1));
        User1 user2 = new User1();
        user2.setName("user1");
        assertTrue(user.equals(user2));
        assertEquals(new User1(), new User1());
        assertFalse(user.equals(null));
        
        assertEquals(user.hashCode(), user.hashCode());
        
        user2 = new User1();
        user2.setName("user2");
        BUser3 b3 = new BUser3();
        b3.setParent(user2);
        user2.addChild(b3);
        
        BUser3 b4 = new BUser3();
        b4.setParent(user2);
        user2.addChild(b4);
        
        assertFalse(b3.equals(user2));
        assertFalse(b3.equals(null));
        assertFalse(b3.equals(b1));
        assertTrue(b3.equals(b4));
        assertTrue(b3.equals(b3));
        assertEquals(b3.hashCode(), b4.hashCode());
        
        user2.setProperty(ApiUserTest.class, this);
        assertEquals(user2.get(getClass()), this);
        
    }
    
    
    @Test(expectedExceptions = {IllegalStateException.class})
    public void test1() {
        User1 user = new User1();
        user.setName("user1");
        BUser1 b1 = new BUser1();
        BUser2 b2 = new BUser2();
        b1.setParent(user);
        b2.setParent(user);
        user.addChild(b1);
        user.addChild(b2);
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
