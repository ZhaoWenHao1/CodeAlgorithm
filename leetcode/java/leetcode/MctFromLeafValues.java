package leetcode;

import java.util.Stack;

import static leetcode.RMQ.monotonicStack;

/**
 * @author happyzhao
 * @data 2020/11/12 21:56
 * @type leetcode
 * @question  1130. 叶值的最小代价生成树
 *  区间DP + RMQ
 */
public class MctFromLeafValues {


    /**
     * 区间dp  O(n^3)
     * @param arr
     * @return
     */
    public int mctFromLeafValues(int[] arr) {
        int n = arr.length;
        int[][] cost = new int[n][n];
        RMQ.monotonicStack(arr);
        for(int len = 2;len <= n;len++){
            for(int i = 0;i+len-1 < n;i++){
                // 对于cost[i][i+len-1]找最小
                int minn = Integer.MAX_VALUE;
                for(int j = i;j < i+len-1;j++){
                    int t = cost[i][j] + cost[j+1][i+len-1] + RMQ.f[i][j] * RMQ.f[j+1][i+len-1];
                    minn = Math.min(t, minn);
                }
                cost[i][i+len-1] = minn;
            }

        }
        return cost[0][n-1];
    }

    /**
     * 单调栈 O(n)
     * @param arr
     * @return
     */
    public int mctFromLeafValuesStack(int[] arr){
        int res = 0;
        // 单调递减栈
        Stack<Integer> stack = new Stack<>();
        int len = arr.length;
        stack.push(Integer.MAX_VALUE);
        int[] nums = new int[arr.length+1];
        for(int i = 0;i < arr.length;i++){
            nums[i] = arr[i];
        }
        nums[arr.length] = Integer.MAX_VALUE;
        for(int i = 0;i < nums.length;i++){
            if(stack.size() == 1 || stack.peek() > nums[i]){
                stack.push(nums[i]);
            }
            else{

                int t = stack.pop();
                if(i == nums.length - 1 && stack.size() == 1){
                    break;
                }
                res += Math.min(nums[i], stack.peek()) * t;
                i--;
            }
        }
        return res;

    }

    public static void main(String[] args) {
        MctFromLeafValues mctFromLeafValues = new MctFromLeafValues();
        int[] arr = {6,2,4};
        System.out.println(mctFromLeafValues.mctFromLeafValuesStack(arr));
    }
}
