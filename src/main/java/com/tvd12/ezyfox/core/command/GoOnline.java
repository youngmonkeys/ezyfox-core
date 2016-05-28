/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.model.ApiBaseUser;

/**
 * Execute this command to activates/De-activates the Buddy ONLINE status of the User. 
 * All clients who have the User as their Buddy will see him as online.
 * 
 * @author tavandung12
 *
 */
public interface GoOnline extends BaseCommand {

    /**
     * the user 
     * 
     * @param user the user
     * @return this pointer
     */
    <T extends GoOnline> T user(ApiBaseUser user);
    
    /**
     * the user
     * 
     * @param username user name
     * @return this pointer
     */
    <T extends GoOnline> T user(String username);
    
    /**
     * the online status
     * 
     * @param online the online status
     * @return this pointer
     */
    <T extends GoOnline> T online(boolean online);
    
    /**
     * if true fires a server side event (BUDDY_ONLINE)
     * 
     * @param fireServerEvent fire server event
     * @return this pointer
     */
    <T extends GoOnline> T fireServerEvent(boolean fireServerEvent);
    
    
    
}
