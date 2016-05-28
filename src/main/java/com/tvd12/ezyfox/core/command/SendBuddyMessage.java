/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.model.ApiBaseUser;

/**
 * Execute this command to send a Buddy Message to any Buddy in your list.
 * 
 * @author tavandung12
 *
 */
public interface SendBuddyMessage extends BaseCommand {

    /**
     * the sender of the message
     * 
     * @param sender the sender of the message
     * @return this pointer
     */
    public <T extends SendBuddyMessage> T sender(ApiBaseUser sender);
    
    /**
     * the sender of the message
     * 
     * @param senderName the sender's name of the message
     * @return this pointer
     */
    public <T extends SendBuddyMessage> T sender(String senderName);
    
    /**
     * the recipient of the message (must be a Buddy in the sender's BuddyList)
     * 
     * @param recipient recipient
     * @return this pointer
     */
    public <T extends SendBuddyMessage> T recipient(ApiBaseUser recipient);
    
    /**
     * the recipient's name of the message (must be a Buddy in the sender's BuddyList)
     * 
     * @param recipientName recipient's name
     * @return this pointer
     */
    public <T extends SendBuddyMessage> T recipient(String recipientName);
    
    /**
     * the message to send
     * 
     * @param message message to send
     * @return this pointer
     */
    public <T extends SendBuddyMessage> T message(String message);
    
    /**
     * custom parameters (an object of class be annotated with {@code MessageParams} annotation)
     * 
     * @param params custom parameters
     * @return this pointer
     */
    public <T extends SendBuddyMessage> T params(Object params);
    
}
