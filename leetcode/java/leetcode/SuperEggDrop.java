/**
 * @author happyzhao
 * @data 2020/4/12 14:16
 * @type PACKAGE_NAME
 * @question
 */
public class SuperEggDrop {
    public int dp(int K, int N, int[][] f){
        if(f[K][N] == -1){
            int ans;
            if(K == 1)
                ans = N;
            else if(N == 0)
                ans = 0;
            else{
                // 求x0
                int low = 1, high = N;
                while(low < high-1){
                    int mid = (low+high)/2;
                    int t1 = dp(K-1,mid-1,f);
                    int t2 = dp(K,N-mid,f);
                    if(t1 < t2)
                        low = mid;
                    else if(t1 > t2)
                        high = mid;
                    else
                        high = low = mid;
                }
                int x0 = low, x1 = high;
                int max1 = Math.max(dp(K-1,x0-1,f),dp(K,N-x0,f));
                int max2 = Math.max(dp(K-1,x1-1,f),dp(K,N-x1,f));
                ans = 1+ Math.min(max1,max2);
            }
            f[K][N] = ans;
        }
        return f[K][N];
    }
    public int superEggDrop(int K, int N) {
        int[][] f = new int[K+1][N+1];
        for(int i = 0;i < K+1;i++){
            for(int j = 0;j < N+1;j++){
                f[i][j] = -1;
            }
        }
        return dp(K,N,f);
    }
}
