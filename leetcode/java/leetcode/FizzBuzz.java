import java.util.ArrayList;
import java.util.List;

/**
 * @author happyzhao
 * @data 2020/3/14 16:40
 * @type PACKAGE_NAME
 * @question
 */
public class FizzBuzz {
    public List<String> fizzBuzz(int n) {
        List<String> ans = new ArrayList<>(n);
        for(int i = 1;i <= n;i++){
            if(i % 3 == 0){
                if(i%5==0){
                    ans.add("FizzBuzz");
                }
                else
                    ans.add("Fizz");
            }
            else if(i % 5 == 0){
                ans.add("Buzz");
            }
            else
                ans.add(String.valueOf(i));
        }
        return ans;
    }
}
