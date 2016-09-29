/**
 * 
 */
package com.tvd12.ezyfox.core.testing.utilities;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.ClientRequestListener;
import com.tvd12.ezyfox.core.structure.RoomRequestResponseClass;
import com.tvd12.ezyfox.core.testing.v117.roomconfig1.V117ClienRequestListener1;
import com.tvd12.ezyfox.core.testing.v117.roomconfig1.V117GameUser1;

import static org.testng.Assert.*;

/**
 * @author tavandung12
 *
 */
public class RoomRequestResponseClassTest {

    @Test
    public void test() {
        RoomRequestResponseClass clazz = new RoomRequestResponseClass();
        clazz.init(V117ClienRequestListener1.class);
        clazz.checkExecuteMethod(null, null);
        assertEquals(clazz.getUserClass(), V117GameUser1.class);
    }
    
    @Test(expectedExceptions = {IllegalStateException.class})
    public void checkExecuteMethodTestInvalid1() {
        RoomRequestResponseClass clazz = new RoomRequestResponseClass();
        clazz.init(Listener1.class);
        clazz.checkExecuteMethod(null, null);
    }
    
    @Test(expectedExceptions = {IllegalStateException.class})
    public void checkExecuteMethodTestInvalid2() {
        RoomRequestResponseClass clazz = new RoomRequestResponseClass();
        clazz.init(Listener2.class);
        clazz.checkExecuteMethod(null, null);
    }
    
    @ClientRequestListener(command = "abc")
    public static class Listener1 {
    }
    
    @ClientRequestListener(command = "abc")
    public static class Listener2 {
        
        public void execute(String a, String b) {
        }
    }
    
}
