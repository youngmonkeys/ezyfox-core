/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.entities.ApiBaseUser;
import com.tvd12.ezyfox.core.entities.ApiRoom;

/**
 * Execute this command to rename a Room. Errors can be fired if:
 * - the new name is already in use
 * - the new name length is out of the range allowed by the Zone configuration
 * - the new name contains bad words (if word filter is configured)
 * 
 * @author tavandung12
 *
 */
public interface ChangeRoomName extends BaseCommand {

    /**
     * Set room's owner user, it can be null if this is called on the server side
     * 
     * @param owner room's owner user
     * @return this pointer
     */
    ChangeRoomName owner(ApiBaseUser owner);
    
    /**
     * Set room to change name
     * 
     * @param targetRoom target room
     * @return this pointer
     */
    ChangeRoomName room(ApiRoom targetRoom);
    
    /**
     * Set new room's name
     * 
     * @param newName new room's name
     * @return this pointer
     */
    ChangeRoomName name(String newName);
    
}
