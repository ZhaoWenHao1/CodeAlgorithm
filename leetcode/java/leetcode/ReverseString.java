package leetcode;

/**
 * @author happyzhao
 * @data 2020/3/14 12:03
 * @type leetcode
 * @question 344. 反转字符串
 */
public class ReverseString {
    public void reverseString(char[] s) {
        int len = s.length;
        char t;
        for(int i = 0;i < len/2;i++){
            t = s[i];
            s[i] = s[len-1-i];
            s[len-1-i] = t;
        }
    }
}
