/**
 * 
 */
package com.tvd12.ezyfox.core.testing.structure;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.BuddyVariable;
import com.tvd12.ezyfox.core.annotation.UserAgent;
import com.tvd12.ezyfox.core.annotation.Variable;
import com.tvd12.ezyfox.core.structure.UserAgentClass;

import lombok.Data;

import static org.testng.Assert.*;

/**
 * @author tavandung12
 *
 */
public class UserAgentClassTest {
    
    @Test
    public void test() {
        UserAgentClass clazz = new UserAgentClass(ExUser.class);
        assertEquals(clazz.getBuddyUnwrapper().methodCount(), 1);
    }

    @Data
    @UserAgent
    public static class ExUser {
        
        @Variable
        @BuddyVariable
        private long money;
        
    }
    
}
