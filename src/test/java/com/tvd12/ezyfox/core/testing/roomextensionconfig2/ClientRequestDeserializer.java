/**
 * 
 */
package com.tvd12.ezyfox.core.testing.roomextensionconfig2;

import com.tvd12.ezyfox.core.serialize.ObjectDeserializer;
import com.tvd12.ezyfox.core.transport.Parameters;

/**
 * @author tavandung12
 *
 */
public class ClientRequestDeserializer implements ObjectDeserializer {

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.serialize.ObjectDeserializer#deserialize(java.lang.Object, com.tvd12.ezyfox.core.transport.Parameters)
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T deserialize(Object object, Parameters params) {
        return (T) object;
    }

}
