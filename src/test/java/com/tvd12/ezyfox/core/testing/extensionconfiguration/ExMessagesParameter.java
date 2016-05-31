/**
 * 
 */
package com.tvd12.ezyfox.core.testing.extensionconfiguration;

import com.tvd12.ezyfox.core.annotation.MessageParam;
import com.tvd12.ezyfox.core.annotation.MessageParams;

import lombok.Data;

/**
 * @author tavandung12
 *
 */
@Data
@MessageParams
public class ExMessagesParameter {

    @MessageParam
    private String name;
    
}
