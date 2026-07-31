package com.junior.dsa_practice;

import java.util.Arrays;
import java.util.Scanner;

public class Anagram {

    public static boolean isAnagram (String word, String word2) {
        if (word.length() != word2.length()) {
            return false;
        }

        int[] check = new int[26];
        int[] check2 = new int[26];

        for(int i = 0; i < word.length(); i++){
            check[word.charAt(i) - 'a']++;
        }

        for(int i = 0; i < word2.length(); i++){
            check2[word2.charAt(i) - 'a']++;
        }

        return Arrays.equals(check, check2);

    }
     
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String word1 = null, word2 = null;

        System.out.print("Enter the first English word in lower case: ");
        word1 = sc.nextLine();

        System.out.print("Enter the second English word in lower case: ");
        word2 = sc.nextLine();


        if (isAnagram(word1, word2)) {
            System.out.println("The words are anagrams");
        } else {
            System.out.println("They are not anagrams.");
        }

        sc.close();
    }
}
