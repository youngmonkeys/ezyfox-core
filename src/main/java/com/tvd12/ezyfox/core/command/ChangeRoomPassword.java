/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.entities.ApiBaseUser;
import com.tvd12.ezyfox.core.entities.ApiRoom;

/**
 * Execute this command to changes the Room password and the Room password-state. 
 * The password state indicates if a Room is private and requires a password for accessing it or not.
 * Passing a null or empty string as the password will make room public. 
 * Passing a valid string as the password will make the Room private.
 * 
 * @author tavandung12
 *
 */
public interface ChangeRoomPassword extends BaseCommand {

    /**
     * set Room's owner, it can be null if this is called on the server side
     * 
     * @param owner room's owner
     * @return this pointer
     */
    ChangeRoomPassword owner(ApiBaseUser owner);
    
    /**
     * Set room to change password
     * 
     * @param targetRoom room to change password
     * @return this pointer
     */
    ChangeRoomPassword room(ApiRoom targetRoom);
    
    /**
     * Set room's new password
     * 
     * @param newPassword room's new password
     * @return this pointer
     */
    ChangeRoomPassword password(String newPassword);
    
}
