package com.junior.dsa_practice;

import java.util.Arrays;

public class SlidingWindow {  
    
    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        int[] profits = new int[prices.length];
        int lowest = prices[0];

        for (int i = 1; i < prices.length; i++) {
            profits[i] = prices[i] - lowest;
            lowest = Math.min(prices[i], lowest);       
        }

        int maxProfit = 0;

        for (int i : profits) {
            maxProfit = Math.max(i, maxProfit);
        }

        System.out.println("Buy at: " + lowest + "\nSell at: " + (maxProfit + lowest) + "\nProfit = " + maxProfit);
    } 


        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int[] merged = new int[nums1.length + nums2.length];
            double median = 0;

            for(int i = 0; i < nums1.length; i++){
                merged[i] = nums1[i];
            }

            for(int i = 0; i < nums2.length; i++){
                merged[nums1.length + i] = nums2[i];
            }

            Arrays.sort(merged);

            if (merged.length % 2 == 0) {
                median = (merged.length + (merged.length + 1)) / 2;
            } else {
                median = merged.length;
            }

            return median;
        }
}
