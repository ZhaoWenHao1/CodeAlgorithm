import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author happyzhao
 * @data 2020/3/29 0:22
 * @type PACKAGE_NAME
 * @question 47.全排列2
 */
public class PermuteUnique {
    void dfs(List<List<Integer>> ans, List<Integer> ret, boolean[] f, int[] nums, int cont){
        if(cont == nums.length){
            ans.add(new ArrayList<>(ret));
            return;
        }
        int last = 0, j = 0;
        for(int i = 0;i < nums.length;i++){
            if(f[i])
                continue;
            if(j == 0 || nums[i] != last){
                ret.add(nums[i]);
                f[i] = true;
                dfs(ans,ret,f,nums,cont+1);
                f[i] = false;
                ret.remove(ret.size()-1);
                last = nums[i];
                j++;
            }
        }
    }
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        boolean[] f = new boolean[nums.length];
        for(int i = 0;i < nums.length;i++){
            f[i] = false;
        }
        Arrays.sort(nums);
        List<Integer> ret = new ArrayList<>(nums.length);
        dfs(ans,ret,f,nums,0);
        return ans;
    }

    void dfs(List<List<Integer>> ans, List<Integer> ret, int[] candidates, int target, int pos){
        if(target == 0){
            ans.add(new ArrayList<>(ret));
            return;
        }
        if(candidates.length > 0 && target < candidates[0])
            return;
        int last = -1;
        for(int i = pos;i < candidates.length;i++){
            if(candidates[i] == last){
                continue;
            }
            if(target >= candidates[i]){
                ret.add(candidates[i]);
                dfs(ans, ret, candidates,target-candidates[i],i+1);
                ret.remove(ret.size()-1);
                last = candidates[i];
            }
            else
                break;
        }
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> ret = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(ans, ret, candidates, target, 0);
        return ans;
    }
    int[][] steps = {{1,0},{-1,0},{0,1},{0,-1}};
    int dfs(int[][] grid, int[][] f, int[][] vis, int N, int x, int y){ // 返回grid[x][y]的值
        int ret = Integer.MAX_VALUE;
        for(int i = 0;i < 4;i++){
            int dx = steps[i][0], dy = steps[i][1];
            int t = 0;
            if(x+dx < N && x+dx >= 0 && y+dy < N && y+dy >= 0){
                if(grid[x+dx][y+dy] == 1){
                    t = 0;
                    f[x][y] = 1;
                    return 1;
                }else {
                    if(f[x+dx][y+dy] != 0){
                        ret = Math.min(ret,f[x+dx][y+dy]+1);
                    }
                    else{
                        if(vis[x+dx][y+dy] == 0){
                            vis[x+dx][y+dy] = 1;
                            t = dfs(grid,f,vis,N,x+dx,y+dy);
                            ret = Math.min(ret,t+1);
                        }
                    }
                }
            }
        }
        return ret;

    }
    public int maxDistance(int[][] grid) {
        int m = grid.length;
        if(m < 1) return -1;
        int n = grid[0].length;
        int[][] f = new int[m][n]; // 海洋到陆地的距离（到最近陆地的距离）
        int ans = 0;
        for(int i = 0;i < m;i++)
        {
            for(int j = 0;j < m;j++){
                if(grid[i][j] == 0 && f[i][j] == 0){
                    int[][] vis = new int[m][n];
                    int t = dfs(grid,f,vis,m,i,j);
                    f[i][j] = t;
                    //System.out.print(t+" ");
                    ans = Math.max(ans,t);
                }
            }
            //System.out.println();
        }
        for(int i = 0;i < n;i++){
            for(int j = 0;j < n;j++){
                System.out.print(f[i][j]+" ");
            }
            System.out.println();
        }
        return ans;
    }

    public static void main(String[] args) {
        PermuteUnique per = new PermuteUnique();
        int[] nums = {1,1,2};
        int[] can = {10,1,2,7,6,1,5};
        int[][] grid = {{1,0,0},{0,0,0},{0,0,0}};
        int target = 8;
        System.out.println(per.maxDistance(grid));
    }
}
