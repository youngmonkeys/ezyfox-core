/**
 * 
 */
package com.tvd12.ezyfox.core.testing.roomcontext;

import com.tvd12.ezyfox.core.annotation.ParamsMapper;

/**
 * @author tavandung12
 *
 */
@ParamsMapper(serializer = CurrentStateSerializer.class,
            deserializer = CurrentStateDeserializer.class)
public class CurrentState {

}
