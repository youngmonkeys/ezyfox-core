/**
 * 
 */
package com.tvd12.ezyfox.core.entities;

/**
 * Call this command to get session information
 * 
 * @author tavandung12
 *
 */
public interface ApiSession {
    
    /**
     * Get the unique session Id
     * 
     * @return the session id
     */
    int getId();
    
    /**
     * A unique string token used as an alternative session ID
     * 
     * @return A unique string token used as an alternative session ID
     */
    String getHashId();
    
    /**
     * Set string token used as an alternative session ID
     * 
     * @param hashId the token
     */
    void setHashId(String hashId);
    
    /**
     * See if the ApiSession is local or hosted in another cluster node.
     * 
     * @return is local or hosted in another cluster node or not
     */
    boolean isLocal();
    
    /**
     * Check if the the ApiSession is logged in.
     * 
     * @return is logged in or not
     */
    boolean isLoggedIn();
    
    /**
     * The timestamp at which the connection was done (milliseconds Unix Time)
     * 
     * @return the creation time
     */
    long getCreationTime();
    
    /**
     * Check if the session is connected.
     * 
     * @return true or false
     */
    boolean isConnected();
    
    /**
     * The last time that the session has sent a packet (milliseconds Unix Time)
     * 
     * @return The last time that the session has sent a packet (milliseconds Unix Time)
     */
    long getLastActivityTime();
    
    /**
     * The last time that the session has sent a request (milliseconds Unix Time)
     * 
     * @return The last time that the session has sent a request (milliseconds Unix Time)
     */
    long getLastLoggedInActivityTime();
    
    /**
     * Same as {@link #getLastActivityTime()}
     * 
     * @return The last activity time
     */
    long getLastReadTime();
    
    /**
     * Same as {@link #getLastActivityTime()}
     * 
     * @return The last activity time
     */
    long getLastWriteTime();
    
    /**
     * The amount of data in bytes that the client has sent so far
     * 
     * @return The amount of data in bytes that the client has sent so far
     */
    long getReadBytes();
    
    /**
     * The amount of data that was sent to the client so far
     * 
     * @return The amount of data that was sent to the client so far
     */
    long getWrittenBytes();
    
    /**
     * The number of messages that were dropped since the connection
     * 
     * @return the number of messages that were dropped
     */
    int getDroppedMessages();
    
    /**
     * Get the maximum time of socket inactivity after which the system will consider the User "idle" and disconnect it.
     * 
     * @return the maximum idle time
     */
    int getMaxIdleTime();
    
    /**
     * Get the maximum time of User inactivity after which the system will consider the session "idle" and disconnect it.
     * 
     * @return the maximum logged in idle time 
     */
    int getMaxLoggedInIdleTime();
    
    /**
     * Indicate that the session is marked for eviction
     * 
     * @return true of false
     */
    boolean isMarkedForEviction();
    
    /**
     * Indicate that the session is idle
     * 
     * @return true or false
     */
    boolean isIdle();
    
    /**
     * Indicate that the session is frozen
     * 
     * @return true or false
     */
    boolean isFrozen();
    
    /**
     * @return the freeze time
     */
    long getFreezeTime();
    
    /**
     * Indicate that the session not able to re-connect
     * 
     * @return true or false
     */
    boolean isReconnectionTimeExpired();
    
    /**
     * Get system property by name
     * 
     * @param name =the name name of system property
     * @return the system property value
     */
    Object getSystemProperty(String name);
    
    /**
     * Set system property
     * 
     * @param name the name
     * @param value the value
     */
    void setSystemProperty(String name, Object value);
    
    /**
     * Remove system property
     * 
     * @param name the name
     */
    void removeSystemProperty(String name);
    
    /**
     * Get session property by name
     * 
     * @param name the name
     * @return the value
     */
    Object getProperty(String name);
    
    /**
     * Set session property
     * 
     * @param name the name
     * @param value the value
     */
    void setProperty(String name, Object value);
    
    /**
     * Remove session property
     * 
     * @param name the name
     */
    void removeProperty(String name);
    
    /**
     * Get the IPAddress and Port of the user as a String in the format "1.2.3.4:1234"
     * 
     * @return the ip address
     */
    String getFullIpAddress();
    
    /**
     * Get the session IP address
     * 
     * @return the session IP address
     */
    String getAddress();
    
    /**
     * Get the client side TCP port number
     * 
     * @return the client side TCP port number
     */
    int getClientPort();
    
    /**
     * Get the server address to which the user connected to
     * 
     * @return the server address
     */
    String getServerAddress();
    
    /**
     * Get the server port to which the user connected to
     * 
     * @return the server port
     */
    int getServerPort();
    
    /**
     * Get the full server address and port to which the user connected to, in the form "1.2.3.4:1234"
     * 
     * @return the server ip address
     */
    String getFullServerIpAddress();
    
    /**
     * Get the interval of time allowed for a ApiSession to attempt a reconnection after and abrupt loss of connection.
     * 
     * @return the reconnection time in second
     */
    int getReconnectionSeconds();
    
    /**
     * Indicate that session is encrypted
     * 
     * @return true or false
     */
    boolean isEncrypted();
    
    /**
     * @return the encryption key
     */
    Object getCryptoKey();
    
    /**
     * Set encryption key
     * 
     * @param key the key
     */
    void setCryptoKey(Object key);
    
    /**
     * Return the node Id in a cluster
     * 
     * @return the node Id in a cluster
     */
    String getNodeId();
}
