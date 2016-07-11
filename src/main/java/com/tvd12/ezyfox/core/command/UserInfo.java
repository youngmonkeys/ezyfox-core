/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.entities.ApiBaseUser;

/**
 * Use this command to update or fetch information of an user 
 * 
 * @author tavandung12
 *
 */
public interface UserInfo extends UpdateUserInfo, FetchUserInfo {

    /**
     * Set user
     * 
     * @param user user
     * @return this pointer
     */
    UserInfo user(ApiBaseUser user);
    
    /**
     * Set user name to get user
     * 
     * @param username user name
     * @return this pointer
     */
    UserInfo user(String username);
    
    /**
     * Set user id to get user
     * 
     * @param userId user id
     * @return this pointer
     */
    UserInfo user(int userId);
    
}
