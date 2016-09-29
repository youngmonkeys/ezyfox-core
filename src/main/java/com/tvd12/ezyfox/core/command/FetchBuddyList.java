/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.entities.ApiBaseUser;

/**
 * Execute this command to get all buddies of an user
 * 
 * @author tavandung12
 *
 */
public interface FetchBuddyList extends BaseCommand {

    /**
     * the user
     * 
     * @param user the user
     * @return this pointer
     */
    FetchBuddyList user(ApiBaseUser user);
    
    /**
     * the user
     * 
     * @param username user name
     * @return this pointer
     */
    FetchBuddyList user(String username);
    
}
