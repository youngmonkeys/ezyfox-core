/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.entities.ApiBaseUser;
import com.tvd12.ezyfox.core.entities.ApiRoom;

/**
 * Execute this command to send a public chat message. 
 * The message is broadcast to all Users in the same Room including the sender.
 * 
 * @author tavandung12
 *
 */
public interface SendPublicMessage extends BaseCommand {

    /**
     * the sender of the message
     * 
     * @param sender the sender of the message
     * @return this pointer
     */
    SendPublicMessage sender(ApiBaseUser sender);
    
    /**
     * the sender of the message
     * 
     * @param senderName the sender of the message
     * @return this pointer
     */
    SendPublicMessage sender(String senderName);
    
    /**
     * the Room where the message is sent
     * 
     * @param room target room
     * @return this pointer
     */
    SendPublicMessage room(ApiRoom room);
    
    /**
     * the Room where the message is sent
     * 
     * @param roomName target room
     * @return this pointer
     */
    SendPublicMessage room(String roomName);
    
    /**
     * the chat message
     * 
     * @param message message as string
     * @return this pointer
     */
    SendPublicMessage message(String message);
    
    /**
     * extra parameter of message as object (that object be annotated with {@code MessageParams} annotation)
     * 
     * @param params an object
     * @return this pointer
     */
    SendPublicMessage params(Object params);
    
}
