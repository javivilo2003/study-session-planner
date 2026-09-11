package com.junior.dsa_practice;

public class PalindromeNumber {

    public static boolean isPalindrome(int x){

        if (x < 0 || x > 0 && x % 10 == 0) {
            return false;
        }

        int reversedHalf = 0;

        while(reversedHalf < x){
            reversedHalf = reversedHalf * 10 + x % 10;
            x/=10; 
        }

        return x == reversedHalf || x == reversedHalf / 10;

    }
    
}
