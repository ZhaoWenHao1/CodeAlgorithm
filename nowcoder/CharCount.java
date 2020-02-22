import java.util.Scanner;

/**
 * @author happyzhao
 * @data 2020/2/18 22:38
 * @type 华为机试在线训练
 * @question 字符个数统计
 */
public class CharCount {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int[] flag = new int[128];
        for(int i = 0;i < 128;i++)
        {
            flag[i]=0;
        }
        int ans = 0;
        for(int i = 0;i < s.length();i++){
            char c = s.charAt(i);
            if(c <= 127)
            {
                if(flag[c] == 0)
                {
                    ans++;
                    flag[c]=1;
                }
            }
        }
        System.out.println(ans);
    }
}
