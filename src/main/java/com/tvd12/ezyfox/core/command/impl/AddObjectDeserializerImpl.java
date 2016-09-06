/**
 * 
 */
package com.tvd12.ezyfox.core.command.impl;

import com.tvd12.ezyfox.core.command.AddObjectDeserializer;
import com.tvd12.ezyfox.core.content.impl.BaseAppContext;
import com.tvd12.ezyfox.core.serialize.ObjectDeserializer;

/**
 * @author tavandung12
 *
 */
public class AddObjectDeserializerImpl extends FixedCommand implements AddObjectDeserializer {

    /**
     * @param context
     */
    public AddObjectDeserializerImpl(BaseAppContext context) {
        super(context);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.AddObjectDeserializer#add(java.lang.Class, com.tvd12.ezyfox.core.serialize.ObjectDeserializer)
     */
    @Override
    public AddObjectDeserializer add(Class<?> clazz, ObjectDeserializer serializer) {
        context.addObjectDeserializer(clazz, serializer);
        return this;
    }

    
    
}
