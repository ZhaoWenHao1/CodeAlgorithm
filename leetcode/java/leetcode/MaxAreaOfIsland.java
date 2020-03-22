import java.util.LinkedList;
import java.util.Queue;

/**
 * @author happyzhao
 * @data 2020/3/21 21:39
 * @type PACKAGE_NAME
 * @question
 */
class Position{
    int x,y;
    Position(){}
    Position(int x,int y){
        this.x = x;
        this.y = y;
    }
}
public class MaxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        int row = grid.length;
        if(row == 0) return 0;
        int col = grid[0].length;
        int[][] vis = new int[row][col];
        for(int i = 0;i < row;i++){
            for(int j = 0;j < col;j++){
                vis[i][j] = 0;
            }
        }
        int ans = 0;
        int tem = 0;
        int[][] steps = {{0,1},{0,-1},{1,0},{-1,0}};
        Queue<Position> queue = new LinkedList<>();
        for(int i = 0;i < row;i++){
            for(int j = 0;j < col;j++){
                if(grid[i][j] == 1 && vis[i][j] == 0){
                    queue.clear();
                    tem = 0;
                    queue.offer(new Position(i,j));
                    vis[i][j] = 1;
                    tem++;
                    while(!queue.isEmpty()){
                        Position pos = queue.poll();
                        //tem++;
                        for(int k = 0;k < 4;k++){
                            int px = pos.x + steps[k][0], py = pos.y + steps[k][1];
                            if(px >= 0 && px < row && py >= 0 && py < col
                                    && grid[px][py] == 1 && vis[px][py] == 0){
                                queue.offer(new Position(px,py));
                                vis[px][py] = 1;
                                tem++;
                            }
                        }
                    }
                    ans = Math.max(ans,tem);
                }
            }
        }
        return ans;

    }
}
