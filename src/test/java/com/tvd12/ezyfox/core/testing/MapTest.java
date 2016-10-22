/**
 * 
 */
package com.tvd12.ezyfox.core.testing;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tavandung12
 *
 */
public class MapTest {

    public static void main(String[] args) {
        Map<Class<?>, Runnable> map = new HashMap<>();
        map.put(Map.class, new Runnable() {
            
            @Override
            public void run() {
                System.out.println("hello");
            }
        });
        Map<String, String> m = new HashMap<>();
        System.out.println(map.get(m.getClass()));
        System.out.println(Map.class.isAssignableFrom(m.getClass()));
    }
    
}
