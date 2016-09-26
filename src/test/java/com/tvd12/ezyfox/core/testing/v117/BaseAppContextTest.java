/**
 * 
 */
package com.tvd12.ezyfox.core.testing.v117;

import static org.testng.Assert.*;

import java.lang.reflect.Constructor;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.content.impl.BaseAppContext;
import com.tvd12.ezyfox.core.testing.v117.roomconfig4.V117MessageParams4;
import com.tvd12.ezyfox.core.testing.v117.roomconfig4.V117ResponseParams4;

/**
 * @author tavandung12
 *
 */
public class BaseAppContextTest {
    
    @Test
    public void test() {
        BaseAppContext context = new AppContext1Impl();
        context.initialize(V117EntryPoint2.class);
        assertNotNull(context.getResponseParamsClass(V117ResponseParams4.class));
        assertNotNull(context.getMessageParamsClass(V117MessageParams4.class));
        assertTrue(context.getGameUserAgentClasses().size() >= 2);
    }

    
    public static class AppContext1Impl extends BaseAppContext {

        /* (non-Javadoc)
         * @see com.tvd12.ezyfox.core.content.impl.BaseAppContext#getCommandConstructor(java.lang.Class)
         */
        @Override
        protected Constructor<?> getCommandConstructor(Class<?> commandClass) {
            try {
                return commandClass.getConstructor();
            }
            catch(Exception e) {
                return null;
            }
        }

        /* (non-Javadoc)
         * @see com.tvd12.ezyfox.core.content.impl.BaseAppContext#getCommand(java.lang.Class)
         */
        @Override
        protected <T> T getCommand(Class<T> clazz) {
            return null;
        }
        
    }
}
