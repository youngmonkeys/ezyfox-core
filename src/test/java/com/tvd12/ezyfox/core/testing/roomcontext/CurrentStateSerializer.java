/**
 * 
 */
package com.tvd12.ezyfox.core.testing.roomcontext;

import com.tvd12.ezyfox.core.serialize.ObjectSerializer;
import com.tvd12.ezyfox.core.transport.Parameters;
import com.tvd12.ezyfox.core.transport.impl.ParameterWrapper;

/**
 * @author tavandung12
 *
 */
public class CurrentStateSerializer implements ObjectSerializer<CurrentState> {

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.serialize.ObjectSerializer#serialize(java.lang.Object)
     */
    @Override
    public Parameters serialize(CurrentState object) {
        return new ParameterWrapper();
    }
    
}
