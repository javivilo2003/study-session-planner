package com.junior.dsa_practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KFrequentTimes {
    public int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k];

        Map<Integer, Integer> searchFrequent = new HashMap<>();

        for(int i = 0; i < nums.length; i++){
            searchFrequent.put(
                nums[i],
                searchFrequent.getOrDefault(nums[i], 0) + 1
            );
        }

        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(searchFrequent.entrySet());

        entries.sort(
            Map.Entry.<Integer, Integer>comparingByValue().reversed()
        );


        for(int i = 0; i < k; i++){
            result[i] = entries.get(i).getKey();
        }

        return result;
    }
}
