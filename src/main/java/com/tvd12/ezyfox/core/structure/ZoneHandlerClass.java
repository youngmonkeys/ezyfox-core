package com.tvd12.ezyfox.core.structure;

import java.util.List;

import com.tvd12.ezyfox.core.annotation.ZoneName;
import com.tvd12.ezyfox.core.annotation.parser.HandleMethodParser;
import com.tvd12.ezyfox.core.model.ApiZone;

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
     * @see ServerUserHandlerClass#ServerUserHandlerClass(Class, Class, List)
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
     */
    @Override
    protected void init(Class<?> clazz) {
    }
    
    /**
     * @see ServerUserHandlerClass#newInstance()
     */
    @Override
    public Object newInstance() {
        return propertiesClassWrapper.newInstance();
    }
    
    /**
     * @see ServerUserHandlerClass#checkHandleMethod(Class, Class, List)
     */
    @Override
    protected void checkHandleMethod(Class<?> clazz, 
            Class<?> userClazz, List<Class<?>> gameUserClasses) {
        handleMethod = HandleMethodParser
                .getServerHandleMethod(clazz, ApiZone.class, userClazz, gameUserClasses);
    }
    
    /**
     * @see ServerUserHandlerClass#checkUserClass()
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
