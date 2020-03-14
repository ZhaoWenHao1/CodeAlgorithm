

/**
 * @author happyzhao
 * @data 2020/3/13 20:53
 * @type leetcode
 * @question
 */
public class RomanToInt {
    int get(char c){
        switch (c){
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
        }
        return 0;
    }
    public int romanToInt(String s) {
        int ans = 0;
        for(int i = 0;i < s.length();i++){
            int val = get(s.charAt(i));
            if(i < s.length()-1 && val < get(s.charAt(i+1)))
                val = -val;
            ans += val;
        }
        return ans;
    }
}
