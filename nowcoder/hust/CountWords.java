package hust;

import java.util.Scanner;

/**
 * @author happyzhao
 * @data 2020/3/11 17:05
 * @type
 * @question
 */
public class CountWords {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String string = sc.nextLine();
        int count = 0;
        for(int i = 0;i < string.length();i++){
            if(string.charAt(i) == ' ')
            {
                System.out.print(count+" ");
                count = 0;
            }
            else if(string.charAt(i) == '.')
            {
                System.out.print(count+" ");
                break;
            }
            else {
                count++;
            }
        }
    }
}
