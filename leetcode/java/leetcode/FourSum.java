import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author happyzhao
 * @data 2020/4/12 14:17
 * @type PACKAGE_NAME
 * @question 四数之和
 */
public class FourSum {
    public List<List<Integer>> threeSum(int[] nums, int target) {
        List<List<Integer>> ret = new ArrayList<>();
        if(nums == null || nums.length < 2)
            return ret;
        Arrays.sort(nums);
        for(int i = 0;i < nums.length - 2;i++){
            // if(nums[i] > target) return ret;
            if(i > 0 && nums[i] == nums[i-1]) continue;
            int L = i+1, R = nums.length-1;
            while(L < R){
                if(nums[i]+nums[L]+nums[R] == target){
                    List<Integer> tmp = new ArrayList<>(3);
                    tmp.add(nums[i]);
                    tmp.add(nums[L]);
                    tmp.add(nums[R]);
                    ret.add(tmp);
                    while(L < R && nums[L] == nums[++L]);
                    while(L < R && nums[R] == nums[--R]);
                }
                else if(nums[i]+nums[L]+nums[R] < target){
                    L++;
                }
                else{
                    R--;
                }
            }
        }
        return ret;
    }
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if(nums == null || nums.length < 4) return ans;
        Arrays.sort(nums);

        for(int n:nums){
            System.out.print(n+" ");
        }
        System.out.println();

        for(int i = 0;i < nums.length-3;i++){
            // if(nums[i] > target) return ans;
            if(i > 0 && nums[i] == nums[i-1]) continue;
            int tar = target - nums[i];
            int[] tnums = Arrays.copyOfRange(nums,i+1,nums.length);
            List<List<Integer>> tret = threeSum(tnums,tar);
            for(int j = 0;j < tret.size();j++){
                List<Integer> tt = new ArrayList<>(4);
                tt.add(nums[i]);
                tt.addAll(tret.get(j));
                ans.add(tt);
            }
        }
        return ans;

    }

    public static void main(String[] args) {
        int[] nums = {1,-2,-5,-4,-3,3,3,5};
        int target = -11;
        FourSum fourSum = new FourSum();
        System.out.println(fourSum.fourSum(nums,target));

    }
}
