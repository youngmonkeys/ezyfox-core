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
public class ExampleRoomDeserializer implements ObjectDeserializer<ExampleRoom> {

    @Override
    public ExampleRoom deserialize(ExampleRoom object, Parameters params) {
        return object;
    }

}
