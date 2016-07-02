/**
 * 
 */
package com.tvd12.ezyfox.core.model;

import com.tvd12.ezyfox.core.transport.Parameters;

/**
 * @author tavandung12
 *
 */
public interface ApiInvitation {

   int id();
    
   <T extends ApiBaseUser> T inviter();
    
   <T extends ApiBaseUser> T invitee();
    
   boolean expired();
    
   int expiryTime();
    
   int secondsForAnswer();
    
   Parameters params();
    
}
