/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.model.ApiBaseUser;
import com.tvd12.ezyfox.core.model.ApiRoom;

/**
 * Execute this command to move user to a room
 * 
 * @author tavandung12
 *
 */
public interface JoinRoom extends BaseCommand {
    
    /**
     * Set user agent to join room
     * 
     * @param user user to join room
     * @return this pointer
     */
    public <T extends JoinRoom> T user(ApiBaseUser user);
    
    /**
     * Set user's name to join room
     * 
     * @param username user's name to join room
     * @return
     */
    public <T extends JoinRoom> T user(String username);
    
    /**
     * Set room to join
     * 
     * @param roomName the room to join
     * @return this pointer
     */
    public <T extends JoinRoom> T roomToJoin(String roomName);
    
    /**
     * Set room to join
     * 
     * @param room the room to join
     * @return this pointer
     */
    public <T extends JoinRoom> T roomToJoin(ApiRoom room);
    
    /**
     * Set an optional password if the room requires it. Use null if no password is needed
     * 
     * @param password room's password
     * @return this pointer
     */
    public <T extends JoinRoom> T password(String password);
    
    /**
     * join the room as spectator, in case of a game room
     * 
     * @param value true or false
     * @return this pointer
     */
    public <T extends JoinRoom> T asSpectator(boolean value);
    
    /**
     * Set room to leave, optionally specify a Room that should be left if roomToJoin is successfully joined
     * 
     * @param roomName room to leave
     * @return this pointer
     */
    public <T extends JoinRoom> T roomToLeave(String roomName);
    
    /**
     * Set room to leave, optionally specify a Room that should be left if roomToJoin is successfully joined
     * 
     * @param room room to leave
     * @return this pointer
     */
    public <T extends JoinRoom> T roomToLeave(ApiRoom room);
    
    /**
     * fires client side Event
     * 
     * @param value true or false
     * @return this pointer
     */
    public <T extends JoinRoom> T fireClientEvent(boolean value);
    
    /**
     * fires server side Event
     * 
     * @param value true or false
     * @return this pointer
     */
    public <T extends JoinRoom> T fireServerEvent(boolean value);
    
}
