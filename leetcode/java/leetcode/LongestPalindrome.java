package leetcode;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author happyzhao
 * @data 2020/3/12 23:18
 * @type PACKAGE_NAME
 * @question 最长回文子串
 */
public class LongestPalindrome {
    public String longestPalindrome(String s) {
        if(s.length() < 2) return  s;
        int[] f = new int[s.length()];
        Arrays.fill(f,1);
        int mid = s.length()/2;
        int max = 0;
        String ans = null;
        // 按照奇数个回文队列
        for(int i = 0;i < s.length();i++){
            int len = 0;
            int offset = 1;
            for(;offset+i < s.length() && i-offset >= 0;offset++){
                if(s.charAt(i+offset) == s.charAt(i-offset))
                {
                    len++;
                }
                else
                {
                    break;
                }
            }
            if(offset+i >= s.length() || i-offset < 0 || s.charAt(i+offset) != s.charAt(i-offset))
                offset--;
            if(max < len*2+1)
            {
                max = len*2+1;
                ans = s.substring(i-offset,i+offset+1);
            }
        }
        for(int i = 0;i < s.length();i++){
            int len = 0;
            int offset = 0;
            for(;offset+i < s.length() && i-offset-1 >= 0;offset++){
                if(s.charAt(i+offset) == s.charAt(i-offset-1))
                {
                    len++;
                }
                else
                {
                    break;
                }
            }
            if(offset+i >= s.length() || i-offset-1 < 0 || s.charAt(i+offset) != s.charAt(i-offset-1))
                offset--;
            if(max < len*2)
            {
                max = len*2;
                ans = s.substring(i-offset-1,i+offset+1);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        LongestPalindrome l = new LongestPalindrome();
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        System.out.println(l.longestPalindrome(s));
    }
}
