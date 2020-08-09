package leetcode;
import java.util.*;

/**
 * @author happyzhao
 * @data 2020/3/14 20:12
 * @type PACKAGE_NAME
 * @question easy
 */
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
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


    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle.size() < 1)
            return 0;
        int[] f = new int[triangle.size()], fn = new int[triangle.size()],tf;
        f[0] = triangle.get(0).get(0);
        for(int i = 1;i < triangle.size();i++){
            int lastn = i;
            for(int k = 0;k < lastn;k++)
                System.out.print(f[k]+" ");
            System.out.println();
            for(int j = 0;j <= i;j++){
                int n1 = j-1 >= 0 ? f[j-1] : Integer.MAX_VALUE;
                int n2 = j < lastn ? f[j] : Integer.MAX_VALUE;
                int t = Math.min(n1,n2);
                fn[j] = t + triangle.get(i).get(j);
            }
            tf = f;
            f = fn;
            fn = tf;
        }
        int ans = Integer.MAX_VALUE;
        for(int i = 0;i < triangle.size();i++){
            System.out.print(f[i]+" ");
            if(ans > f[i])
                ans = f[i];
        }
        return ans;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        int flag = 1; // 从左到右遍历
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null)
            return ans;
        queue.offer(root);
        queue.offer(null);// 每一层的标志
        // flag = -flag;
        List<Integer> res = new ArrayList<>();
        while(!queue.isEmpty()){
            TreeNode t = queue.poll();
            if(t == null){
                if(flag == -1){
                    List<Integer> tlist = new ArrayList<>();
                    for(int i = 0;i < res.size();i++){
                        tlist.add(res.get(res.size()-i-1));
                    }
                    ans.add(tlist);
                }
                else
                {
                    ans.add(new ArrayList<>(res));
                }
                if(queue.isEmpty())
                    break;
                flag = -flag;
                res.clear();
                queue.offer(null);
            }
            else{
                res.add(t.val);
                if(t.left != null){
                    queue.offer(t.left);
                }
                if(t.right != null){
                    queue.offer(t.right);
                }
            }
        }
        return ans;
    }
    String handle(String n1, String n2){ // n1 - n2
        System.out.println("n1  n2: " + n1 + " "+ n2);
        if(n1.length() < 1 || n2.length() < 1){
            return null;
        }
        int len1 = n1.length(), len2 = n2.length();
        StringBuilder strb = new StringBuilder();
        int last = 0;
        int pos = 0;
        if(len1 > len2){
            strb.append(n1.substring(0,len1-len2-1));
            last = n1.charAt(len1-len2-1)-'0';
            pos = len1-len2;
        }
        int j = 0;
        System.out.println(strb);
        while(pos < len1){
            int t1 = last*10 + n1.charAt(pos)-'0', t2 = n2.charAt(j)-'0';
            int res = t1-t2;
            if(res < 0){
                String tem = handle(strb.append(String.valueOf(last)).toString(),"1");
                strb.delete(0,strb.length());
                strb.append(tem.substring(0,tem.length()-1));
                // last = tem.charAt(tem.length()-1)-'0';
                last = 10;
                continue;
            }
            strb.append(String.valueOf(res/10));
            last = res%10;
            pos++;
            j++;
        }
        strb.append(String.valueOf(last));
        System.out.println("return  " + strb);
        return strb.toString();
    }

    // 按照长度进行排序降序
    String[] stringsSort(String[] words){
        for(int i = 0;i < words.length;i++){
            for(int j = 0;j < words.length-i-1;j++){
                if(words[j].length() < words[j+1].length()){
                    String t = words[j];
                    words[j] = words[j+1];
                    words[j+1] = t;
                }
            }
        }
        return words;
    }
    public int minimumLengthEncoding(String[] words) {
        words = stringsSort(words);
        StringBuilder sb = new StringBuilder();
        Vector<Integer> vec = new Vector<>();
        for(int i = 0;i < words.length;i++){
            int idx = 0;
            int find = 0;
            StringBuilder tem = new StringBuilder(words[i]);
            tem.append("#");
            if((idx = sb.indexOf(tem.toString())) != -1){
                vec.add(idx);
            }
            else{
                vec.add(sb.length());
                sb.append(tem);
            }
        }
        return sb.length();

    }

    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        Vector<String> res = new Vector<>();
        int start = 0, end = 1;
        while(start < s.length() && s.charAt(start) == ' ') start++;
        end = start+1;
        while(end < s.length()){
            if(s.charAt(end) == ' '){
                res.add(s.substring(start,end));
                //System.out.println("add:"+ s.substring(start,end) + ".");
                start = end+1;
                while(start < s.length() && s.charAt(start) == ' ') start++;
                end = start+1;
            }
            else
                end++;
        }
        if(start < s.length())
        {
            res.add(s.substring(start,end));
        }
        for(int i = res.size()-1;i >= 0;i--){
            //System.out.println(res.get(i));
            sb.append(res.get(i));
            if(i != 0)
                sb.append(' ');
        }
        return sb.toString();
    }

    public String decodeString(String s) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i < s.length();){
            if(s.charAt(i) >= '0' && s.charAt(i) <= '9'){
                int t = s.indexOf('[',i);
                int num = Integer.valueOf(s.substring(i,t));
                int flg = 1, idx = -1;
                for(int j = t+1;j < s.length();j++){
                    if(s.charAt(j) == '[')
                        flg++;
                    if(s.charAt(j) == ']')
                        flg--;
                    if(flg == 0){
                        idx = j;
                        break;
                    }
                }
                String substr = decodeString(s.substring(t+1, idx));
                for(int j = 0;j < num;j++){
                    sb.append(substr);
                }
                i = idx+1;
            }
            else if((s.charAt(i) >= 'a' && s.charAt(i) <= 'z') || (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') )
            {
                sb.append(s.charAt(i));
                i++;
            }
            else{ // 空格
                i++;
            }
        }
        return sb.toString();
    }
    int getIndex(int[][] points, int n, boolean isX){
        int f = isX ? 0 : 1;
        for(int i = 0;i < points.length;i++){
            if(points[i][f] == n)
                return i;
        }
        return -1;
    }
    public int[] bestLine(int[][] points) {
        // 按照x
        HashMap<Integer, Integer> hashMapx = new HashMap<>();
        for(int i = 0;i < points.length;i++){
            int val = 1;
            if(hashMapx.containsKey(points[i][0])){
                val = hashMapx.get(points[i][0]) + 1;
            }
            hashMapx.put(points[i][0], val);
        }
        int maxx = -1, idxx = -1;
        for(int i: hashMapx.keySet()){
            if(maxx < hashMapx.get(i)){
                maxx = hashMapx.get(i);
                idxx = getIndex(points,i,true);
            }
            else if(maxx == hashMapx.get(i) && idxx > getIndex(points,i,true)){
                idxx = getIndex(points,i,true);
            }
        }
        Vector<Integer> vecx = new Vector<>();
        for(int i = 0;i < points.length;i++){
            if(points[i][0] == points[idxx][0]){
                vecx.add(i);
            }
        }

        // 按照y
        HashMap<Integer, Integer> hashMapy = new HashMap<>();
        for(int i = 0;i < points.length;i++){
            int val = 1;
            if(hashMapy.containsKey(points[i][1])){
                val = hashMapy.get(points[i][1]) + 1;
            }
            hashMapy.put(points[i][1], val);
        }
        int maxy = -1, idxy = -1;
        for(int i: hashMapy.keySet()){
            if(maxy < hashMapy.get(i)){
                maxy = hashMapy.get(i);
                idxy = getIndex(points,i,false);
            }
            else if(maxy == hashMapy.get(i) && idxy > getIndex(points,i,false)){
                idxy = getIndex(points,i,false);
            }
        }
        Vector<Integer> vecy = new Vector<>();
        for(int i = 0;i < points.length;i++){
            if(points[i][1] == points[idxy][1]){
                vecy.add(i);
            }
        }

        Vector<Integer> vec;
        if(maxx > maxy){
            vec= vecx;
        }
        else if(maxx < maxy){
            vec = vecy;
        }
        else{
            if(vecx.get(0) <= vecy.get(0))
                vec = vecx;
            else
                vec = vecy;
        }
        Collections.sort(vec);
        int[] ans = new int[2];
        ans[0] = vec.get(0);
        ans[1] = vec.get(1);
        return ans;
    }



    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] nums = {{-2160,34152},{-2942,1637},{-8995,-31970},{-15048,-33786},{-18996,-41685},{-24537,-19974},{-40199,14156},{-16766,-24497},{3111,-28338},{-16766,-25972},{15217,-24706},{-2942,-30154},{21270,-22890},{-15361,-15384}};
        int[] ans = solution.bestLine(nums);
        System.out.println(ans[0] + " " + ans[1]);

        /*PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });*/


    }
}
