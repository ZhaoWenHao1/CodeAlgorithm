/**
 * @author happyzhao
 * @data 2020/2/27 20:11
 * @type
 * @question 寻找出现次数超过一半的数
 */
public class Solution {
    public int MoreThanHalfNum_Solution(int[] array) {
        int ans = 0, count = 0;
        if (array.length == 0)
            return -1;
        for (int i = 0; i < array.length; i++) {
            if (count == 0) {
                ans = array[i];
                count = 1;
            }
            if (array[i] == ans)
                count++;
            else {
                count--;
            }
        }
        int n = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == ans) {
                n++;
            }
        }
        if (2 * n <= array.length)
            ans = 0;
        return ans;
    }
    static void test(){
        Solution solution = new Solution();
        int[] nums = {2,3,4};
        solution.MoreThanHalfNum_Solution(nums);

    }
}
