/**
 * 
 */
package com.tvd12.ezyfox.core.model;

/**
 * Support to wrap message content
 * 
 * @author tavandung12
 *
 */
public interface ApiMessage extends ApiBaseMessage {

    /**
     * The room
     * 
     * @return the room
     */
    public <T extends ApiRoom> T room();
    
}
