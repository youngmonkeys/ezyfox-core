/**
 * 
 */
package com.tvd12.ezyfox.core.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

/**
 * @author tavandung12
 *
 */
public class EzyLists {

	// prevent new instance
    private EzyLists() {
    }
    
    /**
     * Combine some collections to one
     * 
     * @param <T> the value type
     * @param lists the collections to combine 
     * @return a new list
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> combine(Collection<T>... lists) {
        List<T> answer = new ArrayList<>();
        for(Collection<T> list : lists)
            answer.addAll(list);
        return answer;
    }
    
    /**
     * Filter the collection and create a new list
     * 
     * @param <T> the value type
     * @param coll the collection
     * @param filter the filter
     * @return a new list
     */
    public static <T> List<T> newArrayList(Collection<T> coll, ItemFilter<T> filter) {
        List<T> answer = new ArrayList<>();
        for(T t : coll)
            if(filter.filter(t))
                answer.add(t);
        return answer;
    }
    
    /**
     * Remove some item from the collection and create a new list
     * 
     * @param <T> the value type
     * @param coll the collection
     * @param except the unexpected items
     * @return a new list
     */
    public static <T> List<T> newArrayList(Collection<T> coll, Collection<T> except) {
        List<T> answer = new ArrayList<>(coll);
        answer.removeAll(except);
        return answer;
    }
    
    /**
     * Remove some item from the collection and create a new list
     * 
     * @param <T> the value type
     * @param coll the collection
     * @param except the unexpected items
     * @return a new list
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> newArrayList(Collection<T> coll, T... except) {
        return newArrayList(coll, Arrays.asList(except));
    }
    
    /**
     * Transform an array to a new list
     * 
     * @param <I> the input type
     * @param <O> the output type
     * @param input the input
     * @param refactor the refactor
     * @return a new list
     */
    public static <I, O> List<O> newArrayList(I[] input, Refactor<I, O> refactor) {
        return newArrayListByTransform(Lists.newArrayList(input), refactor);
    }
    
    /**
     * Transform a collection to a new list
     * 
     * @param <I> the input type
     * @param <O> the output type
     * @param input the input
     * @param refactor the refactor
     * @return a new list
     */
    public static <I, O> List<O> newArrayListByTransform(
            Collection<I> input, Refactor<I, O> refactor) {
        List<O> answer = new ArrayList<>();
        for(I value : input)
            answer.add(refactor.refact(value));
        return answer;
    }
    
    /**
     * 
     * Transform a map to new list
     * 
     * @param <K> the key type
     * @param <V> the value type
     * @param <O> the output type
     * @param input the input
     * @param refactor the refactor
     * @return the new list
     */
    public static <K, V, O> List<O> newArrayListByTransform(
            Map<K, V> input, MapRefactor<K, V, O> refactor) {
        List<O> answer = new ArrayList<>();
        for(K key : input.keySet())
            answer.add(refactor.refact(key, input.get(key)));
        return answer;
    }
    
    /**
     * Combine a collection and an array of elements to a new list
     * 
     * @param <T> the value type
     * @param coll the collection
     * @param elements the array of elements
     * @return the new list
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> addElementsToNewList(Collection<T> coll, T... elements) {
        List<T> answer = new ArrayList<>(coll);
        answer.addAll(Arrays.asList(elements));
        return answer;
    }
    
    /**
     * Filter the collection and count item
     * 
     * @param <T> the value type
     * @param coll the collection
     * @param filter the filter
     * @return the item count
     */
    public static <T> int getItemCount(Collection<T> coll, ItemFilter<T> filter) {
        int answer = 0;
        for(T t : coll) 
            if(filter.filter(t)) answer ++;
        return answer;
    }
    
    /**
     * Loop the collection and calculate sum of value related to item in the collection
     * 
     * @param <T> the value type
     * @param coll the collection
     * @param measurer the measurer
     * @return the sum value
     */
    public static <T> int measureItemCount(Collection<T> coll, ItemMeasurer<T> measurer) {
        int answer = 0;
        for(T t : coll) 
            answer += measurer.measure(t);
        return answer;
    }
    
    /**
     * Filter the collection and get an item
     * 
     * @param <T> the value type
     * @param coll the collection
     * @param filter the filter
     * @return an item
     */
    public static <T> T getItem(Collection<T> coll, ItemFilter<T> filter) {
        for(T t : coll) 
            if(filter.filter(t)) return t;
        return null;
    }
    
}
