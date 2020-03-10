package xdft1;

import java.util.Scanner;

/**
 * @author happyzhao
 * @data 2020/3/3 19:39
 * @type
 * @question
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        char[] cs = str.toCharArray();
        for(int i = 0;i < cs.length;i++)
        {
            for(int j = 0;j < cs.length;j++){
                if(j != i)
                {
                    System.out.println(cs[i]+","+cs[j]);
                }
            }
        }
    }
}
