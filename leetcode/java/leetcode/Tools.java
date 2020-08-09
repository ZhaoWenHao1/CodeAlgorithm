package leetcode;
import java.util.Scanner;

/**
 * @author happyzhao
 * @data 2020/4/2 21:10
 * @type PACKAGE_NAME
 * @question
 */
public class Tools {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s ;
        while(sc.hasNext()){
            s = sc.nextLine();
            int n = 0;
            for(int i = 0;i < s.length();i++){
                if(s.charAt(i) == ' '){
                    n++;
                    System.out.print(" ");
                }
                else
                    System.out.print(s.charAt(i));
            }
            System.out.println(n+1);
        }

    }
}
