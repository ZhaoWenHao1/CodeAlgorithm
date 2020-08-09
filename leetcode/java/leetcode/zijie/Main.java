package zijie;

import java.util.Scanner;

/**
 * @author happyzhao
 * @data 2020/4/12 19:00
 * @type zijie
 * @question
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for(int i = 0;i < t;i++){
            int n = sc.nextInt();
            int[] a = new int[n], b = new int[n], del = new int[n];
            int dx = 0,flag = 0;
            for(int j = 0;j < n;j++){
                a[j] = sc.nextInt();
            }
            for(int j = 0;j < n;j++) {
                b[j] = sc.nextInt();
            }
            for(int j = 0;j < n;j++){
                del[j] = a[j]-b[j];
                if(del[j] > 0){
                    flag = 2;
                    break;
                }
                if(del[j] != dx && dx != 0 && del[j] != 0){
                    flag = 2;
                    break;
                }
                if(del[j] != dx){
                    if(dx == 0)
                        flag++;
                    dx = del[j];
                }
                if(flag >= 2){
                    break;
                }
            }
            if(flag >= 2){
                System.out.println("NO");
            }
            else {
                System.out.println("YES");
            }
        }
    }
}
