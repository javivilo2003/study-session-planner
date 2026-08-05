package com.junior.dsa_practice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    
    public static int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> seen = new HashMap<>();

        for(int i = 0; i < numbers.length; i++){
            int need = target - numbers[i];
            
            if (seen.containsKey(need)) {
                return new int[] {seen.get(need), i};
            }

            seen.put(numbers[i], i);
        }
        return new int[]{};
    } 

    public static void main(String[] args) {
        int[] numbers = {3, 3};
        int target = 6;

        System.out.println(Arrays.toString(numbers) + ", target " + target + " -> " + Arrays.toString(twoSum(numbers, target)));

    }
}
