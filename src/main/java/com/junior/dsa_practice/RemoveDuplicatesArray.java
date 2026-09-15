package com.junior.dsa_practice;

import java.util.LinkedHashSet;


public class RemoveDuplicatesArray {
    public static int removeDuplicates(int[] nums){
        int k = 0;
        LinkedHashSet<Integer> list = new LinkedHashSet<>();

        for(int i : nums){
            list.add(i);
        }

       int i = 0;

       for (int num : list) {
            nums[i] = num;
            i++; 
       }

        return nums.length;
    }    

    public static void main(String[] args) {
        int[] case1 = {1, 1, 2};
        int[] case2 = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};

        System.out.println(removeDuplicates(case1));
        System.out.println(removeDuplicates(case2));


    }
}
