/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.entities.ApiBaseUser;
import com.tvd12.ezyfox.core.entities.ApiRoom;

/**
 * Execute this command to removes a User from a previously joined Room
 * 
 * @author tavandung12
 *
 */
public interface LeaveRoom extends BaseCommand {
    
    /**
     * User to leave room
     * 
     * @param user user to leave room
     * @return this pointer
     */
    LeaveRoom user(ApiBaseUser user);
    
    /**
     * Name of user to leave room
     * 
     * @param username name of user to leave room
     * @return
     */
    LeaveRoom user(String username);
    
    /**
     * Set room to leave, optionally specify a Room that should be left if roomToJoin is successfully joined
     * 
     * @param room room to leave
     * @return this pointer
     */
    LeaveRoom room(ApiRoom room);
    
    /**
     * Set room to leave, optionally specify a Room that should be left if roomToJoin is successfully joined
     * 
     * @param roomName room to leave
     * @return this pointer
     */
    LeaveRoom room(String roomName);
    
    /**
     * fires client side Event
     * 
     * @param value true or false
     * @return this pointer
     */
    LeaveRoom fireClientEvent(boolean value);
    
    /**
     * fires server side Event
     * 
     * @param value true or false
     * @return this pointer
     */
    LeaveRoom fireServerEvent(boolean value);
    
}
