package com.tvd12.ezyfox.core.testing.context;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Map;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.command.AddCommand;
import com.tvd12.ezyfox.core.command.impl.AddCommandImpl;
import com.tvd12.ezyfox.core.constants.APIEvent;
import com.tvd12.ezyfox.core.constants.ServerEvent;
import com.tvd12.ezyfox.core.content.impl.BaseAppContext;
import com.tvd12.ezyfox.core.exception.ExtensionException;
import com.tvd12.ezyfox.core.reflect.ReflectClassUtil;
import com.tvd12.ezyfox.core.reflect.ReflectFieldUtil;
import com.tvd12.ezyfox.core.transport.Parameters;
import com.tvd12.test.reflect.MethodInvoker;

import lombok.Data;

public class AppContextImplTest {
    
    private BaseAppContext context;
    
    @SuppressWarnings("unchecked")
    public AppContextImplTest() {
        context = newAppContext();
        context.addAppCommand(AppCommand.class, AppCommand.class);
        context.addAppCommand(AppCommand2.class, AppCommand2.class);
        assertTrue(context.isAutoResponse(APIEvent.ZONE_JOIN));
        assertFalse(context.isAutoResponse("zzzzzz"));
        context.set("hello", "world");
        assertEquals(context.get("hello", String.class), "world");
        assertEquals(context.getUserAgentClass().getWrapper().getClazz(), AppUser.class);
        assertTrue(context.getRoomClasses().size() > 0);
        assertTrue(context.getGameUserClasses().size() > 0);
        assertNotNull(context.getRoomAgentClass(PokerRoom.class));
        assertNotNull(context.getUserAgentClass(AppUser.class));
        assertNotNull(context.getUserAgentClass(PokerUser.class));
        assertEquals(context.getUserClass(), AppUser.class);
        assertTrue(context.getRoomClasses().size() > 0);
        assertTrue(context.getGameUserClasses().size() > 0);
        assertNotNull(context.getResponseParamsClass(VideoPokerRoom.class));
        assertNotNull(context.getMessageParamsClass(PokerMessages.class));
        assertNotNull(context.getResponseParamsClass(PokerMessages.class));
        assertTrue(context.getClientRequestListeners("bet").size() > 0);
        assertTrue(context.getServerEventHandlerClasses(ServerEvent.USER_JOIN_ROOM).size() > 0);
        assertTrue(context.getClientRequestCommands().size() > 0);
        assertEquals(context.command(AddCommand.class).getClass(), AddCommandImpl.class);
        assertNotNull(context.command(AppCommand.class));
        assertNotNull(context.command(PokerUser.class));
        
        AppUser user = new AppUser();
        user.setName("dung");
        Parameters params = context.getObjectSerializer(AppUser.class).serialize(user);
        assertEquals(params.get("name", String.class), "dung");
        
        user = (AppUser) context.getObjectDeserializer(AppUser.class).deserialize(null, params);
        assertEquals(user.getName(), "dung");
    }
    
    private BaseAppContext newAppContext() {
        BaseAppContext answer = new ExAppContextImpl();
        answer.initialize(AppEntryPoint.class);
        answer.addObjectSerializer(AppUser.class, new UserSerializer());
        answer.addObjectDeserializer(AppUser.class, new UserDeserializer());
        return answer;
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void getAppCommandInvalidCase() {
        context.command(Class.class);
    }
    
    @Test(expectedExceptions = {RuntimeException.class})
    public void getAppCommandInvalidCase2() {
        context.command(AppCommand2.class);
    }
    
    @Test(expectedExceptions = {RuntimeException.class})
    public void addAppCommandInvalidCase() {
        context.addAppCommand(AppCommand4.class, AppCommand4.class);
    }
    
    @Test(expectedExceptions = {RuntimeException.class})
    public void getAddFixedCommandInvalidCaseTest() {
        MethodInvoker.create()
            .object(context)
            .method("addFixedCommand")
            .param(ClassA.class)
            .param(ClassA.class)
            .invoke();
    }
    
    @Test(expectedExceptions = {RuntimeException.class})
    public void getCommandConstructorInvalidCaseTest() {
        MethodInvoker.create()
            .object(context)
            .method("getCommandConstructor")
            .param(ClassA.class)
            .invoke();
    }
    
    @Test(expectedExceptions = {RuntimeException.class})
    public void getCommandInvalidCaseTest() {
        MethodInvoker.create()
            .object(context)
            .method("getCommand")
            .param(ClassA.class)
            .invoke();
    }
    
    @SuppressWarnings("unchecked")
    @Test(expectedExceptions = {RuntimeException.class})
    public void getCommandInvalidCase2Test() throws ExtensionException, IllegalArgumentException, IllegalAccessException {
        Field commandField = ReflectFieldUtil.getField("commands", BaseAppContext.class);
        commandField.setAccessible(true);
        Map<Object, Constructor<?>> value = (Map<Object, Constructor<?>>)
                commandField.get(context);
        value.put("abc", ReflectClassUtil.getDefaultConstructor(ClassB.class));
        MethodInvoker.create()
            .object(context)
            .method("getCommand")
            .param(ClassB.class)
            .invoke();
    }
    
    @Data
    public static class ClassA {
        public ClassA(String a) {
            
        }
    }
    
    public static class ClassB {
        
    }
    
}
