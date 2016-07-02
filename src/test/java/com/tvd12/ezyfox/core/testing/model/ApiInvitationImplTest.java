/**
 * 
 */
package com.tvd12.ezyfox.core.testing.model;


import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.model.ApiInvitationImpl;
import com.tvd12.ezyfox.core.model.ApiUser;
import com.tvd12.ezyfox.core.transport.Parameters;
import com.tvd12.ezyfox.core.transport.impl.ParameterWrapper;
import com.tvd12.test.base.BaseTest;

import static org.testng.Assert.*;

/**
 * @author tavandung12
 *
 */
public class ApiInvitationImplTest extends BaseTest {

    @Test
    public void test() {
        ApiUser u1 = new ApiUser() {
        };
        ApiUser u2 = new ApiUser() {
        };
        Parameters params = new ParameterWrapper();
        ApiInvitationImpl obj = new ApiInvitationImpl();
        obj.setExpired(true);
        obj.setExpiryTime(10);
        obj.setId(11);
        obj.setInvitee(u1);
        obj.setInviter(u2);
        obj.setParams(params);
        obj.setSecondsForAnswer(12);
        
        assertEquals(obj.expired(), true);
        assertEquals(obj.expiryTime(), 10);
        assertEquals(obj.id(), 11);
        assertEquals(obj.invitee(), u1);
        assertEquals(obj.inviter(), u2);
        assertEquals(obj.params(), params);
        assertEquals(obj.secondsForAnswer(), 12);
        
    }
    
}
