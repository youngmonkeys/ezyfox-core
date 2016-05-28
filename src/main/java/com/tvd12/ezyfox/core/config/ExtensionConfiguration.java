package com.tvd12.ezyfox.core.config;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tvd12.ezyfox.core.annotation.ClientRequestListener;
import com.tvd12.ezyfox.core.annotation.GameUser;
import com.tvd12.ezyfox.core.annotation.ResponseParams;
import com.tvd12.ezyfox.core.annotation.RoomAgent;
import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.annotation.UserAgent;
import com.tvd12.ezyfox.core.model.ApiGameUser;
import com.tvd12.ezyfox.core.model.ApiRoom;
import com.tvd12.ezyfox.core.model.ApiUser;
import com.tvd12.ezyfox.core.reflect.ReflectPackageUtil;
import com.tvd12.ezyfox.core.structure.AgentClass;
import com.tvd12.ezyfox.core.structure.RequestResponseClass;
import com.tvd12.ezyfox.core.structure.ResponseParamsClass;
import com.tvd12.ezyfox.core.structure.UserAgentClass;

import lombok.Getter;

/**
 * 
 * Support to load and hold entire application's configuration
 * 
 * @author tavandung12
 *
 */
public class ExtensionConfiguration extends ConfigurationLoading {
	
    // user agent's class
    @Getter
	private Class<?> userClass;

    @Getter
    // list of room agent's classes
	private List<Class<?>> roomClasses;
    
    // list of game user agent's classes
    @Getter
    private List<Class<?>> gameUserClasses;
	
    // structure of user agent's class
	@Getter
	private UserAgentClass userAgentClass;
	
	// map of room agent's classes and their structure
	@Getter
    private Map<Class<?>, AgentClass> roomAgentClasses;
	
	// map of room agent's classes and their structure
	@Getter
    private Map<Class<?>, UserAgentClass> gameUserAgentClasses;
	
	// list of server event handler classes
	@Getter
	private List<Class<?>> serverEventHandlerClasses;
	
	// list of client request listener class's structure
	@Getter
	private List<RequestResponseClass> requestResponseClientClasses;
	
	// map of client request listener classes and their structure
	@Getter
	private Map<Class<?>, ResponseParamsClass> responseParamsClasses;
	
	/**
	 * Load all configuration from entry point class
	 * 
	 * @param entryPoint application's entry point class
	 */
	public void load(Class<?> entryPoint) {
		super.load(entryPoint);
		initUserAgentClass();
		initRoomAgentClasses();
		initGameUserAgentClasses();
		findServerEventHandlers();
		findRequestResponseHandlers();
		findResponseParamsClasses();
	}
	
	/**
	 * find all server event handler classes in packages to scan
	 */
	private void findServerEventHandlers() {
		serverEventHandlerClasses = ReflectPackageUtil
				.findClasses(packagesScan, ServerEventHandler.class);
	}
	
	/**
	 * find all client request listener classes in packages to scan
	 */
	private void findRequestResponseHandlers() {
		List<Class<?>> classes = ReflectPackageUtil
				.findClasses(packagesScan, ClientRequestListener.class);
		requestResponseClientClasses = new ArrayList<>();
		for(Class<?> clazz : classes)
		    requestResponseClientClasses.add(
		            new RequestResponseClass(clazz, userClass, gameUserClasses));
	}
	
	/**
	 * find all classes hold data to response to client
	 */
	private void findResponseParamsClasses() {
	    List<Class<?>> classes = ReflectPackageUtil
                .findClasses(packagesScan, ResponseParams.class);
	    responseParamsClasses = new HashMap<>();
	    for(Class<?> clazz : classes) {
            responseParamsClasses.put(clazz, new ResponseParamsClass(clazz));
        }
	}
	
	/**
	 * find user agent's class in packages to scan and read it's structure
	 */
	private void initUserAgentClass() {
        userClass = findUserAgentClass();
        checkUserAgentClass();
        userAgentClass = new UserAgentClass(userClass);
    }
	
