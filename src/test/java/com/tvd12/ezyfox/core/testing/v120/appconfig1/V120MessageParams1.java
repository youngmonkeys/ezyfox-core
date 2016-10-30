/**
 * 
 */
package com.tvd12.ezyfox.core.testing.v120.appconfig1;

import com.tvd12.ezyfox.core.annotation.MessageParams;
import com.tvd12.ezyfox.core.annotation.ParamsMapper;

/**
 * @author tavandung12
 *
 */
@MessageParams
@ParamsMapper(
        deserializer = V120ObjectDeserializer1.class,
        serializer = V120ObjectSerializer1.class
        )
public class V120MessageParams1 {

}
