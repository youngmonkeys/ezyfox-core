package com.tvd12.ezyfox.core.config;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tvd12.ezyfox.core.annotation.ClientRequestListener;
import com.tvd12.ezyfox.core.annotation.GameUser;
import com.tvd12.ezyfox.core.annotation.ParamsMapper;
import com.tvd12.ezyfox.core.annotation.MessageParams;
import com.tvd12.ezyfox.core.annotation.ResponseParams;
import com.tvd12.ezyfox.core.annotation.RoomAgent;
import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.annotation.UserAgent;
import com.tvd12.ezyfox.core.entities.ApiGameUser;
import com.tvd12.ezyfox.core.entities.ApiRoom;
import com.tvd12.ezyfox.core.entities.ApiUser;
import com.tvd12.ezyfox.core.reflect.ReflectPackageUtil;
import com.tvd12.ezyfox.core.structure.AgentClass;
import com.tvd12.ezyfox.core.structure.MessageParamsClass;
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
	protected Class<?> userClass;

    // list of room agent's classes
    protected Set<Class<?>> roomClasses;
    
    // list of game user agent's classes
    protected Set<Class<?>> gameUserClasses;
	
    // structure of user agent's class
    @Getter
	protected UserAgentClass userAgentClass;
	
	// map of room agent's classes and their structure
	@Getter
	protected Map<Class<?>, AgentClass> roomAgentClasses;
	
	// map of room agent's classes and their structure
	@Getter
	protected Map<Class<?>, UserAgentClass> gameUserAgentClasses;
	
	// list of server event handler classes
	protected Set<Class<?>> serverEventHandlerClasses;
	
	// list of client request listener class's structure
	protected Set<RequestResponseClass> requestResponseClientClasses;
	
	// map of client request listener classes and their structure
	@Getter
	protected Map<Class<?>, ResponseParamsClass> responseParamsClasses;
	
	// map of message parameters classes and their structure 
	@Getter
	protected Map<Class<?>, MessageParamsClass> messageParamsClasses;
	
	protected Set<Class<?>> hasMapperClasses; 
	
	@Getter
	protected Map<Class<?>, Class<?>> objectSerializerClasses;
	
	@Getter
	protected Map<Class<?>, Class<?>> objectDeserializerClasses;
	
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
		initHasMapperClasses();
		initRequestResponseClientClasses();
		loadAndCheckRoomClasses();
        createRoomAgentClasses();
        loadAndCheckGameUserClasses();
        createGameUserAgentClasses();
		findServerEventHandlers();
		findRequestResponseHandlers();
		findResponseParamsClasses();
		findMessageParamsClasses();
		findHasMapperClasses();
		findObjectSerializerClasses();
		findObjectDeserialierClasses();
	}
	
	/**
	 * find all server event handler classes in packages to scan
	 */
	protected void findServerEventHandlers() {
		initServerEventHandlers();
		findServerEventHandlersFromScannedPackages();
		addAdditionalServerEventHandlers();
	}
	
	protected void initServerEventHandlers() {
	    serverEventHandlerClasses = new HashSet<>();
	}
	
	protected void findServerEventHandlersFromScannedPackages() {
	    serverEventHandlerClasses.addAll(ReflectPackageUtil
                .findClasses(packagesScan, ServerEventHandler.class));
	}
	
	protected void addAdditionalServerEventHandlers() {
	    serverEventHandlerClasses.addAll(getAdditionalServerEventHandlers());
	}
	
	/**
	 * find all client request listener classes in packages to scan
	 */
	protected void findRequestResponseHandlers() {
		addRequestResponseClientClasses(findAllRequestResponseHandlers());
	}
	
	protected void addRequestResponseClientClasses(List<Class<?>> classes) {
        for(Class<?> clazz : classes)
            requestResponseClientClasses.add(newRequestResponseClass(clazz));
	}
	
	protected void initRequestResponseClientClasses() {
	    requestResponseClientClasses = new HashSet<>();
	}
	
	protected List<Class<?>> findRequestResponseHandlersFromScannedPackages() {
	    return ReflectPackageUtil.findClasses(packagesScan, ClientRequestListener.class);
	}
	
	protected List<Class<?>> findAllRequestResponseHandlers() {
	    Set<Class<?>> answer = new HashSet<>();
	    answer.addAll(findRequestResponseHandlersFromScannedPackages());
	    answer.addAll(getAdditionalClientRequestHandlers());
	    return new ArrayList<>(answer);
	}
	
	protected RequestResponseClass newRequestResponseClass(Class<?> clazz) {
	    return new RequestResponseClass(clazz, getUserClass(), getGameUserClasses());
	}
	
	/**
	 * find all classes hold data to response to client
	 */
	protected void findResponseParamsClasses() {
	    List<Class<?>> classes = ReflectPackageUtil
                .findClasses(packagesScan, ResponseParams.class);
	    responseParamsClasses = new HashMap<>();
	    for(Class<?> clazz : classes) {
            responseParamsClasses.put(clazz, new ResponseParamsClass(clazz));
        }
	}
	
	/**
     * find all classes hold data of message to response to client
     */
    protected void findMessageParamsClasses() {
        List<Class<?>> classes = ReflectPackageUtil
                .findClasses(packagesScan, MessageParams.class);
        messageParamsClasses = new HashMap<>();
        for(Class<?> clazz : classes) {
            messageParamsClasses.put(clazz, new MessageParamsClass(clazz));
        }
    }
    
    public void initHasMapperClasses() {
        hasMapperClasses = new HashSet<>();
    }
    
    protected void findHasMapperClasses() {
        hasMapperClasses.addAll(findHasMapperClassesInScannedPackages());
        hasMapperClasses.addAll(findHasMapperClassesInAdditionalRequestHandlerClasses());
    }
    
    protected List<Class<?>> findHasMapperClassesInScannedPackages() {
        return ReflectPackageUtil.findClasses(packagesScan, ParamsMapper.class);
    }
    
    protected List<Class<?>> findHasMapperClassesInAdditionalRequestHandlerClasses() {
        return ReflectPackageUtil.findClasses(additionalClientRequestHandlers, ParamsMapper.class);
    }
    
    protected void findObjectSerializerClasses() {
        objectSerializerClasses = new HashMap<>();
        for(Class<?> clazz : hasMapperClasses) {
            ParamsMapper anno = clazz.getAnnotation(ParamsMapper.class);
            if(!anno.serializer().equals(Class.class))
                objectSerializerClasses.put(clazz, anno.serializer());
        }
    }
    
    protected void findObjectDeserialierClasses() {
        objectDeserializerClasses = new HashMap<>();
        for(Class<?> clazz : hasMapperClasses) {
            ParamsMapper anno = clazz.getAnnotation(ParamsMapper.class);
            if(!anno.deserializer().equals(Class.class))
                objectDeserializerClasses.put(clazz, anno.deserializer());
        }
    }
	
	/**
	 * find user agent's class in packages to scan and read it's structure
	 */
	protected void initUserAgentClass() {
        userClass = findUserAgentClass();
        checkUserAgentClass();
        userAgentClass = new UserAgentClass(userClass);
    }
	
	/**
	 * find all room agent's classes and read their struct and put all to map
	 */
	protected void initRoomAgentClasses() {
	    roomClasses = new HashSet<>();
	}
	
	protected void loadAndCheckRoomClasses() {
	    roomClasses.addAll(findAgentClasses(RoomAgent.class));
	    checkRoomClasses();
	}
	
	protected void createRoomAgentClasses() {
	    roomAgentClasses = new HashMap<>();
        for(Class<?> clazz : roomClasses) 
            roomAgentClasses.put(clazz, new AgentClass(clazz));
	}
	
	/**
	 * find all game user agent's classes and read their struct and put all to map
	 */
	protected void initGameUserAgentClasses() {
	    gameUserClasses = new HashSet<>();
	}
	
	protected void loadAndCheckGameUserClasses() {
	    gameUserClasses.addAll(findAgentClasses(GameUser.class));
	    checkGameUserClasses();
	}
	
	protected void createGameUserAgentClasses() {
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
	protected List<Class<?>> findAgentClasses(Class<? extends Annotation> annotation) {
		return ReflectPackageUtil
				.findClasses(packagesScan, annotation);
	}
	
	/**
	 * find user agent's class in packages to scan
	 * 
	 * @return user agent's class
	 */
	protected Class<?> findUserAgentClass() {
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
	protected void checkUserAgentClass() {
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
	protected void checkRoomClasses() {
	    for(Class<?> clazz : roomClasses)
	        checkRoomClass(clazz);
	}
	
	/**
	 * validate room agent's class
	 *  
	 * @param clazz room agent's class
	 * @throws IllegalStateException when room agent's class not extends {@code ApiRoom}
	 */
	protected void checkRoomClass(Class<?> clazz) {
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
	protected void checkGameUserClasses() {
	    for(Class<?> clazz : gameUserClasses)
	        checkGameUserClass(clazz);
	}
	
	/**
	 * validate game user agent's class
	 * 
	 * @param clazz game user agent's class
	 * @throws IllegalStateException when agent's class not extends {@code ApiGameUser}
	 */
	protected void checkGameUserClass(Class<?> clazz) {
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
	protected String exceptionMsgWhenFindingAgent(Class<? extends Annotation> annotation, int size) {
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
	
    public List<Class<?>> getRoomClasses() {
        return new ArrayList<>(roomClasses);
    }
    
    public List<Class<?>> getGameUserClasses() {
        return new ArrayList<>(gameUserClasses);
    }
    
    public List<RequestResponseClass> getRequestResponseClientClasses() {
        return new ArrayList<>(requestResponseClientClasses);
    }
    
    public List<Class<?>> getServerEventHandlerClasses() {
        return new ArrayList<>(serverEventHandlerClasses);
    }
    
}
    
