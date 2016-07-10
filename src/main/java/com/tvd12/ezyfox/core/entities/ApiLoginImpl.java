/**
 * 
 */
package com.tvd12.ezyfox.core.entities;

import lombok.Setter;

/**
 * @author tavandung12
 *
 */
@Setter
public class ApiLoginImpl implements ApiLogin {

    protected ApiZone zone;
    protected ApiSession session;
    protected String username;
    protected String password;
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.entities.ApiLogin#zone()
     */
    @Override
    public ApiZone zone() {
        return this.zone;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.entities.ApiLogin#session()
     */
    @Override
    public ApiSession session() {
        return this.session;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.entities.ApiLogin#username()
     */
    @Override
    public String username() {
        return this.username;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.entities.ApiLogin#password()
     */
    @Override
    public String password() {
        return this.password;
    }

}
