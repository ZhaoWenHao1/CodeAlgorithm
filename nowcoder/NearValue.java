import java.util.Scanner;

/**
 * @author happyzhao
 * @data 2020/2/18 17:33
 * @type 华为机试在线训练
 * @question 取近似值
 */
public class NearValue {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double n = sc.nextDouble();
        System.out.println((int)(n+0.5));
    }
}
