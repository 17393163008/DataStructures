package com.pqq.leetcode;

/**
 * @author 潘勤强
 * @create 2022-01-31 12:38
 */
public class NumberOfVaildWordsInSentence {
    public int countVaildWords(String sentence)
    {
        String[] token = sentence.split(" +");
        int cnt = 0;
        String pattern = "[,.!]^[a-z]+(-[a-z]+)?[,.!]?$";
        for(int i = 0; i < token.length; ++i)
        {
            cnt++;
        }
        return cnt;
    }
}
