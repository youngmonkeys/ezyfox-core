/**
 * 
 */
package com.tvd12.ezyfox.core.command.impl;

import com.tvd12.ezyfox.core.command.AddObjectSerializer;
import com.tvd12.ezyfox.core.content.impl.BaseAppContext;
import com.tvd12.ezyfox.core.serialize.ObjectSerializer;

/**
 * @author tavandung12
 *
 */
public class AddObjectSerializerImpl extends FixedCommand implements AddObjectSerializer {

    /**
     * @param context
     */
    public AddObjectSerializerImpl(BaseAppContext context) {
        super(context);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.AddObjectSerializer#add(java.lang.Class, com.tvd12.ezyfox.core.serialize.ObjectSerializer)
     */
    @Override
    public AddObjectSerializer add(Class<?> clazz, ObjectSerializer serializer) {
        context.addObjectSerializer(clazz, serializer);
        return this;
    }

    
    
}
