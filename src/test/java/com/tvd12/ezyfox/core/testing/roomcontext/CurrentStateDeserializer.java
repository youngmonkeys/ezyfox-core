/**
 * 
 */
package com.tvd12.ezyfox.core.testing.roomcontext;

import com.tvd12.ezyfox.core.serialize.ObjectDeserializer;
import com.tvd12.ezyfox.core.transport.Parameters;

/**
 * @author tavandung12
 *
 */
public class CurrentStateDeserializer implements ObjectDeserializer {

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.serialize.ObjectDeserializer#deserialize(java.lang.Object, com.tvd12.ezyfox.core.transport.Parameters)
     */
    @SuppressWarnings("unchecked")
    @Override
    public Object deserialize(Object object, Parameters params) {
        return object;
    }

}
