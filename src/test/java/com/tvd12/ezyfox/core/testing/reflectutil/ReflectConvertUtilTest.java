package com.tvd12.ezyfox.core.testing.reflectutil;

import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.arrayToList;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.byteArrayToCharArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.byteArrayToCharList;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.charArrayToByteArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.charCollectionToPrimitiveByteArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.charWrapperArrayToPrimitiveByteArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToPrimitiveBoolArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToPrimitiveByteArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToPrimitiveCharArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToPrimitiveDoubleArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToPrimitiveFloatArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToPrimitiveIntArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToPrimitiveLongArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToPrimitiveShortArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToStringArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToWrapperBoolArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToWrapperByteArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToWrapperCharArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToWrapperDoubleArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToWrapperFloatArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToWrapperIntArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToWrapperLongArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToWrapperShortArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.primitiveArrayToBoolCollection;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.primitiveArrayToByteCollection;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.primitiveArrayToCharCollection;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.primitiveArrayToDoubleCollection;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.primitiveArrayToFloatCollection;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.primitiveArrayToIntCollection;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.primitiveArrayToLongCollection;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.primitiveArrayToShortCollection;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.stringArrayToCollection;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.stringToChar;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.toBoolWrapperArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.toByteWrapperArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.toCharWrapperArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.toPrimitiveByteArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.toPrimitiveCharArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.wrapperArrayToCollection;
import static org.testng.Assert.assertEquals;

import java.util.Collection;

import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.tvd12.ezyfox.core.reflect.ReflectConvertUtil;
import com.tvd12.test.base.BaseTest;

public class ReflectConvertUtilTest extends BaseTest {

    @Override
    public Class<?> getTestClass() {
        return ReflectConvertUtil.class;
    }
    
