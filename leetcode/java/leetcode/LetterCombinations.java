package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author happyzhao
 * @data 2020/3/13 11:11
 * @type leetcode
 * @question 17. 电话号码的字母组合
 */
public class LetterCombinations {
    void dfs(List<String> ans, String digits,int pos,StringBuilder sb,String[] strs){
        if(pos == digits.length())
        {
            ans.add(sb.toString());
            return;
        }
        String tems = strs[digits.charAt(pos)-'0'];
        for(int i = 0;i < tems.length();i++){
            sb.append(tems.charAt(i));
            dfs(ans,digits,pos+1,sb,strs);
            sb.deleteCharAt(sb.length()-1);
        }
    }
    public List<String> letterCombinations(String digits) {
        if(digits.isEmpty()) return null;
        String[] strs = new String[10];
        strs[2] = "abc";
        strs[3] = "def";
        strs[4] = "ghi";
        strs[5] = "jkl";
        strs[6] = "mno";
        strs[7] = "pqrs";
        strs[8] = "tuv";
        strs[9] = "wxyz";
        List<String> ans = new ArrayList<String>();
        if(digits.isEmpty()) {
            ans.clear();
            return ans;
        }
        StringBuilder sb = new StringBuilder();
        dfs(ans,digits,0,sb,strs);
        return ans;
    }

    public static void main(String[] args) {
        LetterCombinations l = new LetterCombinations();
        System.out.println(l.letterCombinations("23"));
    }
}
