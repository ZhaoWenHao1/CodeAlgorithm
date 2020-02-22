import java.util.Scanner;
public class StringSplit{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        for(int i = 0;i < 2;i++)
        {
            String s = sc.next();
            if(s.isEmpty())
                continue;
            int len = s.length();
            if(len == 8)
                System.out.println(s);
            int j = 8;
            while(j < len)
            {
                System.out.println(s.substring(j-8,j));
                j+=8;
            }
            if(j != len)
            {
                System.out.print(s.substring(j-8,len));
                for(int k = len;k < j;k++ )
                {
                    System.out.print("0");
                }
                System.out.println();
            }


        }
    }
}