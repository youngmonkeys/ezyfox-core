/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.entities.ApiBaseUser;

/**
 * Execute this command to propagate a request
 * 
 * @author tavandung12
 *
 */
public interface PropagateRequest extends BaseCommand {

    /**
     * set command to response
     * 
     * @param command command to response 
     * @return this pointer
     */
    PropagateRequest command(String command);
    
    /**
     * set data object to response to client
     * 
     * @param object data to response
     * @return this pointer
     */
    PropagateRequest data(Object object);
    
    /**
     * add a key-value data to response to client
     * 
     * @param name name
     * @param value value
     * @return this pointer
     */
    PropagateRequest param(String name, Object value);
    
    /**
     * the user
     * 
     * @param user the user agent
     * @return this pointer
     */
    PropagateRequest user(ApiBaseUser user);
    
    /**
     * Only response array of parameters to client
     * 
     * @param params array of parameters name
     * @return this pointer
     */
    PropagateRequest only(String... params);
    
    /**
     * Does not send the array of parameters to client
     * 
     * @param params array of parameters name
     * @return this pointer
     */
    PropagateRequest ignore(String... params);
}
