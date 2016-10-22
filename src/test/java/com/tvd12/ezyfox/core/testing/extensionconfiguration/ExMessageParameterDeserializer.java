/**
 * 
 */
package com.tvd12.ezyfox.core.testing.extensionconfiguration;

import com.tvd12.ezyfox.core.serialize.ObjectDeserializer;
import com.tvd12.ezyfox.core.transport.Parameters;

/**
 * @author tavandung12
 *
 */
public class ExMessageParameterDeserializer implements ObjectDeserializer<ExMessagesParameter> {

    @Override
    public ExMessagesParameter deserialize(ExMessagesParameter object, Parameters params) {
        return object;
    }
    
}
