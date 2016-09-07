/**
 * 
 */
package com.tvd12.ezyfox.core.testing.context;

import java.lang.reflect.Constructor;
import java.util.concurrent.ConcurrentHashMap;

import com.tvd12.ezyfox.core.content.impl.BaseAppContext;

/**
 * @author tavandung12
 *
 */
public class ExAppContextImpl extends BaseAppContext {
    
    /**
     * 
     */
    public ExAppContextImpl() {
        commands = new ConcurrentHashMap<>();
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.content.impl.BaseAppContext#initialize(java.lang.Class)
     */
    @Override
    public void initialize(Class<?> entryPoint) {
        super.initialize(entryPoint);
        commands.put(PokerUser.class.getName(), getCommandConstructor(PokerUser.class));
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.content.impl.BaseAppContext#getCommandConstructor(java.lang.Class)
     */
    @Override
    protected Constructor<?> getCommandConstructor(Class<?> clazz) {
        try {
            return clazz.getConstructor();
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.content.impl.BaseAppContext#getCommand(java.lang.Class)
     */
    @Override
    protected <T> T getCommand(Class<T> clazz) {
        if(!commands.containsKey(clazz.getName()))
            throw new RuntimeException("has no command map to " + clazz);
        return getCommand(commands, clazz);
    }

}
