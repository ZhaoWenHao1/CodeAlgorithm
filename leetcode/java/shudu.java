import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class shudu {
    public static int[][] nums = new int[9][9];
    public static int[][] rowf = new int[9][9];//row[i][j] = 1 表示第row行已有j
    public static int[][] colf = new int[9][9];
    public static int[][] blcf = new int[9][9];
    public static void printNums()
    {
        for(int i = 0;i < 9;i++)
        {
            for(int j = 0;j < 9;j++)
            {
                if(nums[i][j] >= 0)
                    System.out.print(nums[i][j]+1+ " ");
                else
                    System.out.print("_ ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static int rc2blc(int row,int col)
    {
        int n = col / 3 , m = row /3;
        return m*3+n;
    }
    public static void put(int row,int col,int k)//在num[row][col]下放k
    {
        int blcnum = rc2blc(row,col);
        rowf[row][k] = 1;
        colf[col][k] = 1;
        blcf[blcnum][k] = 1;
    }
    public static void delete(int row,int col,int k)
    {
        int blcnum = rc2blc(row,col);
        rowf[row][k] = 0;
        colf[col][k] = 0;
        blcf[blcnum][k] = 0;
    }
    public static boolean Myhanle(int row,int col) //处理nums[row][col]后面的
    {
        //System.out.print( row +""+ col + "\t");
        for(int i = col+1;i < 9;i++)
        {
            if(nums[row][i] == -1)
                return handle(row,i);
        }
        for(int i = row+1;i < 9;i++)
        {
            for(int j = 0;j < 9;j++)
            {
                if(nums[i][j] == -1)
                    return handle(i,j);
            }
        }
        //System.out.println("Myhandle : " + row + " " + col);
        return true;
    }
    public static boolean handle(int row,int col) //nums[row][col]为-1，进行放置
    {
        int blcNum = rc2blc(row,col);
        int flag = 0;
        if(row == 6 && col == 2)
        {
            printNums();
            flag = 1;
        }
        //int find = 0;//必有一个可以填的数，否则错误
        for(int k = 8;k >= 0;k--)
        {
            if(rowf[row][k] == 0 && colf[col][k] == 0 && blcf[blcNum][k] == 0)
            {
                //find = 1;
                nums[row][col] = k;
                put(row,col,k);
                printNums();
                //System.out.println();
                if(Myhanle(row,col))
                    return true;
                else
                {
                    nums[row][col] = -1;
                    delete(row,col,k);
                }

            }
        }
        //System.out.println("handle : " + row + " " + col);
        return false;

    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        for(int i = 0;i < 9;i++)
        {
            for(int j = 0;j < 9;j++)
            {
                nums[i][j] = sc.nextInt()-1;
            }
        }

/*
        for(int i = 0;i < 9;i++)
        {
            for(int j = 0;j < 9;j++)
            {
                System.out.print(rc2blc(i,j)+" ");
            }
            System.out.println();
        }*/


        for(int i = 0;i < 9;i++)
        {
            for(int j = 0;j < 9;j++)
            {
                rowf[i][j] = 0;
                colf[i][j] = 0;
                blcf[i][j] = 0;
            }
        }
        for(int i = 0;i < 9;i++)
        {
            for(int j = 0;j < 9;j++)
            {
                if(nums[i][j] != -1)
                {
                    put(i,j,nums[i][j]);
                }
            }
        }
        if(Myhanle(0,-1))
        {
            for(int i = 0;i < 9;i++)
            {
                for(int j = 0;j < 9;j++)
                {
                    System.out.print(nums[i][j]+1+" ");
                }
                System.out.println();
            }
        }
        else
            System.out.println("error");


    }
}
/*
0 0 5 3 0 0 0 0 0
8 0 0 0 0 0 0 2 0
0 7 0 0 1 0 5 0 0
4 0 0 0 0 5 3 0 0
0 1 0 0 7 0 0 0 6
0 0 3 2 0 0 0 8 0
0 6 0 5 0 0 0 0 9
0 0 4 0 0 0 0 3 0
0 0 0 0 0 9 7 0 0

5 3 0 0 7 0 0 0 0
6 0 0 1 9 5 0 0 0
0 9 8 0 0 0 0 6 0
8 0 0 0 6 0 0 0 3
4 0 0 8 0 3 0 0 1
7 0 0 0 2 0 0 0 6
0 6 0 0 0 0 2 8 0
0 0 0 4 1 9 0 0 5
0 0 0 0 8 0 0 7 9

5 3 4 6 7 8 9 1 2
6 7 2 1 9 5 3 4 8
1 9 8 3 4 2 5 6 7
8 5 9 7 6 1 4 2 3
4 2 6 8 5 3 7 9 1
7 1 3 9 2 4 8 5 6
9 6 1 5 3 7 2 8 4
2 8 7 4 1 9 6 3 5
3 4 5 2 8 6 1 7 9
 */