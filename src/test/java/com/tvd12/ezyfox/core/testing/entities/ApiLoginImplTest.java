/**
 * 
 */
package com.tvd12.ezyfox.core.testing.entities;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.entities.ApiLoginImpl;
import com.tvd12.ezyfox.core.entities.ApiSession;
import com.tvd12.ezyfox.core.entities.ApiZone;

import static org.testng.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author tavandung12
 *
 */
public class ApiLoginImplTest {

    @Test
    public void test() {
        ApiZone zone = mock(ApiZone.class);
        ApiSession session = mock(ApiSession.class);
        String username = "u";
        String password = "p";
        ApiLoginImpl data = new ApiLoginImpl();
        data.setZone(zone);
        data.setSession(session);
        data.setUsername(username);
        data.setPassword(password);
        
        assertEquals(data.zone(), zone);
        assertEquals(data.session(), session);
        assertEquals(data.username(), username);
        assertEquals(data.password(), password);
    }
    
}
