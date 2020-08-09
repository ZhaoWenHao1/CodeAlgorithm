package meituan3;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author happyzhao
 * @data 2020/3/19 20:23
 * @type meituan3
 * @question
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n,k,m,p,q;
        n = sc.nextInt();
        k = sc.nextInt();
        m = sc.nextInt();
        p = sc.nextInt();
        q = sc.nextInt();
        int[] t =new int[k];
        int sum = 0;
        for(int i = 0;i < k;i++){
            t[i] = sc.nextInt();
            sum += t[i];
        }
        int ans = 0;
        Arrays.sort(t);
        int subtask = 0, task = 0;
        int last = m;
        int nn = 0, kk = 0;
        int[][] f = new int[nn][kk];
        task = m/sum;
        last = m%sum;
        for(int i = 0;i < k;i++){
            if(last >= t[i]){
                subtask++;
                last -= t[i];
            }
            else
                break;
        }
        ans = p*(k*task + subtask) + q*task;


        last = m;
        int i = 0;
        subtask = 0;
        task = 0;
        int tem = 0;
        for(i = 0;i < k;i++){
            if(t[i]*k < last){
                subtask = subtask + k;
                last = last - t[i]*k;
            }
            else
                break;
        }
        if(subtask == k*n){ // 所有任务做完
            ans = subtask*p + q*n;
        }
        else{
            if(i == k-1){ // 最后一层且做不完
                task = last/t[i];
                tem = task*q + (subtask+task)*p;
            }
            else {
                subtask = last/t[i] + subtask;
                tem = subtask*p;
            }
        }
        ans = Math.max(ans,tem);
        System.out.println(ans);
    }
}
