import java.util.Scanner;

/**
 * @author happyzhao
 * @data 2020/2/18 22:45
 * @type 华为机试在线训练
 * @question 数字颠倒
 */
public class NumReverse {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String s = String.valueOf(n);
        for(int i = s.length()-1;i >= 0;i-- )
        {
            System.out.print(s.charAt(i));
        }
        System.out.println();
    }
}
