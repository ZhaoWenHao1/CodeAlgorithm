package pinduoduo;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author happyzhao
 * @data 2020/4/10 19:41
 * @type pinduoduo
 * @question
 */
public class LuckyNum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), k = sc.nextInt();
        int[] a = new int[n],b = new int[n];
        String s = sc.next();
        for(int i = 0;i < n;i++){
            a[i] = s.charAt(i)-'0';
            b[i] = a[i];
        }
        Arrays.sort(b);
        int minn = Integer.MAX_VALUE, flag = -1;
        for(int i = 0;i < 10;i++){
            int[] t = new int[n];
            for(int j = 0;j < n;j++){
                t[j] = Math.abs(b[j]-i);
            }
            Arrays.sort(t);
            int loss = 0;
            for(int j = 0;j < k;j++){
                loss += t[j];
            }
            if(minn > loss){
                minn = loss;
                flag = i;
            }
        }
        /*System.out.println("minloss: " + minn);
        System.out.println("flag: " + flag);*/
        int[] t = new int[n];
        for(int j = 0;j < n;j++){
            t[j] = Math.abs(b[j]-flag);
        }
        Arrays.sort(t);
        int sum = 0, last = t[0];
        sum++;
        for(int i = 1;i <= k;i++){
           // System.out.println("sum:"+sum);
            if(i < k && t[i] == last)
                sum++;
            else{
                // 找到sum个与flag绝对值为last的
                if(last == 0){

                }
                else{
                    /*if(i == k)
                    {
                        System.out.println(" K ~~~"+ sum + " " + last);
                    }*/
                    int cunt = 0;
                    //先从左往右减
                    for(int j = 0;j < n && cunt < sum;j++){
                        if(a[j]-flag == last){
                            a[j] = flag;
                            //System.out.println("mod: "+ a[j]);
                            cunt++;
                        }
                    }
                    // 再从右往左加
                    for(int j = n-1;j >= 0 && cunt < sum;j--){
                        if(flag - a[j] == last){
                            a[j] = flag;
                            cunt++;
                        }
                    }
                }
                if(i < n){
                    last = t[i];
                    sum = 1;
                }

            }
        }
        System.out.println(minn);
        for(int i = 0;i < n;i++){
            System.out.print(a[i]);
        }

    }
}
