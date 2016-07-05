/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import java.util.List;

import com.tvd12.ezyfox.core.entities.ApiBaseUser;
import com.tvd12.ezyfox.core.entities.ApiInvitation;
import com.tvd12.ezyfox.core.transport.Parameters;

/**
 * @author tavandung12
 *
 */
public interface SendInvitation extends BaseCommand {

    SendInvitation inviter(ApiBaseUser user);
    
    SendInvitation invitees(List<ApiBaseUser> users);
    
    SendInvitation invitees(ApiBaseUser... users);
    
    SendInvitation expirySeconds(int seconds);
    
    SendInvitation callback(Callback callback);
    
    SendInvitation param(String name, Object value);
    
    public static interface Callback {
        
        void onAccepted(ApiInvitation invitation, Parameters params);
        
        void onRefused(ApiInvitation invitation, Parameters params);
        
        void onExpired(ApiInvitation invitation);
        
    }
    
}
