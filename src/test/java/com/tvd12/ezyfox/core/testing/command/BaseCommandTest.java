/**
 * 
 */
package com.tvd12.ezyfox.core.testing.command;

import com.tvd12.ezyfox.core.content.impl.BaseAppContext;
import com.tvd12.ezyfox.core.testing.context.AppEntryPoint;
import com.tvd12.ezyfox.core.testing.context.ExAppContextImpl;
import com.tvd12.test.base.BaseTest;

/**
 * @author tavandung12
 *
 */
public class BaseCommandTest extends BaseTest {
    
    protected BaseAppContext context;
    
    public BaseCommandTest() {
        context = newAppContext();
    }

    public BaseAppContext newAppContext() {
        BaseAppContext answer = new ExAppContextImpl();
        answer.initialize(AppEntryPoint.class);
        return answer;
    }
    
}
