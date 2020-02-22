import java.util.Scanner;

/**
 * @author happyzhao
 * @data 2020/2/18 22:51
 * @type 华为机试在线训练
 * @question 字符串翻转
 */
public class StringReverse {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        for(int i = s.length()-1;i >= 0;i--)
        {
            System.out.print(s.charAt(i));
        }
        System.out.println();
    }
}
