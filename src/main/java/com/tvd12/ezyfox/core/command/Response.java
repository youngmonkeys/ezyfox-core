/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import java.util.List;

import com.tvd12.ezyfox.core.entities.ApiBaseUser;

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
    Response command(String command);
    
    /**
     * set data object to response to client
     * 
     * @param object data to response
     * @return this pointer
     */
    Response data(Object object);
    
    /**
     * add a key-value data to response to client
     * 
     * @param name name
     * @param value value
     * @return this pointer
     */
    Response param(String name, Object value);
    
    /**
     * add recipients to list
     * 
     * @param users user agent
     * @return this pointer
     */
    Response recipients(ApiBaseUser... users);
    
    /**
     * add recipients to list
     * 
     * @param users user agent
     * @return this pointer
     */
    public <T extends Response, U extends ApiBaseUser> T recipients(List<U> users);
    
    /**
     * add recipients to list
     * 
     * @param usernames recipient's names
     * @return this pointer
     */
    Response recipients(String... usernames);
    
    /**
     * user udp protocol or not
     * 
     * @param value true or false
     * @return this pointer
     */
    Response useUDP(boolean value);
    
    /**
     * Only response array of parameters to client
     * 
     * @param params array of parameters name
     */
    Response only(String... params);
    
    /**
     * Does not send the array of parameters to client
     * 
     * @param params array of parameters name
     */
    Response ignore(String... params);
}
