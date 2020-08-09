package meituan.meituan2;

import java.util.Scanner;
import java.util.Vector;

/**
 * @author happyzhao
 * @data 2020/3/19 19:27
 * @type meituan.meituan2
 * @question
 */
public class Main {
    public static int handle(int[] nums, int[] f){
        for(int i = 0;i < f.length;i++)
        {
            f[i] = 1;
        }
        int maxn = 0;
        int ans = 0;
        for(int i = 0;i < nums.length;i++){
            maxn = 0;
            for(int j = 0;j < i;j++){
                if(nums[i] > nums[j]){
                    maxn = Math.max(maxn,f[j]+1);
                }
            }
            ans = Math.max(ans,maxn);
            f[i] = maxn;
        }
        return ans;
    }
    static int myhandle(int[] nums,int maxx){
        int ans = 1;
        boolean[] f = new boolean[maxx+1];
        int con = 0;
        for(int i = 0;i < maxx+1;i++){
            f[i] = false;
        }
        for(int i = 1;i < nums.length;i++){
            con = 1;
            for(int j = i;j < nums.length;j++){
                if(nums[j] > nums[j-1])
                    con++;
                else
                    break;
            }
            ans = Math.max(ans,con);
        }
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        int maxx = 0;
        for(int i = 0;i < n;i++){
            nums[i] = sc.nextInt();
            maxx = Math.max(maxx,nums[i]);
        }
        int ans = 0;
        int[] tem = new int[n-1];
        for(int i = 0;i < n;i++){
            int mx = 0;
            for(int j = 0,k = 0;j < n-1 && k < n;k++){
                if(k != i){
                    tem[j++] = nums[k];
                    mx = Math.max(mx,nums[k]);
                }
            }
            int tn = Main.myhandle(tem,mx);
            ans = Math.max(ans,tn);
        }
        System.out.println(ans);
    }
}
