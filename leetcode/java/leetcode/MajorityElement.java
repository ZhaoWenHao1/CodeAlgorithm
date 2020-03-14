/**
 * @author happyzhao
 * @data 2020/3/14 16:46
 * @type PACKAGE_NAME
 * @question
 */
public class MajorityElement {
    public int majorityElement(int[] nums) {
        int ans = 0, count = 0;
        int len = nums.length;
        if(len == 1) return nums[0];
        ans = nums[0];
        count++;
        for(int i = 1;i < len;i++){
            if(nums[i] != ans){
                count--;
            }
            else{
                count++;
            }
            if(count == 0){
                ans = nums[i];
                count++;
            }
        }
        int sum = 0;
        for(int i = 0;i < len;i++){
            if(nums[i] == ans){
                sum++;
            }
        }
        if(sum * 2 > len)
            return ans;
        else
            return  -1;
    }
}
