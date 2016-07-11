/**
 * 
 */
package com.tvd12.ezyfox.core.entities;

/**
 * @author tavandung12
 *
 */
public interface ApiLogin {

    ApiZone zone();
    
    ApiSession session();
    
    String username();
    
    String password();
    
}
