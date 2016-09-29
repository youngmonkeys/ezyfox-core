package com.tvd12.ezyfox.core.testing.structure;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.exception.ExtensionException;
import com.tvd12.ezyfox.core.reflect.ReflectFieldUtil;
import com.tvd12.ezyfox.core.reflect.ReflectMethodUtil;
import com.tvd12.ezyfox.core.structure.MethodCover;
import com.tvd12.test.base.BaseTest;
import com.tvd12.test.reflect.MethodBuilder;
import com.tvd12.test.reflect.MethodInvoker;

import lombok.Data;

import static org.testng.Assert.*;

public class MethodCoverTest extends BaseTest {

    @Test
    public void test() {
        Method initWithFieldMethod = MethodBuilder.create()
                .clazz(MethodCover.class)
                .method("initWithField")
                .argument(Class.class)
                .argument(Field.class)
                .build();
        Field[] fields = ClassA.class.getDeclaredFields();
        MethodCover methodCovers[] = new MethodCover[fields.length];
        for(int i = 0 ; i < fields.length ; i++) {
            methodCovers[i] = new MethodCover();
            MethodInvoker.create()
                .object(methodCovers[i])
                .method(initWithFieldMethod)
                .param(ClassA.class)
                .param(fields[i]).invoke();
        }
        
        assertTrue(methodCovers[0].isString());
        assertTrue(methodCovers[1].isPrimitiveBoolean());
        assertTrue(methodCovers[2].isPrimitiveByte());
        assertTrue(methodCovers[3].isPrimitiveChar());
        assertTrue(methodCovers[4].isPrimitiveDouble());
        assertTrue(methodCovers[5].isPrimitiveFloat());
        assertTrue(methodCovers[6].isPrimitiveInt());
        assertTrue(methodCovers[7].isPrimitiveLong());
        assertTrue(methodCovers[8].isPrimitiveShort());
        
        assertTrue(methodCovers[0].isString());
        assertTrue(methodCovers[1].isBoolean());
        assertTrue(methodCovers[2].isByte());
        assertTrue(methodCovers[3].isChar());
        assertTrue(methodCovers[4].isDouble());
        assertTrue(methodCovers[5].isFloat());
        assertTrue(methodCovers[6].isInt());
        assertTrue(methodCovers[7].isLong());
        assertTrue(methodCovers[8].isShort());
        
        
        assertTrue(methodCovers[9].isString());
        assertTrue(methodCovers[10].isWrapperBoolean());
        assertTrue(methodCovers[11].isWrapperByte());
        assertTrue(methodCovers[12].isWrapperChar());
        assertTrue(methodCovers[13].isWrapperDouble());
        assertTrue(methodCovers[14].isWrapperFloat());
        assertTrue(methodCovers[15].isWrapperInt());
        assertTrue(methodCovers[16].isWrapperLong());
        assertTrue(methodCovers[17].isWrapperShort());
        
        assertTrue(methodCovers[9].isString());
        assertTrue(methodCovers[10].isBoolean());
        assertTrue(methodCovers[11].isByte());
        assertTrue(methodCovers[12].isChar());
        assertTrue(methodCovers[13].isDouble());
        assertTrue(methodCovers[14].isFloat());
        assertTrue(methodCovers[15].isInt());
        assertTrue(methodCovers[16].isLong());
        assertTrue(methodCovers[17].isShort());
        
        assertTrue(methodCovers[18].isStringArray());
        assertTrue(methodCovers[19].isPrimitiveBooleanArray());
        assertTrue(methodCovers[20].isPrimitiveByteArray());
        assertTrue(methodCovers[21].isPrimitiveCharArray());
        assertTrue(methodCovers[22].isPrimitiveDoubleArray());
        assertTrue(methodCovers[23].isPrimitiveFloatArray());
        assertTrue(methodCovers[24].isPrimitiveIntArray());
        assertTrue(methodCovers[25].isPrimitiveLongArray());
        assertTrue(methodCovers[26].isPrimitiveShortArray());
        
        assertTrue(methodCovers[18].isStringArray());
        assertFalse(methodCovers[26].isPrimitiveBooleanArray());
        assertFalse(methodCovers[25].isPrimitiveByteArray());
        assertFalse(methodCovers[24].isPrimitiveCharArray());
        assertFalse(methodCovers[23].isPrimitiveDoubleArray());
        assertFalse(methodCovers[22].isPrimitiveFloatArray());
        assertFalse(methodCovers[21].isPrimitiveIntArray());
        assertFalse(methodCovers[20].isPrimitiveLongArray());
        assertFalse(methodCovers[19].isPrimitiveShortArray());
        
        assertFalse(methodCovers[0].isStringArray());
        assertFalse(methodCovers[0].isPrimitiveBooleanArray());
        assertFalse(methodCovers[0].isPrimitiveByteArray());
        assertFalse(methodCovers[0].isPrimitiveCharArray());
        assertFalse(methodCovers[0].isPrimitiveDoubleArray());
        assertFalse(methodCovers[0].isPrimitiveFloatArray());
        assertFalse(methodCovers[0].isPrimitiveIntArray());
        assertFalse(methodCovers[0].isPrimitiveLongArray());
        assertFalse(methodCovers[0].isPrimitiveShortArray());
        
        assertTrue(methodCovers[18].isStringArray());
        assertTrue(methodCovers[19].isBooleanArray());
        assertTrue(methodCovers[20].isByteArray());
        assertTrue(methodCovers[21].isCharArray());
        assertTrue(methodCovers[22].isDoubleArray());
        assertTrue(methodCovers[23].isFloatArray());
        assertTrue(methodCovers[24].isIntArray());
        assertTrue(methodCovers[25].isLongArray());
        assertTrue(methodCovers[26].isShortArray());
        
        assertTrue(methodCovers[27].isStringArray());
        assertTrue(methodCovers[28].isWrapperBooleanArray());
        assertTrue(methodCovers[29].isWrapperByteArray());
        assertTrue(methodCovers[30].isWrapperCharArray());
        assertTrue(methodCovers[31].isWrapperDoubleArray());
        assertTrue(methodCovers[32].isWrapperFloatArray());
        assertTrue(methodCovers[33].isWrapperIntArray());
        assertTrue(methodCovers[34].isWrapperLongArray());
        assertTrue(methodCovers[35].isWrapperShortArray());
        
        assertFalse(methodCovers[35].isStringArray());
        assertFalse(methodCovers[34].isWrapperBooleanArray());
        assertFalse(methodCovers[33].isWrapperByteArray());
        assertFalse(methodCovers[32].isWrapperCharArray());
        assertFalse(methodCovers[32].isWrapperDoubleArray());
        assertFalse(methodCovers[31].isWrapperFloatArray());
        assertFalse(methodCovers[30].isWrapperIntArray());
        assertFalse(methodCovers[29].isWrapperLongArray());
        assertFalse(methodCovers[28].isWrapperShortArray());
        
        assertFalse(methodCovers[0].isStringArray());
        assertFalse(methodCovers[0].isWrapperBooleanArray());
        assertFalse(methodCovers[0].isWrapperByteArray());
        assertFalse(methodCovers[0].isWrapperCharArray());
        assertFalse(methodCovers[0].isWrapperDoubleArray());
        assertFalse(methodCovers[0].isWrapperFloatArray());
        assertFalse(methodCovers[0].isWrapperIntArray());
        assertFalse(methodCovers[0].isWrapperLongArray());
        assertFalse(methodCovers[0].isWrapperShortArray());
        
        assertTrue(methodCovers[27].isStringArray());
        assertTrue(methodCovers[28].isBooleanArray());
        assertTrue(methodCovers[29].isByteArray());
        assertTrue(methodCovers[30].isCharArray());
        assertTrue(methodCovers[31].isDoubleArray());
        assertTrue(methodCovers[32].isFloatArray());
        assertTrue(methodCovers[33].isIntArray());
        assertTrue(methodCovers[34].isLongArray());
        assertTrue(methodCovers[35].isShortArray());
        
        assertFalse(methodCovers[35].isStringArray());
        assertFalse(methodCovers[34].isBooleanArray());
        assertFalse(methodCovers[33].isByteArray());
        assertFalse(methodCovers[32].isCharArray());
        assertFalse(methodCovers[30].isDoubleArray());
        assertFalse(methodCovers[29].isFloatArray());
        assertFalse(methodCovers[28].isIntArray());
        assertFalse(methodCovers[27].isLongArray());
        assertFalse(methodCovers[28].isShortArray());
        
        assertFalse(methodCovers[0].isStringArray());
        assertFalse(methodCovers[0].isBooleanArray());
        assertFalse(methodCovers[0].isByteArray());
        assertFalse(methodCovers[0].isCharArray());
        assertFalse(methodCovers[0].isDoubleArray());
        assertFalse(methodCovers[0].isFloatArray());
        assertFalse(methodCovers[0].isIntArray());
        assertFalse(methodCovers[0].isLongArray());
        assertFalse(methodCovers[0].isShortArray());
        
        assertTrue(methodCovers[36].isStringCollection());
        assertTrue(methodCovers[37].isBooleanCollection());
        assertTrue(methodCovers[38].isByteCollection());
        assertTrue(methodCovers[39].isCharCollection());
        assertTrue(methodCovers[40].isDoubleCollection());
        assertTrue(methodCovers[41].isFloatCollection());
        assertTrue(methodCovers[42].isIntCollection());
        assertTrue(methodCovers[43].isLongCollection());
        assertTrue(methodCovers[44].isShortCollection());
        
        
        assertFalse(methodCovers[44].isStringCollection());
        assertFalse(methodCovers[43].isBooleanCollection());
        assertFalse(methodCovers[42].isByteCollection());
        assertFalse(methodCovers[41].isCharCollection());
        assertFalse(methodCovers[41].isDoubleCollection());
        assertFalse(methodCovers[39].isFloatCollection());
        assertFalse(methodCovers[38].isIntCollection());
        assertFalse(methodCovers[37].isLongCollection());
        assertFalse(methodCovers[36].isShortCollection());
        
        assertFalse(methodCovers[0].isStringCollection());
        assertFalse(methodCovers[0].isBooleanCollection());
        assertFalse(methodCovers[0].isByteCollection());
        assertFalse(methodCovers[0].isCharCollection());
        assertFalse(methodCovers[0].isDoubleCollection());
        assertFalse(methodCovers[0].isFloatCollection());
        assertFalse(methodCovers[0].isIntCollection());
        assertFalse(methodCovers[0].isLongCollection());
        assertFalse(methodCovers[0].isShortCollection());
        
        assertTrue(methodCovers[45].isArray());
        assertTrue(methodCovers[45].isObjectArray());
        assertTrue(methodCovers[46].isObjectCollection());
        assertTrue(methodCovers[47].isArrayCollection());
        assertTrue(methodCovers[48].isArrayObjectCollection());
        assertTrue(methodCovers[49].isObject());
        assertFalse(methodCovers[49].isPrimitive());
        assertTrue(methodCovers[51].isTwoDimensionsArray());
        assertEquals("a49", methodCovers[49].getField().getName());
        assertEquals("a49", methodCovers[49].getField().getName());
        
//        assertTrue(methodCovers[9].isString());
//        assertTrue(methodCovers[10].isBoolean());
//        assertTrue(methodCovers[11].isByte());
//        assertTrue(methodCovers[12].isChar());
//        assertTrue(methodCovers[13].isDouble());
//        assertTrue(methodCovers[14].isFloat());
//        assertTrue(methodCovers[15].isInt());
//        assertTrue(methodCovers[16].isLong());
//        assertTrue(methodCovers[17].isShort());
        
    }

