package alibaba;

import java.util.Scanner;

/**
 * @author happyzhao
 * @data 2020/5/29 9:52
 * @type alibaba
 * @question
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while(T-- > 0){
            int n = sc.nextInt();
            int[] a = new int[n];
            for(int i = 0;i < n;i++){
                a[i] = sc.nextInt();
            }
            int max = Integer.MIN_VALUE;
            for(int i = 0;i < n;i++){
                for(int j = i+1;j < n;j++){
                    for(int k = 0;k < n;k++){
                        if(k != i && k != j){
                            int t = (a[i]+a[j])^a[k];
                            max = Math.max(max, t);
                        }
                    }
                }
            }
            System.out.println(max);
        }
    }
}
