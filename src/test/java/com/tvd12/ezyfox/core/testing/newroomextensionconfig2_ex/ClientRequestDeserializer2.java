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
public class ClientRequestDeserializer2 implements ObjectDeserializer {

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.serialize.ObjectDeserializer#deserialize(java.lang.Object, com.tvd12.ezyfox.core.transport.Parameters)
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T deserialize(Object object, Parameters params) {
        return (T) object;
    }

}
