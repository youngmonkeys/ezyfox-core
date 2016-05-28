/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.model.ApiBaseUser;

/**
 * Execute this command to response data to client
 * 
 * @author tavandung12
 *
 */
public interface Response extends BaseCommand {

    /**
     * set command to response
     * 
     * @param command command to response 
     * @return this pointer
     */
    public <T extends Response> T command(String command);
    
    /**
     * set data object to response to client
     * 
     * @param object data to response
     * @return this pointer
     */
    public <T extends Response> T data(Object object);
    
    /**
     * add a key-value data to response to client
     * 
     * @param name name
     * @param value value
     * @return this pointer
     */
    public <T extends Response> T param(String name, Object value);
    
    /**
     * add a recipient to list
     * 
     * @param user user agent
     * @return this pointer
     */
    public <T extends Response> T recipient(ApiBaseUser user);
    
    /**
     * add a recipient to list
     * 
     * @param username recipient's name
     * @return this pointer
     */
    public <T extends Response> T recipient(String username);
    
    /**
     * user udp protocol or not
     * 
     * @param value true or false
     * @return this pointer
     */
    public <T extends Response> T useUDP(boolean value);
}
