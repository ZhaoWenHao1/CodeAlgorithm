import java.util.Scanner;

public class jinzhi {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String string = sc.nextLine();

        System.out.println(Integer.parseInt(string.substring(2),16));
        /*long res = 0L,mask = 1L;
        int len = string.length();
        for(int i = 2;i < len;i++)
        {
            int temnum = 0;
            if(string.charAt(i) >= 'a' && string.charAt(i) <= 'f')
            {
                temnum = string.charAt(i) - 'a' + 10;
            }
            else if(string.charAt(i) >= 'A' && string.charAt(i) <= 'F')
            {
                temnum = string.charAt(i) - 'A' + 10;
            }
            else if(string.charAt(i) >= '0' && string.charAt(i) <= '9')

            {
                temnum = string.charAt(i) - '0';
            }
            else
            {
                System.out.println("error" + string.charAt(i));
                break;
            }
            res = res *16 + temnum;
        }
        System.out.println(res);*/


       /* for(int i = 1;i <= len-2;i++)
        {
            int temnum = 0;
            if(string.charAt(len-i) >= 'a' && string.charAt(len-i) <= 'f')
            {
                temnum = string.charAt(len-i) - 'a' + 10;
            }
            else if(string.charAt(len-i) >= 'A' && string.charAt(len-i) <= 'F')
            {
                temnum = string.charAt(len-i) - 'A' + 10;
            }
            else
            {
                temnum = string.charAt(len-i) - '0';
            }
            res += temnum*mask;
            mask *= 16;
        }*/
        //System.out.println(res);
    }
}
