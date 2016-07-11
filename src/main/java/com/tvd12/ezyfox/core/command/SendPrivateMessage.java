/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.entities.ApiBaseUser;

/**
 * Sends a private chat message. The message is sent to both the sender and receiver. 
 * The sender and receiver can be in any Rooms or even not joined in any rooms at all.
 *
 * 
 * @author tavandung12
 *
 */
public interface SendPrivateMessage extends BaseCommand {

    /**
     * the sender of the message
     * 
     * @param sender the sender of the message
     * @return this pointer
     */
    SendPrivateMessage sender(ApiBaseUser sender);
    
    /**
     * the sender of the message
     * 
     * @param senderName the sender of the message
     * @return this pointer
     */
    SendPrivateMessage sender(String senderName);
    
    /**
     * the recipient of the message
     * 
     * @param recipient the recipient of the message
     * @return this pointer
     */
    SendPrivateMessage recipient(ApiBaseUser recipient);
    
    /**
     * the recipient of the message
     * 
     * @param recipientName the recipient of the message
     * @return this pointer
     */
    SendPrivateMessage recipient(String recipientName);
    
    /**
     * the chat message
     * 
     * @param message message as string
     * @return this pointer
     */
    SendPrivateMessage message(String message);
    
    /**
     * extra parameter of message as object (that object be annotated with {@code MessageParams} annotation)
     * 
     * @param params an object
     * @return this pointer
     */
    SendPrivateMessage params(Object params);
    
}
