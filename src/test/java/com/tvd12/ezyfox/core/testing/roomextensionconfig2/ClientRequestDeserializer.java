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
public class ClientRequestDeserializer implements ObjectDeserializer<ExClientRequestListener> {

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.serialize.ObjectDeserializer#deserialize(java.lang.Object, com.tvd12.ezyfox.core.transport.Parameters)
     */
    @Override
    public ExClientRequestListener deserialize(ExClientRequestListener object, Parameters params) {
        return object;
    }

}
