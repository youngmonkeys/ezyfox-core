package com.tvd12.ezyfox.core.testing.parser;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.lang.reflect.Method;
import java.util.Properties;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.ConfigProperty;
import com.tvd12.ezyfox.core.exception.ExtensionException;
import com.tvd12.ezyfox.core.serialize.impl.ConfigPropertyDeserializer;
import com.tvd12.ezyfox.core.structure.PropertiesClassWrapper;
import com.tvd12.ezyfox.core.structure.SetterMethodCover;
import com.tvd12.test.base.BaseTest;
import com.tvd12.test.reflect.MethodBuilder;
import com.tvd12.test.reflect.ReflectMethodUtil;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ConfigPropertiesParserTest extends BaseTest {

    private Properties pro = new Properties();
    
	@ConfigProperty
	public String greet;
	
	public String howAreYouAnwser;
	
	private String str1;
	
	private String str2;
	
	public ConfigPropertiesParserTest() {
	    pro.setProperty("greet", "hello");
        pro.setProperty("abc", "halo");
        pro.setProperty("howAreYouAnwser", "I'm find, thanks!");
        pro.setProperty("str1", "String 1");
        pro.setProperty("2", "String 2");
	}
	
	@ConfigProperty
	public void setHowAreYouAnwser(String answer) {
	    this.howAreYouAnwser = answer;
	}
	
	@ConfigProperty("")
    public void setStr1(String str1) {
        this.str1 = str1;
    }
	
	@ConfigProperty("2")
    public void setStr2(String str2) {
        this.str2 = str2;
    }
	
	@Test
	public void testValidCase() throws ExtensionException {
		ConfigPropertiesParserTest config = new ConfigPropertiesParserTest();
		PropertiesClassWrapper wrapper = new PropertiesClassWrapper(config.getClass());
		new ConfigPropertyDeserializer().deserialize(wrapper, config, pro);
		assertEquals(config.getGreet(), "hello");
		assertEquals(config.getHowAreYouAnwser(), "I'm find, thanks!");
		assertEquals(config.getStr1(), "String 1");
		assertEquals(config.getStr2(), "String 2");
	}
	
	@Test(expectedExceptions = {IllegalStateException.class})
	public void callSetterMethodWithNoSetterMethodTest() throws ExtensionException {
	    ClassA config = new ClassA();
	    PropertiesClassWrapper wrapper = new PropertiesClassWrapper(config.getClass());
        new ConfigPropertyDeserializer().deserialize(wrapper, config, pro);
	}
	
	@Test
    public void getPropertyWithKeyTest() throws ExtensionException {
        ClassB config = new ClassB();
        PropertiesClassWrapper wrapper = new PropertiesClassWrapper(config.getClass());
        new ConfigPropertyDeserializer().deserialize(wrapper, config, pro);
        assertEquals("halo", config.getHello());
    }
	
	@Test
	public void assignValueTest() {
	    Object object = new Object();
	    Properties properties = new Properties();
	    properties.setProperty("value1", "true");
	    properties.setProperty("value2", "1");
	    properties.setProperty("value3", "a");
	    properties.setProperty("value4", "1.0");
	    properties.setProperty("value5", "2.0");
	    properties.setProperty("value6", "3");
	    properties.setProperty("value7", "4");
	    properties.setProperty("value8", "5");
	    properties.setProperty("value9", "hell");
	    properties.put("value10", object);
	    PropertiesClassWrapper wrapper = new PropertiesClassWrapper(ClassC.class);
	    ClassC classC = (ClassC) new ConfigPropertyDeserializer().deserialize(wrapper, properties);
	    assertEquals(true, classC.isValue1());
	    assertEquals(1, classC.getValue2());
	    assertEquals('a', classC.getValue3());
	    assertEquals(1.0, classC.getValue4());
	    assertEquals(2.0f, classC.getValue5());
	    assertEquals(3, classC.getValue6());
	    assertEquals(4, classC.getValue7());
	    assertEquals(5, classC.getValue8());
	    assertEquals("hell", classC.getValue9());
	    assertEquals(object, classC.getValue10());
	}
	
	@Test
    public void assignValueTestToObject() {
        Object object = new Object();
        Properties properties = new Properties();
        properties.setProperty("value1", "true");
        properties.setProperty("value2", "1");
        properties.setProperty("value3", "a");
        properties.setProperty("value4", "1.0");
        properties.setProperty("value5", "2.0");
        properties.setProperty("value6", "3");
        properties.setProperty("value7", "4");
        properties.setProperty("value8", "5");
        properties.setProperty("value9", "hell");
        properties.put("value10", object);
        ClassC classC = new ClassC();
        PropertiesClassWrapper wrapper = new PropertiesClassWrapper(ClassC.class);
        new ConfigPropertyDeserializer().deserialize(wrapper, classC, properties);
        assertEquals(true, classC.isValue1());
        assertEquals(1, classC.getValue2());
        assertEquals('a', classC.getValue3());
        assertEquals(1.0, classC.getValue4());
        assertEquals(2.0f, classC.getValue5());
        assertEquals(3, classC.getValue6());
        assertEquals(4, classC.getValue7());
        assertEquals(5, classC.getValue8());
        assertEquals("hell", classC.getValue9());
        assertEquals(object, classC.getValue10());
    }
	
	@Test
	public void getValueTest() {
	    SetterMethodCover setter = null; 
	    ConfigPropertyDeserializer ds = 
	            new ConfigPropertyDeserializer();
	    Method method = MethodBuilder.create()
	            .method("getValue")
	            .argument(SetterMethodCover.class)
	            .argument(Object.class)
	            .clazz(ConfigPropertyDeserializer.class)
	            .build();
	    setter = mock(SetterMethodCover.class);
        when(setter.isBoolean()).thenReturn(true);
	    Boolean boolValue = (Boolean) ReflectMethodUtil
	            .invokeMethod(method, ds, setter, new Boolean(true));
	    assertEquals(Boolean.TRUE, boolValue);
	    
	    setter = mock(SetterMethodCover.class);
        when(setter.isByte()).thenReturn(true);
        Byte byteValue = (Byte) ReflectMethodUtil
                .invokeMethod(method, ds, setter, new Byte((byte)1));
        assertEquals(new Byte((byte)1), byteValue);
        
        setter = mock(SetterMethodCover.class);
        when(setter.isChar()).thenReturn(true);
        Character charValue = (Character) ReflectMethodUtil
                .invokeMethod(method, ds, setter, new Character((char)1));
        assertEquals(new Character((char)1), charValue);
        
        setter = mock(SetterMethodCover.class);
        when(setter.isDouble()).thenReturn(true);
        Double doubleValue = (Double) ReflectMethodUtil
                .invokeMethod(method, ds, setter, new Double(1));
        assertEquals(new Double(1), doubleValue);
        
        setter = mock(SetterMethodCover.class);
        when(setter.isFloat()).thenReturn(true);
        Float floatValue = (Float) ReflectMethodUtil
                .invokeMethod(method, ds, setter, new Float(1));
        assertEquals(new Float(1), floatValue);
        
        setter = mock(SetterMethodCover.class);
        when(setter.isInt()).thenReturn(true);
        Integer intValue = (Integer) ReflectMethodUtil
                .invokeMethod(method, ds, setter, new Integer(1));
        assertEquals(new Integer(1), intValue);
        
        setter = mock(SetterMethodCover.class);
        when(setter.isLong()).thenReturn(true);
        Long longValue = (Long) ReflectMethodUtil
                .invokeMethod(method, ds, setter, new Long(1));
        assertEquals(new Long(1), longValue);
        
        setter = mock(SetterMethodCover.class);
        when(setter.isShort()).thenReturn(true);
        Short shortValue = (Short) ReflectMethodUtil
                .invokeMethod(method, ds, setter, new Short((short) 1));
        assertEquals(new Short((short) 1), shortValue);
        
        setter = mock(SetterMethodCover.class);
        when(setter.isString()).thenReturn(true);
        String stringValue = (String) ReflectMethodUtil
                .invokeMethod(method, ds, setter, new String("hello"));
        assertEquals("hello", stringValue);
        
        Object object = new Object();
        setter = mock(SetterMethodCover.class);
        Object objectValue = ReflectMethodUtil
                .invokeMethod(method, ds, setter, object);
        assertEquals(object, objectValue);
	}
	
	@Test
	public void constructorTest() {
	    Object object = ReflectMethodUtil.invokeConstructor(ConfigPropertyDeserializer.class);
	    assertNotNull(object);
	}
	
	public static class ClassA {
	    @ConfigProperty
	    public String greet;
	}
	
	@Data
	public static class ClassB {
	    @ConfigProperty("abc")
	    public String hello;
	}
	
	@Data
	public static class ClassC {
	    @ConfigProperty
	    public boolean value1;
	    
	    @ConfigProperty
        public byte value2;
	    
	    @ConfigProperty
        public char value3;
	    
	    @ConfigProperty
        public double value4;
	    
	    @ConfigProperty
        public float value5;
	    
	    @ConfigProperty
        public int value6;
	    
	    @ConfigProperty
        public long value7;
	    
	    @ConfigProperty
	    public short value8;
	    
	    @ConfigProperty
	    public String value9;
	    
	    @ConfigProperty
	    public Object value10;
	}
}
