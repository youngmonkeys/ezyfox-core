package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.entities.ApiRoom;

/**
 * Run this command to create a room
 * 
 * @author tavandung12
 *
 */

public interface CreateRoom extends BaseCommand {

    /**
     * set room agents
     * 
     * @param agents room agent objects
     * @return this pointer 
     */
	CreateRoom agents(ApiRoom... agents);
	
}
