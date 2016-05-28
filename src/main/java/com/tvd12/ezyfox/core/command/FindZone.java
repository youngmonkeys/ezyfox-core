/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import java.util.List;

import com.tvd12.ezyfox.core.model.ApiZone;

/**
 * Execute this command to find a zone
 * 
 * @author tavandung12
 *
 */
public interface FindZone {

    /**
     * Get current zone
     * 
     * @return current zone
     */
    ApiZone current();
    
    /**
     * Get all zones
     */
    List<ApiZone> all();
    
    /**
     * Find zone by id
     * 
     * @param id zone id
     * @return a zone
     */
    ApiZone find(int id);
    
    /**
     * Find zone by name
     * 
     * @param name zone name
     * @return a zone
     */
    ApiZone find(String name);
    
}
