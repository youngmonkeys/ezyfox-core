package com.tvd12.ezyfox.core.structure;

import java.util.List;

import com.tvd12.ezyfox.core.annotation.ZoneName;
import com.tvd12.ezyfox.core.annotation.parser.HandleMethodParser;
import com.tvd12.ezyfox.core.entities.ApiZone;

import lombok.Getter;

/**
 * Support to hold structure of zone event handler class
 * 
 * @see ServerUserHandlerClass
 * 
 * @author tavandung12
 *
 */
public class ZoneHandlerClass extends ServerUserHandlerClass {

    // zone name
    @Getter
    public String zoneName;
    
    /**
     * @param clazz the class to parse
     * @param userClazz the user class 
     * @param gameUserClasses the list of game user classes
     */
    public ZoneHandlerClass(Class<?> clazz, 
            Class<?> userClazz, List<Class<?>> gameUserClasses) {
        super(clazz);
        checkZoneName(clazz);
        checkHandleMethod(clazz, userClazz, gameUserClasses);
        checkUserClass();
    }
    
    /**
     * Disable initializing from parent class
     * 
     * @param clazz the class
     */
    @Override
    protected void init(Class<?> clazz) {
    }
    
    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfox.core.structure.ServerHandlerClass#newInstance()
     */
    @Override
    public Object newInstance() {
        return propertiesClassWrapper.newInstance();
    }
    
    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfox.core.structure.ServerUserHandlerClass#checkHandleMethod(java.lang.Class, java.lang.Class, java.util.List)
     */
    @Override
    protected void checkHandleMethod(Class<?> clazz, 
            Class<?> userClazz, List<Class<?>> gameUserClasses) {
        handleMethod = HandleMethodParser
                .getServerHandleMethod(clazz, ApiZone.class, userClazz, gameUserClasses);
    }
    
    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfox.core.structure.ServerUserHandlerClass#checkUserClass()
     */
    @Override
    protected void checkUserClass() {
        userClass = handleMethod.getParameterTypes()[2]; 
    }
    
    /**
     * Get zone name and check its
     * 
     * @param clazz handler class
     */
    private void checkZoneName(Class<?> clazz) {
        ZoneName annotation = clazz.getAnnotation(ZoneName.class);
        zoneName  = annotation != null ? annotation.value().trim() : "";
    }
    
}
