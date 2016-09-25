package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.entities.ApiBaseUser;

/**
 * Run this command to kick user from server
 * 
 * @author tavandung12
 *
 */

public interface KickUser extends BaseCommand {
    
    /**
     * Set user to kick
     * 
     * @param user user to kick
     * @return this pointer
     */
    KickUser user(ApiBaseUser user);
    
    /**
     * Set user's name to kick
     * 
     * @param username user's name
     * @return this pointer
     */
    KickUser user(String username);
    
    /**
     * Set the mod/admin user, can be null to indicate generically the "Server"
     * 
     * @param user the mod/admin user, can be null to indicate generically the "Server" 
     * @return this pointer
     */
    KickUser modUser(ApiBaseUser user);
    
    /**
     * Set the mod/admin user's name, can be null to indicate generically the "Server"
     * 
     * @param username mod/admin user's name
     * @return this pointer
     */
    KickUser modUser(String username);
    
    /**
     * set moderator message
     * 
     * @param message a moderator message
     * @return this pointer 
     */
    KickUser message(String message);
    
    /**
     * set delay before the disconnection is performed
     * 
     * @param delaySeconds delay time in seconds
     * @return this pointer
     */
    KickUser delay(int delaySeconds);
    
}
