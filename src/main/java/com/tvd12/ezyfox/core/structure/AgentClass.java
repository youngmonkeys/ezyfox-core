package com.tvd12.ezyfox.core.structure;

import lombok.Getter;

/**
 * Because sometimes we need serialize or deserialize user agent or room agent's classes,
 * but using java reflection is too slow, so we need use reflection in first time to load 
 * struct of agent's classes. And this class support to hold structure of a agent's class
 * 
 * @author tavandung12
 *
 */

public class AgentClass {

    // object holds all structure of setter method for deserialize
    @Getter
    private AgentClassWrapper wrapper;
   
    @Getter
    // object holds all structure of getter method for serialize
    private AgentClassUnwrapper unwrapper;
    
    /**
     * Construct with agent's class
     * 
     * @param clazz agent's class
     */
    public AgentClass(Class<?> clazz) {
        wrapper = new AgentClassWrapper(clazz);
        unwrapper = new AgentClassUnwrapper(clazz);
    }
    
}
