import java.util.Arrays;

/**
 * @author happyzhao
 * @data 2020/3/14 21:54
 * @type PACKAGE_NAME
 * @question
 */
public class NetworkDelayTime {
    void dijkstra(int[][] times, int N, int K, int[] dis)
    {
        dis[K] = 0;
        int[] vis = new int[N];
        int cont = 0;
        Arrays.fill(vis,0);
        int min = Integer.MAX_VALUE,minN = -1;
        while(cont < N){
            min = Integer.MAX_VALUE;
            minN = -1;
            for(int i = 0;i < N;i++){
                if(vis[i] == 0 && min > dis[i]){
                    min = dis[i];
                    minN = i;
                }
            }
            if(minN == -1)
                return;
            vis[minN] = 1;
            cont++;
            for(int i = 0;i < N;i++){
                if(vis[i] == 0 && times[minN][i] != Integer.MAX_VALUE && dis[i] > dis[minN] + times[minN][i]){
                    dis[i] = dis[minN] + times[minN][i];
                }
            }
        }

    }
    public int networkDelayTime(int[][] times, int N, int K) {
        int[] dis = new int[N];
        Arrays.fill(dis,Integer.MAX_VALUE);
        int[][] graph = new int[N][N];
        for(int i = 0;i < N;i++){
            for(int j = 0;j < N;j++){
                graph[i][j] = Integer.MAX_VALUE;
            }
        }
        for(int i = 0;i < times.length;i++){
            int u = times[i][0],v = times[i][1],w = times[i][2];
            graph[u-1][v-1] = w;
        }
        dijkstra(graph,N,K-1,dis);
        for(int i = 0;i < N;i++)
        {
            System.out.print(dis[i]+" ");
        }
        System.out.println();
        int ans = -1;
        for(int i = 0;i < N;i++){
            if(dis[i] == Integer.MAX_VALUE)
                return -1;
            ans = Math.max(ans,dis[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] times = {{2,1,1},{2,3,1},{3,4,1}};
        NetworkDelayTime net = new NetworkDelayTime();
        System.out.println(net.networkDelayTime(times,4,2));
    }
}
