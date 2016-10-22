/**
 * 
 */
package com.tvd12.ezyfox.core.testing.extensionconfiguration;

import com.tvd12.ezyfox.core.serialize.ObjectSerializer;
import com.tvd12.ezyfox.core.transport.Parameters;
import com.tvd12.ezyfox.core.transport.impl.ParameterWrapper;

/**
 * @author tavandung12
 *
 */
public class ExMessageParameterSerializer implements ObjectSerializer<ExMessagesParameter> {

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.serialize.ObjectSerializer#serialize(java.lang.Object)
     */
    @Override
    public Parameters serialize(ExMessagesParameter object) {
        return new ParameterWrapper();
    }
    
}
