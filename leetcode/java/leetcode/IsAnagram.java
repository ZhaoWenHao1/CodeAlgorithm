/**
 * @author happyzhao
 * @data 2020/3/14 18:02
 * @type PACKAGE_NAME
 * @question
 */
public class IsAnagram {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;
        int[] nums = new int[26];
        for(int i = 0;i < 26;i++) nums[i] = 0;
        for(int i = 0;i < s.length();i++){
            int n = s.charAt(i)-'a';
            nums[n]++;
            n = t.charAt(i)-'a';
            nums[n]--;
        }
        for(int i = 0;i < 26;i++){
            if(nums[i] != 0)
                return false;
        }
        return true;

    }
}
