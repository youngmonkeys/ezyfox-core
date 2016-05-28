/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.model.ApiBaseUser;

/**
 * Execute this command to get id address of an user
 * 
 * @author tavandung12
 *
 */
public interface GetIpAddress extends BaseCommand {

    /**
     * user to get ip address
     * 
     * @param user user to get ip address
     * @return this pointer
     */
    <T extends GetIpAddress> T user(ApiBaseUser user);
    
    /**
     * name of user to get ip address
     * 
     * @param username user name
     * @return this pointer
     */
    <T extends GetIpAddress> T user(String username);
    
}