	/**
	 * find all room agent's classes and read their struct and put all to map
	 */
	private void initRoomAgentClasses() {
		roomClasses = findAgentClasses(RoomAgent.class);
		checkRoomClasses();
		roomAgentClasses = new HashMap<>();
		for(Class<?> clazz : roomClasses) 
		    roomAgentClasses.put(clazz, new AgentClass(clazz));
	}
	
	/**
	 * find all game user agent's classes and read their struct and put all to map
	 */
	private void initGameUserAgentClasses() {
	    gameUserClasses = findAgentClasses(GameUser.class);
	    checkGameUserClasses();
	    gameUserAgentClasses = new HashMap<>();
	    for(Class<?> clazz : gameUserClasses) 
            gameUserAgentClasses.put(clazz, new UserAgentClass(clazz));
	}
	
	/**
	 * find agent's class (room or user)
	 * 
	 * @param annotation annotation related to agent's class
	 * @return list of agent's classes
	 */
	private List<Class<?>> findAgentClasses(Class<? extends Annotation> annotation) {
		return ReflectPackageUtil
				.findClasses(packagesScan, annotation);
	}
	
	/**
	 * find user agent's class in packages to scan
	 * 
	 * @return user agent's class
	 */
	private Class<?> findUserAgentClass() {
	    List<Class<?>> agentClasses = findAgentClasses(UserAgent.class);
	    if(agentClasses.size() != 1) 
            throw new RuntimeException(exceptionMsgWhenFindingAgent(UserAgent.class, 
                    agentClasses.size()));
        return agentClasses.get(0);
	}
	
	/**
	 * validate user agent's class, class must extends {@code ApiUser}
	 * 
	 * @throws IllegalStateException when user agent's class not extends {@code ApiUser}
	 */
	private void checkUserAgentClass() {
	    if(!ApiUser.class.isAssignableFrom(getUserClass()))
	        throw new IllegalStateException("User agent class " 
	                + userClass + " must extend "
	                + ApiUser.class + " class");
	}
	
	/**
	 * validate all room agent's classes
	 * 
	 * @throws IllegalStateException when any room agent's class not extends {@code ApiRoom}
	 */
	private void checkRoomClasses() {
	    for(Class<?> clazz : roomClasses)
	        checkRoomClass(clazz);
	}
	
	/**
	 * validate room agent's class
	 *  
	 * @param clazz room agent's class
	 * @throws IllegalStateException when room agent's class not extends {@code ApiRoom}
	 */
	private void checkRoomClass(Class<?> clazz) {
        if(!ApiRoom.class.isAssignableFrom(clazz))
            throw new IllegalStateException("Room agent class " 
                    + clazz + " must extend "
                    + ApiRoom.class + " class");
    }
	
	/**
	 * validate all game user agent's classes
	 * 
	 * @throws IllegalStateException when has any agent's classes not extends {@code ApiGameUser}
	 */
	private void checkGameUserClasses() {
	    for(Class<?> clazz : gameUserClasses)
	        checkGameUserClass(clazz);
	}
	
	/**
	 * validate game user agent's class
	 * 
	 * @param clazz game user agent's class
	 * @throws IllegalStateException when agent's class not extends {@code ApiGameUser}
	 */
	private void checkGameUserClass(Class<?> clazz) {
        if(!ApiGameUser.class.isAssignableFrom(clazz))
            throw new IllegalStateException("Game user class " 
                    + clazz + " must extend "
                    + ApiGameUser.class + " class");
    }
	
	/**
	 * Create exception when finding agent classes get any errors
	 * 
	 * @param annotation annotation related to agent's class
	 * @param size invalid size of agent's classes
	 * @return message
	 */
	private String exceptionMsgWhenFindingAgent(Class<? extends Annotation> annotation, int size) {
		return new StringBuilder()
				.append("Number of ") 
				.append(annotation.getSimpleName()) 
				.append(" class = ") 
				.append(size)
				.append(" is not allowed. ")
				.append("You must specific an and only user class ")
				.append("by appending ") 
				.append(annotation) 
				.append(" annotation in a model class that you want to it represents to ")
				.append(annotation.getSimpleName())
				.toString();
	}
}
