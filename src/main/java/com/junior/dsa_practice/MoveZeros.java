package com.junior.dsa_practice;

import java.util.Arrays;

public class MoveZeros {
    
    public static void main(String[] args) {
        int[] list = {0, 1, 0, 3, 12};
        int insertPosition = 0;

        for (int num : list) {
            if (num != 0) {
                list[insertPosition] = num;
                insertPosition++;
            } 
        }

        for (int i = insertPosition; i < list.length; i++) {
            list[i] = 0;
        }

        System.out.println(Arrays.toString(list));
    }
}
