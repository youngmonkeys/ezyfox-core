package com.tvd12.ezyfox.core.command.impl;

import com.tvd12.ezyfox.core.command.AddCommand;
import com.tvd12.ezyfox.core.content.impl.BaseAppContext;

/**
 * @see AddCommand
 * 
 * @author tavandung12
 * Created on Jun 14, 2016
 *
 */
public class AddCommandImpl extends FixedCommand implements AddCommand {
    
    /**
     * @param context
     */
    public AddCommandImpl(BaseAppContext context) {
        super(context);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.AddCommand#add(java.lang.Class, java.lang.Class)
     */
    @Override
    public AddCommand add(Class<?> baseClass, Class<?> implementation) {
        context.addAppCommand(baseClass, implementation);
        return this;
    }

}
