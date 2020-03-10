/**
 * @author happyzhao
 * @data 2020/3/6 16:29
 * @type
 * @question
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        StringBuilder sb = new StringBuilder();
        if(strs.length == 0) return "";
        if(strs.length == 1) return strs[0];
        int len = strs[0].length();
        int flag = 0;
        for(int i = 0;i < len;i++){
            char c = strs[0].charAt(i);
            for(int j = 1;j < strs.length;j++ ){
                if(strs[j].length()-1 < i || strs[j].charAt(i) != c){
                    flag = 1;
                    break;
                }
            }
            if(flag == 1){
                break;
            }
            else
            {
                sb.append(c);
            }
        }
        return sb.toString();

    }

    public static void main(String[] args) {
        String[] strs = {"flower","flow","flight"};
        LongestCommonPrefix lo = new LongestCommonPrefix();
        System.out.println(lo.longestCommonPrefix(strs));
    }
}
