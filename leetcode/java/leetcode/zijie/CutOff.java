package zijie;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author happyzhao
 * @data 2020/4/12 19:58
 * @type zijie
 * @question
 */
public class CutOff {
    // 查找小于等于x的最大值
    static int binary(int[] nums, int x){
        int low = 0, high = nums.length-1;
        if(x < nums[0])
            return 0;
        if(x > nums[nums.length-1])
            return nums[nums.length-1];
        while(low < high-1){
            int mid = (low+high)/2;
            if(nums[mid] < x){
                low = mid;
            }
            else if(nums[mid] > x){
                high = mid;
            }
            else{
                return nums[mid];
            }
        }
        if(nums[high] <= x)
            return nums[high];
        else
            return nums[low];
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(),m = sc.nextInt();
        int[] a = new int[n],b = new int[m];
        for(int i = 0;i < n;i++){
            a[i] = sc.nextInt();
        }
        for(int i = 0;i < m;i++){
            b[i] = sc.nextInt();
        }
        Arrays.sort(a);
        long cost = 0;
        for(int i = 0;i < m;i++){
            int cut = binary(a,b[i]);
            //System.out.println(cut);
            int del = b[i] - cut;
            if(del < 0){
                System.out.println("Error");
                return;
            }
            cost += del;
        }
        System.out.println(cost);
    }
}
