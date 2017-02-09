/**
 * 
 */
package com.tvd12.ezyfox.core.util;

import java.lang.reflect.Array;
import java.util.Collection;

/**
 * @author tavandung12
 *
 */
public class EzyArrays {

	// prevent new instance
    private EzyArrays() {
    }
    
    /**
     * Transform the collection to an array
     * 
     * @param <I> the input type
     * @param <O> the output type
     * @param coll the collection to transform
     * @param outType the output type clazz
     * @param refactor the refactor
     * @return an array
     */
    @SuppressWarnings("unchecked")
    public static <I, O> O[] newArray(
            Collection<I> coll, Class<O> outType, Refactor<I, O> refactor) {
        int count = 0;
        int size = coll.size();
        Object answer = Array.newInstance(outType, size);
        for(I input : coll)
            Array.set(answer, count ++, refactor.refact(input));
        return (O[])answer;
    }
}
