package leetcode;

import java.util.Arrays;

/**
 * @program: CodeAlgorithm
 * @description: leetcode304
 * @author: zwh
 * @create: 2021-03-02 08:35
 **/
public class NumMatrix {

    private int[][] rowSum; // rowSum[i][j] 第i行[0,j]的和
    private int[][] sum; // sum[i][j] [0,0] 到 [i][j]的和

    public NumMatrix(int[][] matrix) {
        int m = matrix.length;
        if(m == 0){
            return;
        }
        int n = matrix[0].length;
        rowSum = new int[m][n];
        sum = new int[m][n];
        for (int i = 0; i < m; i++) {
            rowSum[i][0] = matrix[i][0];
            for (int j = 1; j < n; j++) {
                rowSum[i][j] = rowSum[i][j - 1] + matrix[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            sum[0][i] = rowSum[0][i];
        }
        int temSum = 0;
        for (int i = 0; i < m; i++) {
            temSum += matrix[i][0];
            sum[i][0] = temSum;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                sum[i][j] = sum[i - 1][j] + rowSum[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if(sum == null){
            return 0;
        }
        int sum1 = 0, sum2 = 0, sum3 = 0;
        if (row1 > 0 && col1 > 0) {
            sum1 = sum[row1 - 1][col1 - 1];
        }
        if (row1 > 0) {
            sum2 = sum[row1 - 1][col2] - sum1;
        }
        if (col1 > 0) {
            sum3 = sum[row2][col1 - 1] - sum1;
        }
        int sum4 = sum[row2][col2];
        return sum4 - sum1 - sum2 - sum3;
    }

    public int[] countBits(int num) {
        int[] sum = new int[num+1];
        sum[0] = 0;
        for(int i = 1;i <= num;i++){
            sum[i] = sum[i >> 1] + (i & 1);
        }
        return sum;
    }
    
    // 计算x二进制中1的个数
    private int popCount(int x){
        int count = 0;
        for(;x > 0;++count){
            x = x & (x-1);
        }
        return count;
    }

    

    public static void main(String[] args) {
        

    }
}
