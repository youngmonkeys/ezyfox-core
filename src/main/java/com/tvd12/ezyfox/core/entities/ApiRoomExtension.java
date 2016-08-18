/**
 * 
 */
package com.tvd12.ezyfox.core.entities;

import lombok.Setter;

import lombok.Getter;

import lombok.AllArgsConstructor;

/**
 * @author tavandung12
 *
 */
@AllArgsConstructor
@Getter @Setter
public class ApiRoomExtension {
    
    private String name;
    private String clazz;
    
    public ApiRoomExtension(String name, Class<?> clazz) {
        this(name, clazz.getName());
    }
    
    public void setClazz(Class<?> clazz) {
        this.setClazz(clazz.getName());
    }
    
    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
    
}
