package com.junior.dsa_practice;

public class Palindrome {

    /*
     * Assumptions:
     * - Spaces do not matter.
     * - Punctuation does not matter.
     * - Case does not matter, so 'A' and 'a' are treated as the same character.
     */
    public static boolean isPalindrome(String text) {
        if (text == null) {
            return false;
        }

        int left = 0;
        int right = text.length() - 1;

        while (left < right) {
            char leftChar = text.charAt(left);
            char rightChar = text.charAt(right);

            if (!Character.isLetterOrDigit(leftChar)) {
                left++;
            } else if (!Character.isLetterOrDigit(rightChar)) {
                right--;
            } else if (Character.toLowerCase(leftChar) != Character.toLowerCase(rightChar)) {
                return false;
            } else {
                left++;
                right--;
            }
        }

        return true;
    }
}