    @Test
    public void initWithMethodTest() {
        Method method = MethodBuilder.create()
                .clazz(MethodCover.class)
                .method("initWithMethod")
                .argument(Class.class)
                .argument(Method.class)
                .build();
        MethodCover cover = new MethodCover();
        Method setMethod = MethodBuilder.create()
                .clazz(ClassA.class)
                .method("setA0")
                .argument(String.class).build();
        MethodInvoker.create()
            .object(cover)
            .method(method)
            .param(ClassA.class)
            .param(setMethod)
            .invoke();
        assertEquals(cover.getKey(), "a0");
        assertEquals(setMethod, cover.getMethod());
        assertNull(MethodInvoker.create()
                .object(cover)
                .method("getGenericTypeFromMethod")
                .param(setMethod).invoke());
        assertFalse(cover.isHidden());
        assertEquals(setMethod.getName(), cover.getMethodName());
    }
    
    @Test(expectedExceptions = {IllegalStateException.class})
    public void checkTwoDimensionsArrayCollectionTest() throws ExtensionException {
        Field filed1 = ReflectFieldUtil.getField("a1", ClassB.class);
        MethodCover cover = new MethodCover();
        MethodInvoker.create().object(cover).method("initWithField")
            .param(ClassA.class).param(filed1).invoke();
        MethodInvoker.create()
            .object(cover).method("checkTwoDimensionsArrayCollection").invoke();
    }
    
