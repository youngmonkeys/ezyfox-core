/**
 * 
 */
package com.tvd12.ezyfox.core.command;

/**
 * Return the relative path to the current extension folder 
 * Typically this will return: extensions/{name-of-extension}/ 
 * The path is relative to the Server root folder and it can be used to load external data files that are stored together with the extension jar file(s)
 * 
 * @author tavandung12
 *
 */
public interface GetCurrentFolder extends BaseCommand {

}
