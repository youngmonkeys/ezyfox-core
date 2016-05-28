package com.tvd12.ezyfox.core.action;

import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.transport.Parameters;

/**
 * Represent to an action in action chain
 * 
 * @author tavandung12
 *
 */
public interface Action {

    /**
     * delay time
     * @return delay time
     */
    public long delay();
    
    /**
     * execute action
     * 
     * @param context application context
     * @param params parameters
     */
    public void execute(AppContext context, Parameters params);
    
}
