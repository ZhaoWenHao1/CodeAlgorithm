import com.sun.org.apache.xerces.internal.impl.xs.SchemaNamespaceSupport;

import java.util.Scanner;

/**
 * @author happyzhao
 * @data 2020/2/18 22:54
 * @type 华为机试在线训练
 * @question
 */
public class WordsReverse {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] ss = s.split(" ");
        for(int i = ss.length-1;i >= 0;i--)
        {
            System.out.print(ss[i]+" ");
        }
    }
}
