/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.model.ApiBaseUser;
import com.tvd12.ezyfox.core.model.ApiRoom;

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
    public <T extends SendPublicMessage> T sender(ApiBaseUser sender);
    
    /**
     * the sender of the message
     * 
     * @param senderName the sender of the message
     * @return this pointer
     */
    public <T extends SendPublicMessage> T sender(String senderName);
    
    /**
     * the Room where the message is sent
     * 
     * @param room target room
     * @return this pointer
     */
    public <T extends SendPublicMessage> T room(ApiRoom room);
    
    /**
     * the Room where the message is sent
     * 
     * @param roomName target room
     * @return this pointer
     */
    public <T extends SendPublicMessage> T room(String roomName);
    
    /**
     * the chat message
     * 
     * @param message message as string
     * @return this pointer
     */
    public <T extends SendPublicMessage> T message(String message);
    
    /**
     * extra parameter of message as object (that object be annotated with {@code MessageParams} annotation)
     * 
     * @param params an object
     * @return this pointer
     */
    public <T extends SendPublicMessage> T params(Object params);
    
}
