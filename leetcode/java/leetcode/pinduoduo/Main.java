package pinduoduo;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author happyzhao
 * @data 2020/4/10 19:01
 * @type pinduoduo
 * @question
 */
public class Main {
    long handle(int n, int[] a){
        Arrays.sort(a);
        long ans = 0;
        int flag = 0;
        for(int i = n-1;i >= 0;i--){
            if(flag == 2){
                flag = 0;
            }
            else
            {
                ans += a[i];
                flag++;
            }
        }
        return ans;
    }
    // 区间DP
    int hexie(int n, int m, int[] a){
        long[] f = new long[n+1];
        long sum = 0;
        f[0] = 0;
        for(int i = 1;i <= n;i++){
            sum += a[i-1];
            f[i] = sum;
        }
        int ans = 0;
        for(int len = 1;len <= n;len++){
            for(int i = 1;i <= n-len+1;i++){
                int j = i+len-1;
                long aij = f[j]-f[i-1]; // sum[i,j] 从i加到j
                if(aij % m == 0){
                    ans++;
                    /*System.out.print("len: " + len + " | ");
                    for(int k = i;k <= j;k++){
                        System.out.print(a[k-1]+" ");
                    }
                    System.out.println();*/
                }

            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Main main = new Main();
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt(), N = sc.nextInt();
        System.out.println();

    }
}
