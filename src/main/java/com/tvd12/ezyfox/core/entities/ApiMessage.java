/**
 * 
 */
package com.tvd12.ezyfox.core.entities;

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
     * @param <T> the room type
     * @return the room
     */
    public <T extends ApiRoom> T room();
    
}
