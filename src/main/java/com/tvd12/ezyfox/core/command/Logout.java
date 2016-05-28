/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.model.ApiBaseUser;

/**
 * Execute this command to log a User out of the current Zone
 * 
 * @author tavandung12
 *
 */
public interface Logout extends BaseCommand {

    /**
     * User to logout
     * 
     * @param user user to logout
     * @return this pointer
     */
    public <T extends Logout> T user(ApiBaseUser user);
    
    /**
     * Name of user to logout
     * 
     * @param username name of user to logout
     * @return this pointer
     */
    public <T extends Logout> T user(String username);
    
}
