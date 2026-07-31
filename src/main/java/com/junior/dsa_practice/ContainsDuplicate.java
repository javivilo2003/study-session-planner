package com.junior.dsa_practice;

import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {

    public static boolean containsDuplicate(Integer[] array) {
    Set<Integer> appearances = new HashSet<Integer>();

        for (Integer i : array) {
            if (!appearances.contains(i)) {
                appearances.add(i);
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Integer[] array1 = {1, 2, 3, 1};
        
        if (containsDuplicate(array1)) {
            System.out.println("It contains a duplicate");
        } else {
            System.out.println("It doesn't contain a duplicate.");
        }
        
    }
}
