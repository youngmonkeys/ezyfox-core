/**
 * 
 */
package com.tvd12.ezyfox.core.serialize;

import com.tvd12.ezyfox.core.transport.Parameters;

/**
 * @author tavandung12
 *
 */
public interface ObjectDeserializer<T> {

    T deserialize(T object, Parameters params);
    
}