    @Test
    public void test() {
        assertEquals(stringToChar(null), (char)0);
        assertEquals(stringToChar(""), (char)0);
        assertEquals(stringToChar("a"), 'a');
        
        assertEquals(byteArrayToCharArray(new byte[] {1, 2, 3}), new char[] {(char)1, (char)2, (char)3});
        assertEquals(charArrayToByteArray(new char[] {(char)1, (char)2, (char)3}), new byte[] {1, 2, 3});
        
        assertEquals(collectionToStringArray(Lists.newArrayList("1", "2", "3")), new String[] {"1", "2", "3"});
        
        assertEquals(collectionToPrimitiveBoolArray(Lists.newArrayList(true, false, true)), new boolean[] {true, false, true});
        assertEquals(collectionToPrimitiveByteArray(Lists.newArrayList((byte)1, (byte)2, (byte)3)), new byte[] {1, 2, 3});
        assertEquals(collectionToPrimitiveCharArray(Lists.newArrayList((char)1, (char)2, (char)3)), new char[] {1, 2, 3});
        assertEquals(collectionToPrimitiveDoubleArray(Lists.newArrayList(1.0, 2.0, 3.0)), new double[] {1.0, 2.0, 3.0});
        assertEquals(collectionToPrimitiveFloatArray(Lists.newArrayList(1.0f, 2.0f, 3.0f)), new float[] {1.0f, 2.0f, 3.0f});
        assertEquals(collectionToPrimitiveIntArray(Lists.newArrayList(1, 2, 3)), new int[] {1, 2, 3});
        assertEquals(collectionToPrimitiveLongArray(Lists.newArrayList(1L, 2L, 3L)), new long[] {1L, 2L, 3L});
        assertEquals(collectionToPrimitiveShortArray(Lists.newArrayList((short)1, (short)2, (short)3)), new short[] {(short)1, (short)2, (short)3});
        
        assertEquals(collectionToWrapperBoolArray(Lists.newArrayList(true, false, true)), new Boolean[] {true, false, true});
        assertEquals(collectionToWrapperByteArray(Lists.newArrayList((byte)1, (byte)2, (byte)3)), new Byte[] {1, 2, 3});
        assertEquals(collectionToWrapperCharArray(Lists.newArrayList((char)1, (char)2, (char)3)), new Character[] {1, 2, 3});
        assertEquals(collectionToWrapperDoubleArray(Lists.newArrayList(1.0, 2.0, 3.0)), new Double[] {1.0, 2.0, 3.0});
        assertEquals(collectionToWrapperFloatArray(Lists.newArrayList(1.0f, 2.0f, 3.0f)), new Float[] {1.0f, 2.0f, 3.0f});
        assertEquals(collectionToWrapperIntArray(Lists.newArrayList(1, 2, 3)), new Integer[] {1, 2, 3});
        assertEquals(collectionToWrapperLongArray(Lists.newArrayList(1L, 2L, 3L)), new Long[] {1L, 2L, 3L});
        assertEquals(collectionToWrapperShortArray(Lists.newArrayList((short)1, (short)2, (short)3)), new Short[] {(short)1, (short)2, (short)3});
        
        assertEquals(arrayToList(new String[] {"1", "2", "3"}).toArray(new String[3]), new String[] {"1", "2", "3"});
        assertEquals(arrayToList(new int[] {1, 2, 3}).toArray(new Integer[3]), new Integer[] {1, 2, 3});
        assertEquals(byteArrayToCharList(new byte[] {(byte)1, (byte)2, (byte)3}).toArray(new Character[3]), new Character[] {(char)1, (char)2, (char)3});
        assertEquals(toBoolWrapperArray(new boolean[] {true, false, true}), new Boolean[] {true, false, true});
        assertEquals(toByteWrapperArray(new byte[] {(byte)1, (byte)2, (byte)3}), new Byte[] {(byte)1, (byte)2, (byte)3});
        assertEquals(toCharWrapperArray(new byte[] {(byte)1, (byte)2, (byte)3}), new Character[] {1, 2, 3});
        assertEquals(toPrimitiveByteArray(new Byte[] {(byte)1, (byte)2, (byte)3}), new byte[] {(byte)1, (byte)2, (byte)3});
        assertEquals(toPrimitiveCharArray(new Character[] {(char)1, (char)2, (char)3}), new char[] {(char)1, (char)2, (char)3});
        assertEquals(charWrapperArrayToPrimitiveByteArray(new Character[] {(char)1, (char)2, (char)3}), new byte[] {(byte)1, (byte)2, (byte)3});
        
        assertEquals(primitiveArrayToBoolCollection(new boolean[] {true, false, true}).toArray(new Boolean[3]), new Boolean[] {true, false, true});
        assertEquals(primitiveArrayToByteCollection(new byte[] {(byte)1, (byte)2, (byte)3}).toArray(new Byte[3]), new Byte[] {(byte)1, (byte)2, (byte)3});
        assertEquals(primitiveArrayToCharCollection(new char[] {(char)1, (char)2, (char)3}).toArray(new Character[3]), new Character[] {(char)1, (char)2, (char)3});
        assertEquals(primitiveArrayToDoubleCollection(new double[] {1.0, 2.0, 3.0}).toArray(new Double[3]), new Double[] {1.0, 2.0, 3.0});
        assertEquals(primitiveArrayToFloatCollection(new float[] {1.0f, 2.0f, 3.0f}).toArray(new Float[3]), new Float[] {1.0f, 2.0f, 3.0f});
        assertEquals(primitiveArrayToIntCollection(new int[] {1, 2, 3}).toArray(new Integer[3]), new Integer[] {1, 2, 3});
        assertEquals(primitiveArrayToLongCollection(new long[] {1L, 2L, 3L}).toArray(new Long[3]), new Long[] {1L, 2L, 3L});
        assertEquals(primitiveArrayToShortCollection(new short[] {(short)1, (short)2, (short)3}).toArray(new Short[3]), new Short[] {(short)1, (short)2, (short)3});
        
        assertEquals(charCollectionToPrimitiveByteArray(Lists.newArrayList((char)1, (char)2, (char)3)), new byte[] {(byte)1, (byte)2, (byte)3});
        assertEquals(stringArrayToCollection(new String[] {"1", "2", "3"}).toArray(new String[3]), new String[] {"1", "2", "3"});
        
        assertEquals(wrapperArrayToCollection(new Integer[] {1, 2, 3}).toArray(new Integer[3]), new Integer[] {1, 2, 3});
        
        Object arr = new Integer[] {1, 2, 3};
        Collection<Integer> list = wrapperArrayToCollection(arr);
        assertEquals(list.toArray(new Integer[3]), arr);
        
    }
    
}
