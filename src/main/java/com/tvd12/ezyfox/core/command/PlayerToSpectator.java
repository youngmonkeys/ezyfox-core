/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.model.ApiBaseUser;
import com.tvd12.ezyfox.core.model.ApiRoom;

/**
 * Execute this command to turns a player in a Game Room to a spectator.
 * 
 * @author tavandung12
 *
 */
public interface PlayerToSpectator extends BaseCommand {
    
    /**
     * user to turn to spectator
     * 
     * @param user user to turn to spectator
     * @return this pointer
     */
    public <T extends PlayerToSpectator> T user(ApiBaseUser user);
    
    /**
     * the Room in which the player will be turned into a spectator
     * 
     * @param room the Room in which the player will be turned into a spectator
     * @return this pointer
     */
    public <T extends PlayerToSpectator> T room(ApiRoom room);
    
    /**
     * if true send an update to the client (recommended)
     * 
     * @param value fire client event or not
     * @return this pointer
     */
    public <T extends PlayerToSpectator> T fireClientEvent(boolean value);
    
    /**
     * if true fire a server side event
     * 
     * @param value fire a server side event or not
     * @return this pointer
     */
    public <T extends PlayerToSpectator> T fireServerEvent(boolean value);
    
}
