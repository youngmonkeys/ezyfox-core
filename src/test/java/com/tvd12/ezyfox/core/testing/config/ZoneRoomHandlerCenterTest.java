/**
 * 
 */
package com.tvd12.ezyfox.core.testing.config;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.HandleMethod;
import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.config.ServerEvent;
import com.tvd12.ezyfox.core.config.ZoneRoomHandlerCenter;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.entities.ApiRoom;
import com.tvd12.ezyfox.core.entities.ApiZone;
import com.tvd12.test.base.BaseTest;

import static org.testng.Assert.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tavandung12
 *
 */
public class ZoneRoomHandlerCenterTest extends BaseTest {
    
    private List<Class<?>> roomClasses = new ArrayList<>();
    public ZoneRoomHandlerCenterTest() {
        roomClasses.add(ExRoom.class);
        roomClasses.add(ExRoom1.class);
    }

    @Test
    public void test() {
        List<Class<?>> handleClasses = new ArrayList<>();
        handleClasses.add(InviteHandler.class);
        handleClasses.add(Handle.class);
        handleClasses.add(Handle1.class);
        ZoneRoomHandlerCenter center = new ZoneRoomHandlerCenter();
        assertEquals(center.addHandlers(handleClasses, roomClasses.toArray(new Class[roomClasses.size()])).size(), 3);
    }
    
    @Test(expectedExceptions = {RuntimeException.class})
    public void test1() {
        List<Class<?>> handleClasses = new ArrayList<>();
        handleClasses.add(Handle2.class);
        ZoneRoomHandlerCenter center = new ZoneRoomHandlerCenter();
        center.addHandlers(handleClasses, roomClasses.toArray(new Class[roomClasses.size()]));
    }
    
    @Test(expectedExceptions = {RuntimeException.class})
    public void test2() {
        List<Class<?>> handleClasses = new ArrayList<>();
        handleClasses.add(Handle3.class);
        ZoneRoomHandlerCenter center = new ZoneRoomHandlerCenter();
        center.addHandlers(handleClasses, roomClasses.toArray(new Class[roomClasses.size()]));
    }
    
    @Test(expectedExceptions = {RuntimeException.class})
    public void test3() {
        List<Class<?>> handleClasses = new ArrayList<>();
        handleClasses.add(Handle4.class);
        ZoneRoomHandlerCenter center = new ZoneRoomHandlerCenter();
        center.addHandlers(handleClasses, roomClasses.toArray(new Class[roomClasses.size()]));
    }
    
    @Test(expectedExceptions = {RuntimeException.class})
    public void test4() {
        List<Class<?>> handleClasses = new ArrayList<>();
        handleClasses.add(Handle5.class);
        ZoneRoomHandlerCenter center = new ZoneRoomHandlerCenter();
        center.addHandlers(handleClasses, roomClasses.toArray(new Class[roomClasses.size()]));
    }
    
    @Test(expectedExceptions = {RuntimeException.class})
    public void test5() {
        List<Class<?>> handleClasses = new ArrayList<>();
        handleClasses.add(Handle6.class);
        ZoneRoomHandlerCenter center = new ZoneRoomHandlerCenter();
        center.addHandlers(handleClasses, roomClasses.toArray(new Class[roomClasses.size()]));
    }
    
    public static class ExRoom {
        
    }
    
    public static class ExRoom1 {
        
    }
    
    @ServerEventHandler(event = ServerEvent.GAME_INVITATION_SUCCESS)
    public static class InviteHandler {
        public void handle(AppContext context, ApiZone zone, ApiRoom room) {
            
        }
    }
    
    @ServerEventHandler(event = ServerEvent.GAME_INVITATION_SUCCESS)
    public static class Handle {
        public void handle(AppContext context, ApiZone zone, ExRoom room) {
            
        }
    }
    
    @ServerEventHandler(event = ServerEvent.GAME_INVITATION_SUCCESS)
    public static class Handle1 {
        @HandleMethod
        public void exe(AppContext context, ApiZone zone, ExRoom room) {
            
        }
    }
    
    public static class Handle2 {
        public void exe(AppContext context, ApiZone zone, ExRoom room) {
            
        }
    }
    
    public static class Handle3 {
        public void handle() {
            
        }
    }
    
    public static class Handle4 {
        public void handle(Class<?> context, ApiZone zone, ExRoom room) {
            
        }
    }
    
    public static class Handle5 {
        public void handle(AppContext context, Class<?> zone, ExRoom room) {
            
        }
    }
    
    public static class Handle6 {
        public void handle(AppContext context, ApiZone zone, Class<?> room) {
            
        }
    }
}
