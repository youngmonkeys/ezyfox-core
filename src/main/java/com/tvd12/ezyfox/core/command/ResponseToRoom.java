/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.entities.ApiBaseUser;
import com.tvd12.ezyfox.core.entities.ApiRoom;

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
    ResponseToRoom command(String command);
    
    /**
     * set data object to response to client
     * 
     * @param object data to response
     * @return this pointer
     */
    ResponseToRoom data(Object object);
    
    /**
     * add a key-value data to response to client
     * 
     * @param name name
     * @param value value
     * @return this pointer
     */
    ResponseToRoom param(String name, Object value);
    
    /**
     * the sender
     * 
     * @param user the sender
     * @return this pointer
     */
    ResponseToRoom sender(ApiBaseUser user);
    
    /**
     * the room
     * 
     * @param room the room
     * @return this pointer
     */
    ResponseToRoom room(ApiRoom room);
    
    /**
     * Add user to excluded users list, excluded users will not receive the message
     * 
     * @param user the excluded user
     * @return this pointer
     */
    ResponseToRoom exclude(ApiBaseUser user);
    
}
