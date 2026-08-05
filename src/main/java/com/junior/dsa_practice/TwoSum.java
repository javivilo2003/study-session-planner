package com.junior.dsa_practice;

import java.util.Arrays;

public class TwoSum {
    
    public static int[] twoSum(int[] numbers, int target) {
        for(int i = 0; i < numbers.length; i++){
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    } 

    public static void main(String[] args) {
        int[] numbers = {3, 3};
        int target = 6;

        System.out.println(Arrays.toString(numbers) + ", target " + target + " -> " + Arrays.toString(twoSum(numbers, target)));

    }
}
