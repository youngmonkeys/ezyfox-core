/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.model.ApiBaseUser;

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
    <T extends UserInfo> T user(ApiBaseUser user);
    
    /**
     * Set user name to get user
     * 
     * @param username user name
     * @return this pointer
     */
    <T extends UserInfo> T user(String username);
    
    /**
     * Set user id to get user
     * 
     * @param userId user id
     * @return this pointer
     */
    <T extends UserInfo> T user(int userId);
    
}
