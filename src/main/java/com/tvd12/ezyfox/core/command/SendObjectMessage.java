/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import java.util.List;

import com.tvd12.ezyfox.core.model.ApiBaseUser;
import com.tvd12.ezyfox.core.model.ApiRoom;

/**
 * Execute this command to Send an Object message. 
 * This message is based on a custom Object that can contain any data. 
 * Typically this is used for sending game moves to players or other game/app related updates. 
 * The message is sent to all users in the specified target room but you can also pass 
 * a collection of Users to specify which clients in that Room should receive the message.
 * 
 * NOTE: The sender must be joined in the target Room.
 * 
 * @author tavandung12
 *
 */
public interface SendObjectMessage extends BaseCommand {

    /**
     * the room where the message will be sent to
     * 
     * @param room target room
     * @return this pointer
     */
    public <T extends SendObjectMessage> T room(ApiRoom room);
    
    /**
     * name of the room where the message will be sent to
     * 
     * @param roomName name of room
     * @return this pointer
     */
    public <T extends SendObjectMessage> T room(String roomName);
    
    /**
     * id of the room where the message will be sent to
     * 
     * @param roomId room's id
     * @return this pointer
     */
    public <T extends SendObjectMessage> T room(int roomId);
    
    /**
     * the sender of the message
     * 
     * @param sender the sender of the message
     * @return this pointer
     */
    public <T extends SendObjectMessage> T sender(ApiBaseUser sender);
    
    /**
     * add a recipient to the list
     * 
     * @param recipient recipient to add
     * @return this pointer
     */
    public <T extends SendObjectMessage> T recipient(ApiBaseUser recipient);
    
    /**
     * add a recipient to the list
     * 
     * @param recipientName recipient to add
     * @return this pointer
     */
    public <T extends SendObjectMessage> T recipient(String recipientName);
    
    /**
     * add multiple recipients to the list
     * 
     * @param recipients list of recipients
     * @return this pointer
     */
    public <T extends SendObjectMessage> T recipients(List<ApiBaseUser> recipients);
    
    /**
     * add multiple recipients to the list
     * 
     * @param recipients list of recipients
     * @return this pointer
     */
    public <T extends SendObjectMessage> T recipients(String... recipients);
    
    /**
     * message as string to send
     * 
     * @param message message as string
     * @return this pointer
     */
    public <T extends SendObjectMessage> T message(String message);
    
    /**
     * message as object (that object be annotated with {@code MessageParams} annotation)
     * 
     * @param data an object
     * @return this pointer
     */
    public <T extends SendObjectMessage> T message(Object data);
    
    /**
     * massage in json format
     * 
     * @param jsonMessage message in json format
     * @return this pointer
     */
    public <T extends SendObjectMessage> T jsonMessage(String jsonMessage);
    
}