    @Test(expectedExceptions = {IllegalStateException.class})
    public void getGenericTypeFromFieldTest() throws ExtensionException {
        Field filed1 = ReflectFieldUtil.getField("a2", ClassB.class);
        MethodCover cover = new MethodCover();
        MethodInvoker.create().object(cover).method("initWithField")
            .param(ClassA.class).param(filed1).invoke();
    }
    
    @Test
    public void getKeyTest() {
        MethodCover cover = new MethodCover();
        String key = (String)MethodInvoker.create().object(cover).method("getKey")
                .param(MethodBuilder.create().clazz(ClassB.class).method("hasLove").build())
                .invoke();
        assertEquals(key, "love");
        
        key = (String)MethodInvoker.create().object(cover).method("getKey")
                .param(MethodBuilder.create().clazz(ClassB.class).method("isVisible").build())
                .invoke();
        assertEquals(key, "visible");
        
        key = (String)MethodInvoker.create().object(cover).method("getKey")
                .param(MethodBuilder.create().clazz(ClassB.class).method("getHehe").build())
                .invoke();
        assertEquals(key, "hehe");
        
        key = (String)MethodInvoker.create().object(cover).method("getKey")
                .param(MethodBuilder.create().clazz(ClassB.class).method("setHehe").argument(String.class).build())
                .invoke();
        assertEquals(key, "hehe");
    }
    
    
    @Data
    public static class ClassA {
        public String a0;
        public boolean a1;
        public byte a2;
        public char a3;
        public double a4;
        public float a5;
        public int a6;
        public long a7;
        public short a8;
        
