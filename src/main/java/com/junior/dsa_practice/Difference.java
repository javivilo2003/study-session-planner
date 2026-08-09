package com.junior.dsa_practice;

import java.util.HashMap;
import java.util.Map;

public class Difference {

    public static Character findTheDifference(String s, String t){
        Map<Character, Integer> map = new HashMap<>();

        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);   
        }

        for (char c : t.toCharArray()) {
            if (!map.containsKey(c)) {
                return c;
            } else {
                map.put(c, map.get(c) - 1);
                if (map.get(c) < 0) {
                    return c;
                }
            }
        }

        throw new IllegalArgumentException("No extra character found.");        
    }

    public static void main(String[] args) {
        String s = "aabb";
        String t = "ababb";

        System.out.println(findTheDifference(s, t));

    }    
}
