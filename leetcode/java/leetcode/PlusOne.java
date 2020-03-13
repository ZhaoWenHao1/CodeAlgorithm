package leetcode;

/**
 * @author happyzhao
 * @data 2020/3/13 22:48
 * @type leetcode
 * @question 加一
 */
public class PlusOne {
    public int[] plusOne(int[] digits) {
        int carry = 1,tem;
        int[] ans = digits;
        for(int i = digits.length-1;i >= 0;i--){
            tem = digits[i]+carry;
            digits[i] = tem%10;
            carry = tem/10;
        }
        if(carry > 0){
            ans = new int[digits.length+1];
            ans[0] = carry;
            for(int i = 0;i < digits.length;i++){
                ans[i+1] = digits[i];
            }
        }
        return ans;
    }
}
