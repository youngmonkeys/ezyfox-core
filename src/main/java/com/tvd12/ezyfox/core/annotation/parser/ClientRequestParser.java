package com.tvd12.ezyfox.core.annotation.parser;

import org.springframework.core.annotation.AnnotationUtils;

import com.tvd12.ezyfox.core.annotation.ClientRequestListener;

/**
 * Support to read information of a class, get configuration of {@code @ClientRequestListener} to obtain
 * command name of client's request and priority of the class
 * 
 * @author tavandung12
 *
 */

public final class ClientRequestParser {

    //prevent new an instance
	private ClientRequestParser() {}
	
	/**
	 * Obtain command of client's request
	 * 
	 * @param clientRequestHandlerClass listener class
	 * @return command name
	 */
	public static String getCommand(Class<?> clientRequestHandlerClass) {
		return AnnotationUtils.findAnnotation(
				clientRequestHandlerClass, 
				ClientRequestListener.class).command().trim();
	}
	
	/**
	 * Obtain priority of listener class
	 * 
	 * @param clientRequestHandlerClass listener class
	 * @return priority
	 */
	public static int getPriority(Class<?> clientRequestHandlerClass) {
        return AnnotationUtils.findAnnotation(
                clientRequestHandlerClass, 
                ClientRequestListener.class).priority();
    }
	
}
