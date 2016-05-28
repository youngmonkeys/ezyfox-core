/**
 * 
 */
package com.tvd12.ezyfox.core.model;

/**
 * Represent to buddy's agent
 * 
 * @author tavandung12
 *
 */
public interface ApiBuddy extends ApiProperties {
    
    /**
     * Block the Buddy
     * 
     * @param blocked blocked
     */
    public void setBlocked(boolean blocked);
    
    /**
     * Set temporary
     * 
     * @param temp temporary
     */
    public void setTemp(boolean temp);

    /**
     * Get The Buddy name, which corresponds to the User name.
     * 
     * @return buddy's name
     */
    public String getName();
    
    /**
     * Get the nickname of the Buddy.
     * 
     * @return buddy's nick name
     */
    public String getNickName();
    
    /**
     * Get the current Buddy State
     * 
     * @return current buddy state
     */
    public String getState();
    
    /**
     * When a Buddy is blocked he won't be able to see the User status and send him messages
     * 
     * @return blocked or not
     */
    public boolean isBlocked(); 
    
    /**
     * Checks if the Buddy is active in the system
     * 
     * @return online or not
     */
    public boolean isOnline();
    
    /**
     * A temporary Buddy will only exist at runtime but will be lost as soon as he goes away or the user is disconnected.
     * 
     * @return temporary or not
     */
    public boolean isTemp();
    
    /**
     * Get buddy's owner
     * 
     * @return buddy's owner
     */
    public <T extends ApiUser> T getOwner();
    
}
