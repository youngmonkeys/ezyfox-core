package com.tvd12.ezyfox.core.structure;

import java.util.List;

import com.tvd12.ezyfox.core.annotation.RoomName;
import com.tvd12.ezyfox.core.annotation.parser.HandleMethodParser;

import lombok.Getter;

/**
 * Support to hold structure of room event handler class
 * 
 * @see ServerUserHandlerClass
 * 
 * @author tavandung12
 *
 */

public class RoomHandlerClass extends ServerUserHandlerClass {

    // room name
    @Getter
    private String roomName;
    
    // room agent's class
    @Getter
    private Class<?> roomClass;
    
    /**
     * Construct with handler class, room agent's, user agent's class, game user agent's classes
     * 
     * @param clazz handler class
     * @param roomClasses list of room agent's classes
     * @param userClass user agent's class
     * @param gameUserClasses list of game user agent's classes
     */
    public RoomHandlerClass(Class<?> clazz, 
            List<Class<?>> roomClasses, 
            Class<?> userClass, List<Class<?>> gameUserClasses) {
        super(clazz);
        checkRoomName(clazz);
        checkHandleMethod(clazz, roomClasses, userClass, gameUserClasses);
        checkUserClass();
        checkRoomClass();
    }
    
    /**
     * Get handle method and check its, handle method of room handler class must has first parameter
     * is {@code AppContext}, second parameter is a room agent, and third parameter is user agent or
     * game user agent.
     * 
     * @param clazz handler class
     * @param roomClasses room agent's class
     * @param userClass user agent's class 
     * @param gameUserClasses game user agent's classes
     */
    private void checkHandleMethod(Class<?> clazz, 
            List<Class<?>> roomClasses, 
            Class<?> userClass, List<Class<?>> gameUserClasses) {
        handleMethod = HandleMethodParser.getServerHandleMethod(
                clazz, roomClasses, userClass, gameUserClasses);
    }

    /**
     * disable initializing of parent class
     */
    @Override
    protected void init(Class<?> clazz) {}
    
    /**
     * Read {@code RoomName} annotation to get room name and check its
     * 
     * @param clazz handler class
     */
    private void checkRoomName(Class<?> clazz) {
        RoomName annotation = clazz.getAnnotation(RoomName.class);
        roomName = (annotation != null) ? annotation.value().trim() : "";
    }
    
    /**
     * Get room agent's class and check it
     */
    private void checkRoomClass() {
        roomClass = handleMethod.getParameterTypes()[1];
    }
    
    /**
     * Get user agent's class or game user agent's class and check its
     */
    @Override
    protected void checkUserClass() {
        userClass = handleMethod.getParameterTypes()[2];
    }
    
}
