/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.model.ApiBaseUser;

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
     * @return
     */
    <T extends FetchBuddyList> T user(ApiBaseUser user);
    
    /**
     * the user
     * 
     * @param username user name
     * @return
     */
    <T extends FetchBuddyList> T user(String username);
    
}
