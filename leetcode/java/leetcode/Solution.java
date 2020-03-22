import java.util.*;

/**
 * @author happyzhao
 * @data 2020/3/14 20:12
 * @type PACKAGE_NAME
 * @question easy
 */
public class Solution {
    public int missingNumber(int[] nums) {
        int[] flag = new int[nums.length+1];
        for(int i = 0;i < nums.length;i++) flag[i] = 0;
        for(int i = 0;i < nums.length;i++)
            flag[nums[i]]++;
        for(int i = 0;i < nums.length+1;i++){
            if(flag[i] == 0) return i;
        }
        return -1;
    }

    int happy(int m){
        int t;
        int ans = 0;
        while(m>0){
            t = m%10;
            m = m / 10;
            ans += t*t;
        }
        return ans;
    }
    public boolean isHappy(int n) {
        int ans = 0;
        if(n == 1) return true;
        int m = n;
        Set<Integer> set = new HashSet<Integer>();
        // 使用set用时较高，可使用快慢指针来判断是否有环
        int p = happy(n);

        while(m != 1 && m != p){
            m = happy(m);
            p = happy(happy(p));
            // System.out.println(m);
        }
        if(m == 1){
            return true;
        }
        else
            return false;
    }

    void adjust(int[] heap, int k){
        int pos = k;
        int pivot = heap[k];
        while(pos*2 < heap.length){
            int t = heap[pos*2];
            int p = pos*2;
            if(pos*2+1 < heap.length && heap[pos*2+1] > t){
                t = heap[pos*2+1];
                p++;
            }
            if(pivot < t){
                heap[pos] = t;
                pos = p;
            }
            else
                break;
        }
        heap[pos] = pivot;
    }
    void initHeap(int[] heap){
        int n = (heap.length-1)/2;
        for(int i = n;i > 0;i--){
            adjust(heap,i);
        }
    }
    public int[] getLeastNumbers(int[] arr, int k) {
        if(k == 0) return new int[0];
        int[] ans = new int[k+1]; // 大根堆
        for(int i = 1;i <= k;i++){
            ans[i] = arr[i-1];
        }
        initHeap(ans);
        for(int i = k;i < arr.length;i++){
            if(arr[i] < ans[1]){
                ans[1] = arr[i];
                adjust(ans,1);
            }
        }
        int[] anss = new int[k];
        for(int i = 0;i < k;i++){
            anss[i] = ans[i+1];
        }
        Arrays.sort(anss);
        return anss;
    }

    public int[] searchRange(int[] nums, int target) {
        int[] ans = new int[2];
        int low = 0, high = nums.length-1;
        int idx = -1;
        while(low <= high){
            int mid = (low+high)/2;
            if(nums[mid] < target){
                low = mid+1;
            }
            else if(nums[mid] > target)
            {
                high = mid-1;
            }
            else
            {
                idx = mid;
                break;
            }
        }
        if(idx == -1){
            ans[0] = -1;
            ans[1] = -1;
        }
        else{
            for(int i = idx;i >= 0;i--){
                if(nums[i] == nums[idx]){
                    ans[0] = i;
                }
                else
                    break;
            }
            for(int i = idx;i < nums.length;i++){
                if(nums[i] == nums[idx]){
                    ans[1] = i;
                }
                else
                    break;
            }
        }
        return ans;
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int[] f = new int[nums.length];
        for(int i = 0;i < f.length;i++)
            f[i] = 0;
        List<Integer> tem = new ArrayList<>(nums.length);
        myAdd(ans,tem,nums,f,nums.length);
        return ans;
    }
    void myAdd(List<List<Integer>> ans, List<Integer> res, int[] nums, int[] f,int last){
        // System.out.println("myadd");
        if(last == 0){
            List<Integer> t = new ArrayList<>(res);
            ans.add(t);
            return;
        }
        for(int i = 0;i < nums.length;i++){
            if(f[i] == 1)
                continue;
            res.add(nums[i]);
            f[i] = 1;
            myAdd(ans,res,nums,f,last-1);
            f[i] = 0;
            res.remove(res.size()-1);
        }
    }

    public int uniquePaths(int m, int n) {
        int[][] f = new int[m][n];
        for(int i = 0;i < m;i++){
            for(int j = 0;j < n;j++){
                f[i][j] = 0;
            }
        }
        for(int i = 0;i < n;i++)
            f[0][i] = 1;
        for(int i = 1;i < m;i++){
            for(int j = 0;j < n;j++){
                f[i][j] = f[i-1][j];
                if(j > 0){
                    f[i][j] += f[i][j-1];
                }
            }
        }
        return f[m-1][n-1];
    }


    /*public int minimumTotal(List<List<Integer>> triangle) {

    }*/

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {1,2,3};
        System.out.println(solution.permute(arr));

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
    }
}
