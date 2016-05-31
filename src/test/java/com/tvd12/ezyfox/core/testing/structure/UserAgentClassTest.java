/**
 * 
 */
package com.tvd12.ezyfox.core.testing.structure;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.BuddyVariable;
import com.tvd12.ezyfox.core.annotation.BuddyVariableParam;
import com.tvd12.ezyfox.core.annotation.UserAgent;
import com.tvd12.ezyfox.core.annotation.Variable;
import com.tvd12.ezyfox.core.structure.GetterMethodCover;
import com.tvd12.ezyfox.core.structure.SetterMethodCover;
import com.tvd12.ezyfox.core.structure.UserAgentClass;

import lombok.Data;

/**
 * @author tavandung12
 *
 */
public class UserAgentClassTest {
    
    @Test
    public void test() {
        UserAgentClass clazz = new UserAgentClass(ExUser.class);
        assertEquals(clazz.getBuddyClass().getWrapper().methodCount(), 3);
        assertEquals(clazz.getBuddyClass().getUnwrapper().methodCount(), 3);
        assertEquals(((SetterMethodCover)clazz.getBuddyClass().getWrapper().getMethod("data")).getParameterClass().methodCount(), 1);
        assertEquals(((GetterMethodCover)clazz.getBuddyClass().getUnwrapper().getMethod("data")).getReturnClass().methodCount(), 1);
    }

    @Data
    @UserAgent
    public static class ExUser {
        
        @Variable
        @BuddyVariable
        private long money;
        
        @BuddyVariable
        private ClassA data;
        
        @BuddyVariable
        public void setChips(int chips) {
            
        }
        
        @BuddyVariable
        public int getChips() {
            return 10;
        }
        
    }
    
    @Data
    public static class ClassA {
        @BuddyVariableParam
        private int money;
    }
    
}
