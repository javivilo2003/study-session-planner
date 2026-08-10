package com.junior.dsa_practice;

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
}
