package com.pqq.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 潘勤强
 * @create 2022-02-03 12:25
 */
public class FindMinFibonacciNumbers {
    public int findMinFibonacciNumbers(int k) {
        List<Integer> f = new ArrayList<>();
        f.add(1);
        int a = 1, b = 1;
        while(a+b<=k)
        {
            int c = a + b;
            f.add(c);
            a = b;
            b = c;
        }
        int ans = 0;
        for(int i  = f.size()-1; i >= 0 && k > 0; i--)
        {
            int num = f.get(i);
            if(k >= num)
            {
                k-= num;
                ans++;
            }
        }
        return ans;
    }

}
