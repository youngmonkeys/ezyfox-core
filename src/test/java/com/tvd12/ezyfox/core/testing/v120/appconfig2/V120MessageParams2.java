/**
 * 
 */
package com.tvd12.ezyfox.core.testing.v120.appconfig2;

import com.tvd12.ezyfox.core.annotation.MessageParams;
import com.tvd12.ezyfox.core.annotation.ParamsMapper;

/**
 * @author tavandung12
 *
 */
@MessageParams
@ParamsMapper(
        deserializer = V120ObjectDeserializer2.class,
        serializer = V120ObjectSerializer2.class
        )
public class V120MessageParams2 {

}
