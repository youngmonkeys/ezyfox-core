/**
 * 
 */
package com.tvd12.ezyfox.core.model;

import com.tvd12.ezyfox.core.transport.Parameters;

import lombok.Setter;

/**
 * @author tavandung12
 *
 */
@Setter
public class ApiInvitationImpl implements ApiInvitation {

    private int id;
    private ApiBaseUser inviter;
    private ApiBaseUser invitee;
    private boolean expired;
    private int expiryTime;
    private int secondsForAnswer;
    private Parameters params;
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.model.ApiInvitation#getId()
     */
    @Override
    public int id() {
        return this.id;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.model.ApiInvitation#inviter()
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends ApiBaseUser> T inviter() {
        return (T)this.inviter;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.model.ApiInvitation#invitee()
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends ApiBaseUser> T invitee() {
        return (T)this.invitee;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.model.ApiInvitation#exxpired()
     */
    @Override
    public boolean expired() {
        return this.expired;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.model.ApiInvitation#expiryTime()
     */
    @Override
    public int expiryTime() {
        return this.expiryTime;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.model.ApiInvitation#secondsForAnswer()
     */
    @Override
    public int secondsForAnswer() {
        return this.secondsForAnswer;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.model.ApiInvitation#params()
     */
    @Override
    public Parameters params() {
        return this.params;
    }

    
    
}
