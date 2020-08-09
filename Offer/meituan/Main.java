package meituan;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author happyzhao
 * @data 2020/3/19 19:04
 * @type meituan
 * @question
 */
public class Main {
    long handle(int[] nums){
        if(nums.length < 3)
            return 0;
        long ans = 0;
        int[] arr = new int[3];
        arr[0] = nums[0];
        arr[1] = nums[1];
        arr[2] = nums[2];
        Arrays.sort(arr);
        for(int i = 3;i < nums.length;i++){
            if(nums[i] > arr[0]){
                arr[0] = nums[i];
                for(int j = 0;j < 2;j++){
                    if(arr[j] > arr[j+1]){
                        int t = arr[j];
                        arr[j] = arr[j+1];
                        arr[j+1] = t;
                    }
                }
            }
        }
        for(int i = 0;i < 3;i++)
            ans += arr[i];
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] po1 = new int[n], po2 = new int[n];
        for(int i = 0;i < n;i++){
            po1[i] = sc.nextInt();
        }
        for(int i = 0;i < n;i++){
            po2[i] = sc.nextInt();
        }

        if(n < 3)
            return ;
        Main ma = new Main();
        long ans = Math.max(ma.handle(po1),ma.handle(po2));
        System.out.println(ans);
    }
}
