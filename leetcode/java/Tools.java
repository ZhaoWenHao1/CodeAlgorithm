import java.util.Scanner;

public class Tools {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext())
        {
            String s = sc.next();
            for(int i = 0;i < s.length();i++)
                System.out.print(s.charAt(i)+" ");
            System.out.println();
        }
    }
}
