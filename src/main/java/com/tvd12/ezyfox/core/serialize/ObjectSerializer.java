/**
 * 
 */
package com.tvd12.ezyfox.core.serialize;

import com.tvd12.ezyfox.core.transport.Parameters;

/**
 * @author tavandung12
 *
 */
public interface ObjectSerializer<T> {

    Parameters serialize(T object);
    
}
