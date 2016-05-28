package com.tvd12.ezyfox.core.annotation.parser;

import org.springframework.core.annotation.AnnotationUtils;

import com.tvd12.ezyfox.core.annotation.ClientResponseHandler;

/**
 * Support to read information of a class (which class handle response information to client),
 * Read {@code @ClientResponseHandler} annotation to obtain command name and priority
 * 
 * @author tavandung12
 *
 */

public final class ClientResponseParser {
	
    // prevent new instance
	private ClientResponseParser() {}
	
	/**
	 * Check whether class be annotated with {@code @ClientResponseHandler} annotation
	 * 
	 * @param clazz class to check
	 * @return has or not
	 */
	public static boolean hasAnnotation(Class<?> clazz) {
		return AnnotationUtils.findAnnotation(
				clazz, ClientResponseHandler.class) != null;
	}
	
	/**
	 * Get command of response
	 * 
	 * @param clientResponseHandlerClass response handler class
	 * @param defaultCommand default command
	 * @return command name
	 */
	public static String getCommand(Class<?> clientResponseHandlerClass, 
			String defaultCommand) {
		String command = AnnotationUtils.findAnnotation(
				clientResponseHandlerClass, 
				ClientResponseHandler.class).command();
		return command.trim().length() == 0 
				? defaultCommand
				: command;
	}

}
