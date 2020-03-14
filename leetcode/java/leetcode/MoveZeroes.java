/**
 * @author happyzhao
 * @data 2020/3/14 17:10
 * @type PACKAGE_NAME
 * @question
 */
public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        int idx = 0;// 表示已整理数组的长度，0-idx都已整理好，为正确答案
        for(int i = 0;i < nums.length;i++){
            if(nums[i] != 0){
                nums[idx++] = nums[i];
            }
        }
        for(int i = idx;i < nums.length;i++){
            nums[i] = 0;
        }
    }
}
