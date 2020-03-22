/**
 * @author happyzhao
 * @data 2020/3/22 22:36
 * @type PACKAGE_NAME
 * @question 945. 使数组唯一的最小增量
 */
public class MinIncrementForUnique {
    public int minIncrementForUnique(int[] A) {
        int[] count = new int[80000];
        for(int a:A)
            count[a]++;
        int ans = 0, taken = 0;
        for(int i = 0;i < 80000;i++){
            if(count[i] >= 2){
                taken += count[i]-1;
                ans -= i*(count[i]-1);
            }
            else if(count[i] == 0 && taken > 0){
                taken--;
                ans += i;
            }
        }
        return ans;
    }
}