        public String a9;
        public Boolean a10;
        public Byte a11;
        public Character a12;
        public Double a13;
        public Float a14;
        public Integer a15;
        public Long a16;
        public Short a17;
        
        public String[] a18;
        public boolean[] a19;
        public byte[] a20;
        public char[] a21;
        public double[] a22;
        public float[] a23;
        public int[] a24;
        public long[] a25;
        public short[] a26;
        
        public String[] a27;
        public Boolean[] a28;
        public Byte[] a29;
        public Character[] a30;
        public Double[] a31;
        public Float[] a32;
        public Integer[] a33;
        public Long[] a34;
        public Short[] a35;
        
        public List<String> a36;
        public List<Boolean> a37;
        public List<Byte> a38;
        public List<Character> a39;
        public List<Double> a40;
        public List<Float> a41;
        public List<Integer> a42;
        public List<Long> a43;
        public List<Short> a44;
        
        public Object[] a45;
        public List<Object> a46;
        public List<byte[]> a47;
        public List<Object[]> a48;
        
        public Object a49;
        public ClassA a50;
        public int[][] a51;
        
    }
    
    @Data
    public static class ClassB {
        public int[][] a0;
        public List<int[][]> a1;
        public List<?> a2;
        
        public int[] a3;
        
        public List<Integer> a4;
        
        public String hehe;
        
        public boolean visible;
        
        public boolean hasLove() {
            return true;
        }
        
        public void setHaha(String haha) {
            
        }
        
        public void setHaha(String haha, String hehe) {
            
        }
    }
    
    @Test
    public void checkTwoDimensionsArrayValidCaseTest1() throws ExtensionException {
        MethodCover methodCover = new MethodCover();
        Field field = ReflectFieldUtil.getField("hehe", ClassB.class);
        field.setAccessible(true);
        MethodInvoker.create()
            .object(methodCover)
            .method("initWithField")
            .param(ClassB.class)
            .param(field)
            .invoke();
        MethodInvoker.create()
        .object(methodCover)
        .method("checkTwoDimensionsArray")
        .invoke();
    }
    
