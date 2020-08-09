package zijie;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author happyzhao
 * @data 2020/4/12 20:27
 * @type zijie
 * @question
 */
public class Stand {
    static void dandiao(int[] a,int[] f,int n){
        Stack<Integer> stk = new Stack<>();
        for(int j = 0;j <= n;j++){
            if(stk.isEmpty() || a[j] <= stk.peek())
                stk.push(j);
            else {
                int top = stk.peek();
                while(!stk.isEmpty() && a[stk.peek()] < a[j]){
                    int t = stk.pop();
                    f[t] += top-t;
                }
                stk.push(j);
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for(int i = 0;i < t;i++){
            int n = sc.nextInt();
            int[] a = new int[n+1], f = new int[n];
            for(int j = 0;j < n;j++){
                a[j] = sc.nextInt();
                f[j] = 0;
            }
            a[n] = Integer.MAX_VALUE;
            dandiao(a,f,n);

            int[] ra = new int[n+1];
            for(int j = 0;j< n;j++){
                ra[j] = a[n-j-1];
            }
            ra[n] = Integer.MAX_VALUE;
            int[] f1 = new int[n];
            for(int j = 0;j < n;j++){
                f1[j] = 0;
            }
            dandiao(ra,f1,n);

            for(int j = 0;j < n;j++){
                f[j] += f1[n-j-1];
            }
            for(int tmp:f){
                System.out.print(tmp+" ");
            }
            System.out.println();
        }
    }
}

/*
3
4
1 4 3 3
5
8 2 2 4 6
6
5 8 1 3 2 9
        */