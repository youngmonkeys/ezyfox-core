/**
 * 
 */
package com.tvd12.ezyfox.core.testing.structure;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.BuddyVariable;
import com.tvd12.ezyfox.core.annotation.BuddyVariableParam;
import com.tvd12.ezyfox.core.structure.BuddyClassUnwrapper;
import com.tvd12.test.base.BaseTest;

import lombok.Data;

import static org.testng.Assert.*;

/**
 * @author tavandung12
 *
 */
public class BuddyClassUnwrapperTest extends BaseTest {

    @Test
    public void test() {
        BuddyClassUnwrapper unwrapper = new BuddyClassUnwrapper(ClassA.class);
        assertEquals(unwrapper.methodCount(), 3);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.test.base.BaseTest#getTestClass()
     */
    @Override
    public Class<?> getTestClass() {
        return BuddyClassUnwrapper.class;
    }
    
    @Data
    public static class ClassA {
        @BuddyVariable
        private String a0;
        @BuddyVariable
        private String a1;
        @BuddyVariable
        private ClassB classB;
    }
    
    @Data
    public static class ClassB {
        @BuddyVariableParam
        private String a0;
        
        @BuddyVariableParam
        public String getA1() {
            return "a1";
        }
    }
    
}
