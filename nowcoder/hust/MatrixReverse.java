package hust;

import java.util.Scanner;

/**
 * @author happyzhao
 * @data 2020/3/11 17:00
 * @type
 * @question
 */
public class MatrixReverse {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] nums = new int[n][n];
        for(int i = 0;i < n;i++){
            for(int j = 0;j < n;j++){
                nums[i][j] = sc.nextInt();
            }
        }
        for(int i = 0;i < n;i++){
            for(int j = 0;j < n;j++){
                System.out.print(nums[j][i]+" ");
            }
            System.out.println();
        }
    }
}
