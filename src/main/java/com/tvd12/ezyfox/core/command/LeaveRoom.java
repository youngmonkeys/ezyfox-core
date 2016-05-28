/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.model.ApiBaseUser;
import com.tvd12.ezyfox.core.model.ApiRoom;

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
    public <T extends LeaveRoom> T user(ApiBaseUser user);
    
    /**
     * Name of user to leave room
     * 
     * @param username name of user to leave room
     * @return
     */
    public <T extends LeaveRoom> T user(String username);
    
    /**
     * Set room to leave, optionally specify a Room that should be left if roomToJoin is successfully joined
     * 
     * @param room room to leave
     * @return this pointer
     */
    public <T extends LeaveRoom> T room(ApiRoom room);
    
    /**
     * Set room to leave, optionally specify a Room that should be left if roomToJoin is successfully joined
     * 
     * @param roomName room to leave
     * @return this pointer
     */
    public <T extends LeaveRoom> T room(String roomName);
    
    /**
     * fires client side Event
     * 
     * @param value true or false
     * @return this pointer
     */
    public <T extends LeaveRoom> T fireClientEvent(boolean value);
    
    /**
     * fires server side Event
     * 
     * @param value true or false
     * @return this pointer
     */
    public <T extends LeaveRoom> T fireServerEvent(boolean value);
    
}
