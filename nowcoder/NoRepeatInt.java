import java.util.Scanner;

/**
 * @author happyzhao
 * @data 2020/2/18 22:31
 * @type 华为机试在线训练
 * @question 提取不重复的整数
 */
public class NoRepeatInt {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int ans = 0;
        Boolean[] find = new Boolean[10];
        for(int i = 0;i < 10;i++)
        {
            find[i] = false;
        }
        while(n > 0)
        {
            int t = n%10;
            if(!find[t])
            {
                ans = ans * 10 + t;
                find[t] = true;
            }
            n /=10;
        }
        System.out.println(ans);
    }
}