    @Test
    public void checkTwoDimensionsArrayValidCaseTest2() throws ExtensionException {
        MethodCover methodCover = new MethodCover();
        Field field = ReflectFieldUtil.getField("a3", ClassB.class);
        field.setAccessible(true);
        MethodInvoker.create()
            .object(methodCover)
            .method("initWithField")
            .param(ClassB.class)
            .param(field)
            .invoke();
        
        MethodInvoker.create()
        .object(methodCover)
        .method("checkTwoDimensionsArray")
        .invoke();
    }
    
    @Test
    public void checkTwoDimensionsArrayCollectionValidCaseTest1() throws ExtensionException {
        MethodCover methodCover = new MethodCover();
        Field field = ReflectFieldUtil.getField("hehe", ClassB.class);
        field.setAccessible(true);
        MethodInvoker.create()
            .object(methodCover)
            .method("initWithField")
            .param(ClassB.class)
            .param(field)
            .invoke();
        
        MethodInvoker.create()
        .object(methodCover)
        .method("checkTwoDimensionsArrayCollection")
        .invoke();
    }
    
    @Test
    public void checkTwoDimensionsArrayCollectionValidCaseTest2() throws ExtensionException {
        MethodCover methodCover = new MethodCover();
        Field field = ReflectFieldUtil.getField("a4", ClassB.class);
        field.setAccessible(true);
        MethodInvoker.create()
            .object(methodCover)
            .method("initWithField")
            .param(ClassB.class)
            .param(field)
            .invoke();
        
        MethodInvoker.create()
        .object(methodCover)
        .method("checkTwoDimensionsArrayCollection")
        .invoke();
    }
    
    @Test(expectedExceptions = {IllegalStateException.class})
    public void checkTwoDimensionsArrayCollectionInvalidCaseTest() throws ExtensionException {
        MethodCover methodCover = new MethodCover();
        Field field = ReflectFieldUtil.getField("a1", ClassB.class);
        field.setAccessible(true);
        MethodInvoker.create()
            .object(methodCover)
            .method("initWithField")
            .param(ClassB.class)
            .param(field)
            .invoke();
    }
    
    @Test
    public void getTypeFromMethodTest() throws ExtensionException {
        MethodCover methodCover = new MethodCover();
        Class<?> clazz = MethodInvoker.create()
            .object(methodCover)
            .method("getTypeFromMethod")
            .param(ReflectMethodUtil.getMethod("hasLove", ClassB.class))
            .invoke(Class.class);
        assertEquals(clazz, Boolean.TYPE);
        
        clazz = MethodInvoker.create()
                .object(methodCover)
                .method("getTypeFromMethod")
                .param(ReflectMethodUtil.getMethod("setHaha", ClassB.class, String.class))
                .invoke(Class.class);
        assertEquals(clazz, String.class);
        
        clazz = MethodInvoker.create()
                .object(methodCover)
                .method("getTypeFromMethod")
                .param(ReflectMethodUtil.getMethod("setHaha", ClassB.class, String.class, String.class))
                .invoke(Class.class);
        assertEquals(clazz, null);
        
    }
    
    @Test(expectedExceptions = {IllegalStateException.class})
    public void testCheckTwoDimensionsArrayCollection3() {
        MethodInvoker.create()
            .object(new MethodCoverEx())
            .method("checkTwoDimensionsArrayCollection")
            .invoke();
    }
    
    public static class MethodCoverEx extends MethodCover {
        public MethodCoverEx() {
            this.isArray = true;
            this.isArrayCollection = true;
            this.method = MethodBuilder.create()
                    .clazz(ClassB.class)
                    .method("getA0")
                    .build();
            
        }
        
        @Override
        public Class<?> getComponentType() {
            return int[][].class;
        }
        
        protected Class<?> getGenericTypeFromMethod(Method method) {
            return int[][].class;
        };
        
        @Override
        public Class<?> getGenericType() {
            return int[][].class;
        }
        
    };
}
