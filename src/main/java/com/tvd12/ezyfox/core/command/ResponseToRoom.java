/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.model.ApiBaseUser;
import com.tvd12.ezyfox.core.model.ApiRoom;

/**
 * Execute this command to response data to all clients in a room
 * 
 * @author tavandung12
 *
 */
public interface ResponseToRoom extends BaseCommand {

    /**
     * set command to response
     * 
     * @param command command to response 
     * @return this pointer
     */
    public <T extends ResponseToRoom> T command(String command);
    
    /**
     * set data object to response to client
     * 
     * @param object data to response
     * @return this pointer
     */
    public <T extends ResponseToRoom> T data(Object object);
    
    /**
     * add a key-value data to response to client
     * 
     * @param name name
     * @param value value
     * @return this pointer
     */
    public <T extends ResponseToRoom> T param(String name, Object value);
    
    /**
     * the sender
     * 
     * @param user the sender
     * @return this pointer
     */
    public <T extends ResponseToRoom> T sender(ApiBaseUser user);
    
    /**
     * the room
     * 
     * @param room the room
     * @return this pointer
     */
    public <T extends ResponseToRoom> T room(ApiRoom room);
    
    /**
     * Add user to excluded users list, excluded users will not receive the message
     * 
     * @param user the excluded user
     * @return this pointer
     */
    public <T extends ResponseToRoom> T exclude(ApiBaseUser user);
    
}
