package kuaishou;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

/**
 * @author happyzhao
 * @data 2020/4/12 15:59
 * @type kuaishou
 * @question
 */
public class Main {
    public boolean dfs(int N, int last,int pos,Vector<Integer> ans){
        if(last == 0)
            return true;
        if(last < 0)
            return false;
        int high = (int)(Math.log(last)/Math.log(N));
        for(int i = pos;i <= high;i++){
            ans.add(i);
            if(dfs(N,(int)(last-Math.pow(N,i)),i+1,ans)){
                return true;
            }
            ans.remove(ans.size()-1);
        }
        return false;
    }
    public int[] GetPowerFactor (int R, int N) {
        // write code here
        int high = (int)(Math.log(R)/Math.log(N));
        int[] ans = null;
        Vector<Integer> vec = new Vector<Integer>();
        for(int i = 0;i <= high;i++){
            vec.add(i);
            if(dfs(N,(int)(R-Math.pow(N,i)),i+1,vec)){
                ans = new int[vec.size()];
                for(int j = 0;j < vec.size();j++){
                    ans[j] = vec.get(j);
                }
                return ans;
            }
            vec.remove(vec.size()-1);
        }
        return ans;
    }

    int sum = Integer.MAX_VALUE;
    int[] ret = null;
    public void trdfs(int[][] f,int[] ans,int s,int pos,int n){
        if(pos == n){
            if(sum > s){
                sum = s;
                //ret = int[n];
                for(int i = 0;i < n;i++)
                {
                    ret[i] = ans[i]+1;
                    // ans[i] = -1;
                }

            }
            return;
        }
        for(int i = 0;i < n;i++){
            if(ans[i] == -1){
                ans[i] = pos;// 第pos个人排到位置i
                trdfs(f,ans,s+f[pos][i],pos+1,n);
                ans[i] = -1;
            }
        }
    }

    public int[] WaitInLine (int[] a, int[] b) {
        // write code here
        if(a == null || b == null)
            return null;
        int n = a.length;
        if(n < 1)
            return null;
        int[][] f = new int[n][n];
        for(int i = 0;i < n;i++){
            for(int j = 0;j < n;j++){
                int pos = j+1;
                f[i][j] = a[i]*(pos-1)+b[i]*(n-pos);
            }
        }
        int[] ans = new int[n];
        ret = new int[n];
        for(int i = 0;i < n;i++){
            ans[i] = -1;
        }
        trdfs(f,ans,0,0,n);
        return ret;
    }


    public int GetMaxStaffs (char[][] pos) {
        // write code here
        if(pos == null) return -1;
        int m = pos.length;
        int n;
        if(m > 0){
            n = pos[0].length;
        }
        else
            return -1;
        int[][] f = new int[m][n];
        for(int i = 0;i < m;i++){
            for(int j = 0;j < n;j++){
                if(pos[i][j] == '.')
                    f[i][j] = 0;
                else
                    f[i][j] = -1;
            }
        }
        int ans = 0;
        int[][] steps = {{-1,0},{1,0},{0,-1},{0,1}};
        for(int i = 0;i < m;i++){
            for(int j = 0;j < n;j++){
                if(f[i][j] == 0){
                    f[i][j] = 1;
                    ans++;
                    for(int k = 0;k < 4;k++){
                        int dx = steps[k][0],dy = steps[k][1];
                        int px = i+dx,py = j+dy;
                        if(px < m && px >= 0 && py < n && py >= 0 && f[px][py] == 0){
                            f[px][py] = -1;
                        }
                    }
                }
            }
        }
        return ans;
    }



    public static void main(String[] args) {
        Main main = new Main();
        Scanner sc = new Scanner(System.in);
        int[] a = {8,9,7},b = {5,8,3};
        int[] ans = main.WaitInLine(a,b);
        if(ans == null){
            System.out.println("null");
        }
        else{
            for(int n:ans){
                System.out.print(n+" ");
            }
        }
    }
}
