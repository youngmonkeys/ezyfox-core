/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.entities.ApiBaseUser;
import com.tvd12.ezyfox.core.entities.ApiRoom;

/**
 * Execute this command to changes the capacity (max number of Users and Spectators) in the Room. 
 * In case the Room size is shrunk extra players or spectators will not be kicked out of the Room.

 * 
 * @author tavandung12
 *
 */
public interface ChangeRoomCapacity extends BaseCommand {

    /**
     * Set owner of the Room, the requester must be owner of the Room or SuperUser
     * 
     * @param owner room's owner
     * @return this pointer
     */
    ChangeRoomCapacity owner(ApiBaseUser owner);
    
    /**
     * Room to change capacity
     * 
     * @param targetRoom target room
     * @return this pointer
     */
    ChangeRoomCapacity room(ApiRoom targetRoom);
    
    /**
     * Set new max users value
     * 
     * @param newMaxUsers new max users value
     * @return this pointer
     */
    ChangeRoomCapacity maxUsers(int newMaxUsers);
    
    /**
     * Set new max spectators value
     * 
     * @param newMaxSpactators new max spactators value
     * @return this pointer
     */
    ChangeRoomCapacity maxSpectators(int newMaxSpactators);
    
}
