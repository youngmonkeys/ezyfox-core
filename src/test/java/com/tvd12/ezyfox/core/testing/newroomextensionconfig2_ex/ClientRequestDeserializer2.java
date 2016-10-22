/**
 * 
 */
package com.tvd12.ezyfox.core.testing.newroomextensionconfig2_ex;

import com.tvd12.ezyfox.core.serialize.ObjectDeserializer;
import com.tvd12.ezyfox.core.transport.Parameters;

/**
 * @author tavandung12
 *
 */
public class ClientRequestDeserializer2 implements ObjectDeserializer<ExClientRequestListener3> {

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.serialize.ObjectDeserializer#deserialize(java.lang.Object, com.tvd12.ezyfox.core.transport.Parameters)
     */
    @Override
    public ExClientRequestListener3 deserialize(ExClientRequestListener3 object, Parameters params) {
        return object;
    }

}
