/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.model.ApiZone;

/**
 * Execute this command to create connection-less NPC (Non-Player Character). 
 * The NPC will be seen in the system as any other regular User although 
 * he can be recognized like ApiNPC. There is no real, 
 * physical connection to the Server (no TCP connection is used)
 * NOTE: NPCs must be created once the server engine is up and running. 
 * So you will need to do is adding a ServerReadyEventHandler class 
 * (annotate class with {@code @ServerEventHandler(event = ServerEvent.SERVER_READY)}) 
 * and wait for that event before instantiating any NPC. 
 * 
 * @author tavandung12
 *
 */
public interface CreateNPC extends BaseCommand {

    /**
     * Set name to NPC
     * 
     * @param username the NPC name
     * @return this pointer
     */
    public <T extends CreateNPC> T username(String username);
    
    /**
     * Set NPC's zone
     * 
     * @param zone zone
     * @return this pointer
     */
    public <T extends CreateNPC> T zone(ApiZone zone);
    
    /**
     * Name of NPC's zone
     * 
     * @param zoneName zone name
     * @return this pointer
     */
    public <T extends CreateNPC> T zone(String zoneName);
    
    /**
     * if a User already exists with that name, it will disconnect it first

     * @param value force login or not
     * @return this pointer
     */
    public <T extends CreateNPC> T forceLogin(boolean value);
    
}
