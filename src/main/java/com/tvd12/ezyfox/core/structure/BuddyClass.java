package com.tvd12.ezyfox.core.structure;

import lombok.Getter;

/**
 * Because sometimes we need serialize or deserialize buddy agent's classes,
 * but using java reflection is too slow, so we need use reflection in first time to load 
 * structure of agent's classes. And this class support to hold structure of a buddy agent's class
 * 
 * @author tavandung12
 *
 */

public class BuddyClass {

    // object holds all structure of setter method for deserialize
    @Getter
    private BuddyClassWrapper wrapper;
   
    @Getter
    // object holds all structure of getter method for serialize
    private BuddyClassUnwrapper unwrapper;
    
    /**
     * Construct with agent's class
     * 
     * @param clazz agent's class
     */
    public BuddyClass(Class<?> clazz) {
        wrapper = new BuddyClassWrapper(clazz);
        unwrapper = new BuddyClassUnwrapper(clazz);
    }
    
}
