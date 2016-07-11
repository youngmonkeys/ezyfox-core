/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.entities.ApiBaseUser;

/**
 * Execute this command when you want to ban an user
 * 
 * @author tavandung12
 *
 */
public interface BanUser extends BaseCommand {

    /**
     * set user to ban
     * 
     * @param userToBan
     * @return this pointer
     */
    BanUser user(ApiBaseUser userToBan);
    
    /**
     * set user name of user to ban
     * 
     * @param userToBan user name of user to ban
     * @return this pointer
     */
    BanUser user(String userToBan);
    
    /**
     * set the mod/admin user name, can be null to indicate generically the "Server"
     * 
     * @param modUser mod/admin user name
     * @return this pointer
     */
    BanUser modUser(String modUser);
    
    /**
     * set ban message
     * 
     * @param banMessage ban message
     * @return this pointer
     */
    BanUser message(String banMessage);
    
    /**
     * choose between banning by Ip Address or by User name
     * 
     * @return this pointer
     */
    BanUser byAddress();
    
    /**
     * choose between banning by ip address or by User name
     * 
     * @return this pointer
     */
    BanUser byName();
    
    /**
     * the duration of the banishment in hours
     * 
     * @param durationMinutes duration in minute
     * @return this pointer
     */
    BanUser duration(int durationMinutes);
 
    /**
     * delay before the disconnection is performed
     * 
     * @param delaySeconds delay time in second
     * @return this pointer
     */
    BanUser delay(int delaySeconds);
}
