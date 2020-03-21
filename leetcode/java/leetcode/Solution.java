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
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {0,1,1,2,4,4,1,3,3,2};
        int[] ans = solution.getLeastNumbers(arr,6);
        for(int i = 0;i < 6;i++){
            System.out.print(ans[i]+" ");
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
    }
}
