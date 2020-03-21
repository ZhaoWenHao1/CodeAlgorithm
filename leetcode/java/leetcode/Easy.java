/**
 * @author happyzhao
 * @data 2020/3/17 12:03
 * @type PACKAGE_NAME
 * @question
 */
public class Easy {
    public int countCharacters(String[] words, String chars) {
        int ans = 0;
        int[] ch = new int[26];
        for(int i = 0;i < chars.length();i++){
            int n = chars.charAt(i)-'a';
            ch[n]++;
        }
        for(int i = 0;i < words.length;i++){
            int[] nums = new int[26];
            for(int k = 0;k < 26;k++){
                nums[k] = ch[k];
            }
            String word = words[i];
            int find = 1;
            for(int j = 0;j < word.length();j++){
                int n = word.charAt(j)-'a';
                if(nums[n] > 0)
                    nums[n]--;
                else{
                    find = 0;
                    break;
                }
            }
            if(find == 1){
                ans += word.length();
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Easy easy = new Easy();
        String[] strs = {"cat","bt","hat","tree"};
        String chars = "atach";
        System.out.println(easy.countCharacters(strs,chars));
    }
}
