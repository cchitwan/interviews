package com.ms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Solution {


    /**
     * For a given amount of money, maximize total no of copies collected from different store.
     * There are multiple stores and they have unlimited bundles.
     *
     int costPerBundle [] = {20,19};
     int noOfCopyPerBundle[] = {24, 20};
     int x = budgetShopping(50, costPerBundle, noOfCopyPerBundle);
     */
    static int budgetShopping(int n, int[] bundleQuantities, int[] bundleCosts) {
        Map map = new HashMap(bundleQuantities.length);

        for(int i =0 ; i<bundleCosts.length ;i++){
            map.put(bundleCosts[i], bundleQuantities[i]);
        }

        Map<Integer, Integer> sortedMap = new TreeMap<Integer, Integer>(map);
        Arrays.sort(bundleCosts);
        int memo[][] = new int[bundleCosts.length][n+1];
        for(int i=0; i<bundleCosts.length;i++){
            for(int j =0; j<(n+1);j++){
                if(i==0){
                    memo[i][j]= (j/bundleCosts[i])*sortedMap.get(bundleCosts[i]);
                }else{
                    if(j<bundleCosts[i]){
                        memo[i][j] = memo[i-1][j];
                    }else{
                        int count = j/bundleCosts[i];
                        memo[i][j] = Math.max(count*sortedMap.get(bundleCosts[i])+ memo[i-1][j-count*bundleCosts[i]], memo[i-1][j]);
                    }
                }
            }
        }
        return  memo[bundleCosts.length-1][n];
    }


}
