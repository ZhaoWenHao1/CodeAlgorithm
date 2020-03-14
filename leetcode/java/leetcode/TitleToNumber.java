package leetcode;

/**
 * @author happyzhao
 * @data 2020/3/14 12:19
 * @type leetcode
 * @question
 */
public class TitleToNumber {
    public int titleToNumber(String s) {
        int ans = 0;
        for(int i = 0;i < s.length();i++){
            int n = (int)s.charAt(i)-'A'+1;
            ans = ans*26+n;
        }
        return ans;
    }
}
