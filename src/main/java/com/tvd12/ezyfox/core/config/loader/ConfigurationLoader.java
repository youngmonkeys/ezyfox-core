/**
 * 
 */
package com.tvd12.ezyfox.core.config.loader;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tvd12.ezyfox.core.annotation.ClientRequestListener;
import com.tvd12.ezyfox.core.annotation.GameUser;
import com.tvd12.ezyfox.core.annotation.MessageParams;
import com.tvd12.ezyfox.core.annotation.ParamsMapper;
import com.tvd12.ezyfox.core.annotation.ResponseParams;
import com.tvd12.ezyfox.core.annotation.RoomAgent;
import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.config.BaseExtensionConfiguration;
import com.tvd12.ezyfox.core.config.ExtensionConfiguration;
import com.tvd12.ezyfox.core.entities.ApiGameUser;
import com.tvd12.ezyfox.core.entities.ApiRoom;
import com.tvd12.ezyfox.core.reflect.ReflectPackageUtil;

/**
 * @author tavandung12
 *
 */
public abstract class ConfigurationLoader extends BaseConfigurationLoader {

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.config.loader.BaseConfigurationLoader#load(java.lang.Class, java.lang.String[])
     */
    @SuppressWarnings("unchecked")
    @Override
    protected <T extends ExtensionConfiguration> T load(Class<?> configClass, String[] packages) {
        BaseExtensionConfiguration answer = 
                newExtensionConfiguration();
        answer.setMessageParamsClasses(findMessageParamsClasses(packages));
        Set<Class<?>> additionalRequestListenerClasses =
                fetchAdditionalClientRequestListeners(configClass);
        Set<Class<?>> hasMapperClasses = 
                findHasMapperClasses(packages, additionalRequestListenerClasses);
        answer.setObjectDeserializerClasses(findObjectDeserialierClasses(hasMapperClasses));
        answer.setObjectSerializerClasses(findObjectSerializerClasses(hasMapperClasses));
        Set<Class<?>> requestResponseClasses = 
                findRequestResponseHandlers(additionalRequestListenerClasses, packages);
        answer.setRequestResponseClientClasses(requestResponseClasses);
        answer.setResponseParamsClasses(findResponseParamsClasses(packages));
        answer.setServerEventHandlerClasses(
                findServerEventHandlersFromScannedPackages(configClass, packages));
        return (T)answer;
    }
    
    /**
     * Scan the packages and find all response parameter classes
     * 
     * @param packages the packages to scan
     * @return the set of response parameter classes
     */
    protected Set<Class<?>> findResponseParamsClasses(String[] packages) {
        return findAnnotatedClasses(packages, ResponseParams.class);
    }
    
    /**
     * Scan the packages and find all annotated classes
     * 
     * @param packages the packages to scan
     * @param annotation the annotation type
     * @return the set of annotated classes
     */
    protected Set<Class<?>> findAnnotatedClasses(
            String[] packages, Class<? extends Annotation> annotation) {
        return new HashSet<>(ReflectPackageUtil.findClasses(packages, annotation));
    }
    
    /**
     * Scan the packages and find all request listener classes
     * 
     * @param additionalRequestListenerClasses the additional request listener classes
     * @param packages the package to scan
     * @return the set of request listener classes
     */
    protected Set<Class<?>> findRequestResponseHandlers(
            Set<Class<?>> additionalRequestListenerClasses, String[] packages) {
        Set<Class<?>> answer = new HashSet<>();
        answer.addAll(additionalRequestListenerClasses);
        answer.addAll(findAnnotatedClasses(packages, ClientRequestListener.class));
        return answer;
    }
    
    /**
     * Scan the packages and find all server event handler classes
     * 
     * @param configClass the configuration class
     * @param packages the package to scan
     * @return the set of server event handler classes
     */
    protected Set<Class<?>> findServerEventHandlersFromScannedPackages(
            Class<?> configClass, String[] packages) {
        Set<Class<?>> answer = new HashSet<>();
        answer.addAll(findAnnotatedClasses(packages, ServerEventHandler.class));
        answer.addAll(fetchAdditionalServerEventHandlers(configClass));
        return answer;
    }
    
    /**
     * find all classes hold data of message to response to client
     * 
     * @param packages packages to scan
     * @return message parameter classes and their structure
     */
    protected Set<Class<?>> findMessageParamsClasses(String[] packages) {
        return findAnnotatedClasses(packages, MessageParams.class);
    }
    
