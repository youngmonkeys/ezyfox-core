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
public class PokerUserSerializer implements ObjectSerializer {

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.serialize.ObjectSerializer#serialize(java.lang.Object)
     */
    @Override
    public Parameters serialize(Object object) {
        return new ParameterWrapper();
    }

}
