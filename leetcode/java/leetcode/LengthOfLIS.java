package leetcode;

import java.util.Arrays;

/**
 * @author happyzhao
 * @data 2020/3/12 21:51
 * @type PACKAGE_NAME
 * @question 最长上升子序列
 */
public class LengthOfLIS {
    public int lengthOfLIS(int[] nums) {
        if(nums == null) return -1;
        if(nums.length == 1) return 1;
        int[] f = new int[nums.length]; // f[i]表示以nums[i]结尾的最长上升子序列的长度
        Arrays.fill(f,1);
        int ans = 0;
        for(int i = 0;i < nums.length;i++){
            for(int j = 0;j < i;j++){
                if(nums[j] < nums[i]){
                    f[i] = f[i] < f[j]+1 ? f[j]+1 : f[i];
                }
            }
            ans = ans < f[i] ? f[i] : ans;
        }
        return ans;
    }
}
