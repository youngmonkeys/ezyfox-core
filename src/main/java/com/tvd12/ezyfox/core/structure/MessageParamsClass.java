/**
 * 
 */
package com.tvd12.ezyfox.core.structure;

import lombok.Getter;

/**
 * Support to hold structure of class that it's instance holds data to response to client.
 * Class must be annotated with {@code MessageParam} annotations
 * 
 * @author tavandung12
 *
 */
public class MessageParamsClass {

    @Getter
    private MessageParamsClassWrapper wrapper;
    @Getter
    private MessageParamsClassUnwrapper unwrapper;
    
    public MessageParamsClass(Class<?> clazz) {
        wrapper = new MessageParamsClassWrapper(clazz);
        unwrapper = new MessageParamsClassUnwrapper(clazz);
    }
}