    /**
     * Scan the packages and find all classes be annotated with
     * {@code ParamsMapper} annotation
     * 
     * @param packages the package to scan
     * @param additionalRequestListenerClasses the additional request listener classes
     * @return the set of classes
     */
    protected Set<Class<?>> findHasMapperClasses(String[] packages, 
            Set<Class<?>> additionalRequestListenerClasses) {
        Set<Class<?>> answer = new HashSet<>();
        answer.addAll(findHasMapperClassesInScannedPackages(packages));
        answer.addAll(findHasMapperClassesInAdditionalRequestHandlerClasses(
                additionalRequestListenerClasses));
        return answer;
    }
    
    /**
     * Scan the packages and find all classes be annotated with
     * {@code ParamsMapper} annotation
     * 
     * @param packages the package to scan
     * @return the set of classes
     */
    protected Set<Class<?>> findHasMapperClassesInScannedPackages(String[] packages) {
        return findAnnotatedClasses(packages, ParamsMapper.class);
    }
    
    /**
     * Find all classes be annotated with
     * {@code ParamsMapper} annotation
     * 
     * @param additionalRequestListenerClasses the additional request listener classes
     * @return the set of classes
     */
    protected List<Class<?>> findHasMapperClassesInAdditionalRequestHandlerClasses(
            Set<Class<?>> additionalRequestListenerClasses) {
        return ReflectPackageUtil.findClasses(additionalRequestListenerClasses, ParamsMapper.class);
    }
    
    /**
     * Find all serializer classes
     * 
     * @param hasMapperClasses the classes be annotated with {@code ParamsMapper}
     * @return the map of classes and their serializer class
     */
    protected Map<Class<?>, Class<?>> findObjectSerializerClasses(
            Set<Class<?>> hasMapperClasses) {
        Map<Class<?>, Class<?>> answer = new HashMap<>();
        for(Class<?> clazz : hasMapperClasses) {
            ParamsMapper anno = clazz.getAnnotation(ParamsMapper.class);
            if(!anno.serializer().equals(Class.class))
                answer.put(clazz, anno.serializer());
        }
        return answer;
    }
    
    /**
     * Find all deserializer classes
     * 
     * @param hasMapperClasses the classes be annotated with {@code ParamsMapper}
     * @return the map of classes and their deserializer class
     */
    protected Map<Class<?>, Class<?>> findObjectDeserialierClasses(
            Set<Class<?>> hasMapperClasses) {
        Map<Class<?>, Class<?>> answer = new HashMap<>();
        for(Class<?> clazz : hasMapperClasses) {
            ParamsMapper anno = clazz.getAnnotation(ParamsMapper.class);
            if(!anno.deserializer().equals(Class.class))
                answer.put(clazz, anno.deserializer());
        }
        return answer;
    }
    
    /**
     * Scan the packages and find all classes be annotated with 
     * {@code RoomAgent} annotation
     * 
     * @param packages the packages to scan
     * @return the set of room classes
     */
    protected Set<Class<?>> findRoomClasses(String[] packages) {
        Set<Class<?>> answer = findAnnotatedClasses(packages, RoomAgent.class);
        checkRoomClasses(answer);
        return answer;
    }
    
    /**
     * Scan the packages and find all classes be annotated with 
     * {@code GameUser} annotation
     * 
     * @param packages the packages to scan
     * @return the set of game user classes
     */
    protected Set<Class<?>> findGameUserClasses(String[] packages) {
        Set<Class<?>> answer = findAnnotatedClasses(packages, GameUser.class);
        checkGameUserClasses(answer);
        return answer;
    }
    
    /**
     * Validate all room classes
     * 
     * @param classes the room classes
     */
    protected void checkRoomClasses(Set<Class<?>> classes) {
        for(Class<?> clazz : classes)
            checkAgentClass(clazz, ApiRoom.class);
    }
    
    /**
     * Validate all game user classes
     * 
     * @param classes the game user classes
     */
    protected void checkGameUserClasses(Set<Class<?>> classes) {
        for(Class<?> clazz : classes)
            checkAgentClass(clazz, ApiGameUser.class);
    }
    
    /**
     * Validate an agent class
     * 
     * @param clazz the class to validate
     * @param parentType the parent type of the class
     */
    protected void checkAgentClass(Class<?> clazz, Class<?> parentType) {
        if(!parentType.isAssignableFrom(clazz))
            throw new IllegalStateException(clazz + " must extend " + parentType + " class");
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
                .append("number of ") 
                .append(annotation.getSimpleName()) 
                .append(" class = ") 
                .append(size)
                .append(" is not allowed. ")
                .append("You must specific an and only agent class ")
                .append("by appending ") 
                .append(annotation) 
                .append(" annotation in a model class that you want to it represents to ")
                .append(annotation.getSimpleName())
                .append(" (error related to ")
                .append(getConfigurationClass())
                .append(")")
                .toString();
    }
    
}
