package com.tvd12.ezyfox.core.testing.structure;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.Variable;
import com.tvd12.ezyfox.core.annotation.VariableParam;
import com.tvd12.ezyfox.core.exception.ExtensionException;
import com.tvd12.ezyfox.core.reflect.ReflectFieldUtil;
import com.tvd12.ezyfox.core.reflect.ReflectMethodUtil;
import com.tvd12.ezyfox.core.structure.AgentClassWrapper;
import com.tvd12.ezyfox.core.structure.ClassWrapper;
import com.tvd12.ezyfox.core.structure.SetterMethodCover;
import com.tvd12.test.base.BaseTest;
import com.tvd12.test.reflect.MethodBuilder;
import com.tvd12.test.reflect.MethodInvoker;

import lombok.Data;

public class AgentClassWrapperTest extends BaseTest {

    @Test
    public void testValidCase() throws ExtensionException {
        AgentClassWrapper wrapper = new AgentClassWrapper(ClassA.class);
        assertEquals(wrapper.methodCount(), 9);
        assertEquals(wrapper.getMethod(0).getMethodName(), "setName");
        
        SetterMethodCover initWithMethod = (SetterMethodCover) MethodInvoker.create()
                .object(wrapper)
                .method("initWithMethod")
                .param(ReflectMethodUtil.getMethod("setName", ClassA.class, String.class)).invoke();
        assertNotNull(initWithMethod);
        
        wrapper.getMethod(0).setParameterClass(null);
        assertEquals(wrapper.getMethod(0).getParameterClass(), null);
    }
    
    @Test
    public void classWrapperTest() throws ExtensionException {
        ClassWrapper classWrapper = new ClassWrapper() {
            @Override
            protected <T extends Annotation> Class<T>[] getAnnotationClasses() {return null;}
            @Override
            protected ClassWrapper newClass() {return null;}
        };
        SetterMethodCover initWithField = (SetterMethodCover) MethodInvoker.create()
                .object(classWrapper)
                .method("initWithField")
                .param(ReflectFieldUtil.getField("name", ClassA.class)).invoke();
        assertNull(initWithField);

    }
    
    
    @Data
    public static class ClassA {

        @Variable(name = "nam")
        private String name = "dzung";
        
        private int age = 123;
        
        @Variable("nam")
        public void setName(String name) {
            
        }
        
        @Variable
        public String getValue1(String value1) {
            return value1;
        }
        
        @Variable("age")
        public void setAge(List<ClassB> bs) {
            
        }
        
        @Variable("z")
        public int getZ() {
            return 1;
        }
        
        @Variable
        private ClassA classA1;
        
        @Variable
        private ClassA classA2;
        
        @Variable
        private GirlFriend girlFriend = new GirlFriend();
        
        @Variable
        private ClassB classB;
        
        @Variable
        private List<Object> o1;
        
        @Variable
        private Object[] o2;
        
        @Variable
        private List<Object[]> o3;
    }
    
    @Data
    public static class ClassB {
        
        @VariableParam
        private ClassA classA;
        
        @VariableParam
        private ClassC classC1;
        
        @VariableParam
        private ClassC classC2;
    }
    
    @Data
    public static class ClassC {
        @VariableParam
        private ClassC classC;
        
        @VariableParam
        private ClassD classD;
    }
    
    @Data
    public static class ClassD {
        @VariableParam
        private ClassC classA;
        
        @VariableParam
        private ClassC classC;
        
        @VariableParam
        private ClassC classE;
    }
    
    @Data
    public static class ClassE {
        @VariableParam
        private ClassC classC;
        
        @VariableParam
        private ClassC classA;
    }
    
    @Data
    public static class GirlFriend {

        @VariableParam
        private String name = "kitty";
        
    }
    
    @Test
    public void initWithMethodOnClassWrapperTest() {
        Method method = MethodBuilder.create()
                .clazz(GirlFriend.class)
                .method("getName")
                .build();
        assertNull(MethodInvoker.create()
                .object(new ExClassWrapper())
                .method("initWithMethod")
                .param(method)
                .invoke());
    }
    
    public static class ExClassWrapper extends ClassWrapper {

        @Override
        protected ClassWrapper newClass() {
            return null;
        }

        @Override
        protected <T extends Annotation> Class<T>[] getAnnotationClasses() {
            return null;
        }
        
    }
}
