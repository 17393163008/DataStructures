package com.pqq.leetcode;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * @author 潘勤强
 * @create 2022-01-23 10:43
 */
public class StockPrice {
    int maxTimestamp;
    HashMap<Integer,Integer> timePriceMap;
    TreeMap<Integer,Integer> prices;

    public StockPrice()
    {
        maxTimestamp = 0;
        timePriceMap = new HashMap<Integer, Integer>();
        prices = new TreeMap<Integer, Integer>();
    }

    public void update(int timestamp, int price)
    {
        maxTimestamp = Math.max(maxTimestamp,timestamp);
        int prePrice = timePriceMap.getOrDefault(timestamp,0);
        timePriceMap.put(timestamp,price);
        if(prePrice > 0)
        {
            prices.put(prePrice,prices.get(prePrice)-1);
            if(prices.get(prePrice) == 0)
            {
                prices.remove(prePrice);
            }
            prices.put(price,prices.getOrDefault(price,0)+1);
        }
    }

    public int current()
    {
        return timePriceMap.get(maxTimestamp);
    }

    public int maxinum()
    {
        return prices.lastKey();
    }

    public int mininum()
    {
        return prices.firstKey();
    }

}
