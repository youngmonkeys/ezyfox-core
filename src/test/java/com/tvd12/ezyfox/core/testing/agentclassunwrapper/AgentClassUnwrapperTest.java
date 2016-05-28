package com.tvd12.ezyfox.core.testing.agentclassunwrapper;

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
import com.tvd12.ezyfox.core.structure.AgentClassUnwrapper;
import com.tvd12.ezyfox.core.structure.ClassCover;
import com.tvd12.ezyfox.core.structure.ClassUnwrapper;
import com.tvd12.ezyfox.core.structure.GetterMethodCover;
import com.tvd12.test.base.BaseTest;
import com.tvd12.test.reflect.MethodBuilder;
import com.tvd12.test.reflect.MethodInvoker;

import lombok.Data;

public class AgentClassUnwrapperTest extends BaseTest {

    @Test
    public void testValidCase() throws ExtensionException {
        AgentClassUnwrapper unwrapper = new AgentClassUnwrapper(ClassA.class);
        assertEquals(ClassA.class, unwrapper.getClazz());
        assertEquals(unwrapper.getMethod(0).getKey(), "nam");
        assertEquals(unwrapper.methodCount(), 9);
        assertEquals(unwrapper.getMethods().size(), 9);
        
        GetterMethodCover initWithMethod = (GetterMethodCover) MethodInvoker.create()
                .object(unwrapper)
                .method("initWithMethod")
                .param(ReflectMethodUtil.getMethod("getName", ClassA.class)).invoke();
        assertNotNull(initWithMethod);
    }
    
    @Test
    public void classUnwrapperTest() throws ExtensionException {
        ClassUnwrapper classUnwrapper = new ClassUnwrapper() {
            
            @Override
            protected <T extends Annotation> Class<T>[] getAnnotationClasses() {return null;}
            @Override
            protected ClassUnwrapper newClass() {return null;}
        };
        GetterMethodCover initWithField = (GetterMethodCover) MethodInvoker.create()
                .object(classUnwrapper)
                .method("initWithField")
                .param(ReflectFieldUtil.getField("name", ClassA.class)).invoke();
        assertNull(initWithField);

    }
    
    @Data
    public static class ClassA {

        @Variable(name = "nam")
        private String name = "dzung";
        
        @Variable("age")
        public int age() {
            return 123;
        }
        
        @Variable("nam")
        public String getName() {
            return name;
        }
        
        @Variable("z")
        public void setZ(String z) {
            
        }
        
        @Variable
        public int setValue1(String value) {
            return 0;
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
    public void initWithMethodOnClassUnWrapperTest() {
        Method method = MethodBuilder.create()
                .clazz(GirlFriend.class)
                .method("getName")
                .build();
        assertNull(MethodInvoker.create()
                .object(new ExClassUnwrapper())
                .method("initWithMethod")
                .param(method)
                .invoke());
    }
    
    @Test
    public void getDefaultMethodInvalidCase() {
        Method method = MethodBuilder.create()
                .clazz(ClassCover.class)
                .method("getDefaultMethod")
                .argument(String.class)
                .argument(Class[].class)
                .build();
        assertNull(MethodInvoker.create()
                .object(new AgentClassUnwrapper(ClassA.class))
                .method(method)
                .param("hello")
                .param(new Class[] {})
                .invoke());
    }
    
    public static class ExClassUnwrapper extends ClassUnwrapper {

        @Override
        protected ClassUnwrapper newClass() {
            return null;
        }

        @Override
        protected <T extends Annotation> Class<T>[] getAnnotationClasses() {
            return null;
        }
        
    }
    
}
