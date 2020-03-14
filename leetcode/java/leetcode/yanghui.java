package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author happyzhao
 * @data 2020/3/14 12:26
 * @type leetcode
 * @question
 */
public class yanghui {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        if(numRows == 0) return ans;
        List<Integer> n1 = new ArrayList<>(1);
        n1.add(1);
        ans.add(n1);
        for(int i = 2;i <= numRows;i++){ // 第i行
            List<Integer> tem = new ArrayList<>(i);
            for(int j = 1;j <= i;j++){
                if(j == 1 || j == i){
                    tem.add(1);
                }
                else {
                    tem.add(ans.get(i-2).get(j-2)+ans.get(i-2).get(j-1));
                }
            }
            ans.add(tem);
           // System.out.println(tem);
        }
        return ans;
    }

    public static void main(String[] args) {
        yanghui yh = new yanghui();
        System.out.println(yh.generate(5));
    }
}
