package leetcode;

import java.util.Scanner;

/**
 * @author happyzhao
 * @data 2020/3/13 22:56
 * @type leetcode
 * @question 69.x 的平方根
 */
public class MySqrt {
    public int mySqrt(int x) {
        if(x==0) return 0;
        int ans = 1;
        while(ans*ans <= x && ans*ans > 0)
        {
            ans = ans << 1;
        }
        ans = ans >> 1;
        while(ans*ans <= x && ans*ans > 0)
        {
            ans++;
        }
        ans--;
        return ans;
    }
    public static void main(String[] args) {
        MySqrt mq = new MySqrt();
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        System.out.println(Integer.MAX_VALUE);
        System.out.println(mq.mySqrt(x));
        System.out.println((int)Math.sqrt(x));
    }
}
