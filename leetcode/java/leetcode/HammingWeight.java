import java.util.Scanner;

/**
 * @author happyzhao
 * @data 2020/3/14 12:50
 * @type PACKAGE_NAME
 * @question 191. 位1的个数
 */
public class HammingWeight {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        System.out.println(Integer.toBinaryString(n));
        int mask = 0x0001;
        int ans = 0;
        if(n < 0) {
            ans++; // 符号位为1，但是转为整数后将消失
        }
        for(int i = 0;i < 31;i++){
            /*System.out.println(mask);
            System.out.println(Integer.toBinaryString(mask));*/
            if((n&mask) > 0){
                ans++;
            }
            mask = mask << 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        HammingWeight ha = new HammingWeight();
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        System.out.println(ha.hammingWeight(Integer.valueOf(s,2)));
    }
}
