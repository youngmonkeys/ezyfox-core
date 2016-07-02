/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import java.util.List;

import com.tvd12.ezyfox.core.model.ApiBaseUser;
import com.tvd12.ezyfox.core.model.ApiInvitation;
import com.tvd12.ezyfox.core.transport.Parameters;

/**
 * @author tavandung12
 *
 */
public interface SendInvitation extends BaseCommand {

    <T extends SendInvitation> T inviter(ApiBaseUser user);
    
    <T extends SendInvitation> T invitees(List<ApiBaseUser> users);
    
    <T extends SendInvitation> T invitees(ApiBaseUser... users);
    
    <T extends SendInvitation> T expirySeconds(int seconds);
    
    <T extends SendInvitation> T callback(Callback callback);
    
    <T extends SendInvitation> T param(String name, Object value);
    
    public static interface Callback {
        
        void onAccepted(ApiInvitation invitation, Parameters params);
        
        void onRefused(ApiInvitation invitation, Parameters params);
        
        void onExpired(ApiInvitation invitation);
        
    }
    
}
