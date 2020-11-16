package leetcode;

import java.util.*;

/**
 * @author happyzhao
 * @data 2020/3/14 20:12
 * @type PACKAGE_NAME
 * @question easy
 */


public class Solution {
    public int missingNumber(int[] nums) {
        int[] flag = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) flag[i] = 0;
        for (int i = 0; i < nums.length; i++)
            flag[nums[i]]++;
        for (int i = 0; i < nums.length + 1; i++) {
            if (flag[i] == 0) return i;
        }
        return -1;
    }

    int happy(int m) {
        int t;
        int ans = 0;
        while (m > 0) {
            t = m % 10;
            m = m / 10;
            ans += t * t;
        }
        return ans;
    }

    public boolean isHappy(int n) {
        int ans = 0;
        if (n == 1) return true;
        int m = n;
        Set<Integer> set = new HashSet<Integer>();
        // 使用set用时较高，可使用快慢指针来判断是否有环
        int p = happy(n);

        while (m != 1 && m != p) {
            m = happy(m);
            p = happy(happy(p));
            // System.out.println(m);
        }
        if (m == 1) {
            return true;
        } else
            return false;
    }

    void adjust(int[] heap, int k) {
        int pos = k;
        int pivot = heap[k];
        while (pos * 2 < heap.length) {
            int t = heap[pos * 2];
            int p = pos * 2;
            if (pos * 2 + 1 < heap.length && heap[pos * 2 + 1] > t) {
                t = heap[pos * 2 + 1];
                p++;
            }
            if (pivot < t) {
                heap[pos] = t;
                pos = p;
            } else
                break;
        }
        heap[pos] = pivot;
    }

    void initHeap(int[] heap) {
        int n = (heap.length - 1) / 2;
        for (int i = n; i > 0; i--) {
            adjust(heap, i);
        }
    }

    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0) return new int[0];
        int[] ans = new int[k + 1]; // 大根堆
        for (int i = 1; i <= k; i++) {
            ans[i] = arr[i - 1];
        }
        initHeap(ans);
        for (int i = k; i < arr.length; i++) {
            if (arr[i] < ans[1]) {
                ans[1] = arr[i];
                adjust(ans, 1);
            }
        }
        int[] anss = new int[k];
        for (int i = 0; i < k; i++) {
            anss[i] = ans[i + 1];
        }
        Arrays.sort(anss);
        return anss;
    }

    public int[] searchRange(int[] nums, int target) {
        int[] ans = new int[2];
        int low = 0, high = nums.length - 1;
        int idx = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                idx = mid;
                break;
            }
        }
        if (idx == -1) {
            ans[0] = -1;
            ans[1] = -1;
        } else {
            for (int i = idx; i >= 0; i--) {
                if (nums[i] == nums[idx]) {
                    ans[0] = i;
                } else
                    break;
            }
            for (int i = idx; i < nums.length; i++) {
                if (nums[i] == nums[idx]) {
                    ans[1] = i;
                } else
                    break;
            }
        }
        return ans;
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int[] f = new int[nums.length];
        for (int i = 0; i < f.length; i++)
            f[i] = 0;
        List<Integer> tem = new ArrayList<>(nums.length);
        myAdd(ans, tem, nums, f, nums.length);
        return ans;
    }

    void myAdd(List<List<Integer>> ans, List<Integer> res, int[] nums, int[] f, int last) {
        // System.out.println("myadd");
        if (last == 0) {
            List<Integer> t = new ArrayList<>(res);
            ans.add(t);
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (f[i] == 1)
                continue;
            res.add(nums[i]);
            f[i] = 1;
            myAdd(ans, res, nums, f, last - 1);
            f[i] = 0;
            res.remove(res.size() - 1);
        }
    }

    public int uniquePaths(int m, int n) {
        int[][] f = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                f[i][j] = 0;
            }
        }
        for (int i = 0; i < n; i++)
            f[0][i] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                f[i][j] = f[i - 1][j];
                if (j > 0) {
                    f[i][j] += f[i][j - 1];
                }
            }
        }
        return f[m - 1][n - 1];
    }


    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() < 1)
            return 0;
        int[] f = new int[triangle.size()], fn = new int[triangle.size()], tf;
        f[0] = triangle.get(0).get(0);
        for (int i = 1; i < triangle.size(); i++) {
            int lastn = i;
            for (int k = 0; k < lastn; k++)
                System.out.print(f[k] + " ");
            System.out.println();
            for (int j = 0; j <= i; j++) {
                int n1 = j - 1 >= 0 ? f[j - 1] : Integer.MAX_VALUE;
                int n2 = j < lastn ? f[j] : Integer.MAX_VALUE;
                int t = Math.min(n1, n2);
                fn[j] = t + triangle.get(i).get(j);
            }
            tf = f;
            f = fn;
            fn = tf;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < triangle.size(); i++) {
            System.out.print(f[i] + " ");
            if (ans > f[i])
                ans = f[i];
        }
        return ans;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        int flag = 1; // 从左到右遍历
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null)
            return ans;
        queue.offer(root);
        queue.offer(null);// 每一层的标志
        // flag = -flag;
        List<Integer> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode t = queue.poll();
            if (t == null) {
                if (flag == -1) {
                    List<Integer> tlist = new ArrayList<>();
                    for (int i = 0; i < res.size(); i++) {
                        tlist.add(res.get(res.size() - i - 1));
                    }
                    ans.add(tlist);
                } else {
                    ans.add(new ArrayList<>(res));
                }
                if (queue.isEmpty())
                    break;
                flag = -flag;
                res.clear();
                queue.offer(null);
            } else {
                res.add(t.val);
                if (t.left != null) {
                    queue.offer(t.left);
                }
                if (t.right != null) {
                    queue.offer(t.right);
                }
            }
        }
        return ans;
    }

    String handle(String n1, String n2) { // n1 - n2
        System.out.println("n1  n2: " + n1 + " " + n2);
        if (n1.length() < 1 || n2.length() < 1) {
            return null;
        }
        int len1 = n1.length(), len2 = n2.length();
        StringBuilder strb = new StringBuilder();
        int last = 0;
        int pos = 0;
        if (len1 > len2) {
            strb.append(n1.substring(0, len1 - len2 - 1));
            last = n1.charAt(len1 - len2 - 1) - '0';
            pos = len1 - len2;
        }
        int j = 0;
        System.out.println(strb);
        while (pos < len1) {
            int t1 = last * 10 + n1.charAt(pos) - '0', t2 = n2.charAt(j) - '0';
            int res = t1 - t2;
            if (res < 0) {
                String tem = handle(strb.append(String.valueOf(last)).toString(), "1");
                strb.delete(0, strb.length());
                strb.append(tem.substring(0, tem.length() - 1));
                // last = tem.charAt(tem.length()-1)-'0';
                last = 10;
                continue;
            }
            strb.append(String.valueOf(res / 10));
            last = res % 10;
            pos++;
            j++;
        }
        strb.append(String.valueOf(last));
        System.out.println("return  " + strb);
        return strb.toString();
    }

    // 按照长度进行排序降序
    String[] stringsSort(String[] words) {
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length - i - 1; j++) {
                if (words[j].length() < words[j + 1].length()) {
                    String t = words[j];
                    words[j] = words[j + 1];
                    words[j + 1] = t;
                }
            }
        }
        return words;
    }

    public int minimumLengthEncoding(String[] words) {
        words = stringsSort(words);
        StringBuilder sb = new StringBuilder();
        Vector<Integer> vec = new Vector<>();
        for (int i = 0; i < words.length; i++) {
            int idx = 0;
            int find = 0;
            StringBuilder tem = new StringBuilder(words[i]);
            tem.append("#");
            if ((idx = sb.indexOf(tem.toString())) != -1) {
                vec.add(idx);
            } else {
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
        while (start < s.length() && s.charAt(start) == ' ') start++;
        end = start + 1;
        while (end < s.length()) {
            if (s.charAt(end) == ' ') {
                res.add(s.substring(start, end));
                //System.out.println("add:"+ s.substring(start,end) + ".");
                start = end + 1;
                while (start < s.length() && s.charAt(start) == ' ') start++;
                end = start + 1;
            } else
                end++;
        }
        if (start < s.length()) {
            res.add(s.substring(start, end));
        }
        for (int i = res.size() - 1; i >= 0; i--) {
            //System.out.println(res.get(i));
            sb.append(res.get(i));
            if (i != 0)
                sb.append(' ');
        }
        return sb.toString();
    }

    public String decodeString(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); ) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                int t = s.indexOf('[', i);
                int num = Integer.valueOf(s.substring(i, t));
                int flg = 1, idx = -1;
                for (int j = t + 1; j < s.length(); j++) {
                    if (s.charAt(j) == '[')
                        flg++;
                    if (s.charAt(j) == ']')
                        flg--;
                    if (flg == 0) {
                        idx = j;
                        break;
                    }
                }
                String substr = decodeString(s.substring(t + 1, idx));
                for (int j = 0; j < num; j++) {
                    sb.append(substr);
                }
                i = idx + 1;
            } else if ((s.charAt(i) >= 'a' && s.charAt(i) <= 'z') || (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z')) {
                sb.append(s.charAt(i));
                i++;
            } else { // 空格
                i++;
            }
        }
        return sb.toString();
    }

    int getIndex(int[][] points, int n, boolean isX) {
        int f = isX ? 0 : 1;
        for (int i = 0; i < points.length; i++) {
            if (points[i][f] == n)
                return i;
        }
        return -1;
    }

    public int[] bestLine(int[][] points) {
        // 按照x
        HashMap<Integer, Integer> hashMapx = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            int val = 1;
            if (hashMapx.containsKey(points[i][0])) {
                val = hashMapx.get(points[i][0]) + 1;
            }
            hashMapx.put(points[i][0], val);
        }
        int maxx = -1, idxx = -1;
        for (int i : hashMapx.keySet()) {
            if (maxx < hashMapx.get(i)) {
                maxx = hashMapx.get(i);
                idxx = getIndex(points, i, true);
            } else if (maxx == hashMapx.get(i) && idxx > getIndex(points, i, true)) {
                idxx = getIndex(points, i, true);
            }
        }
        Vector<Integer> vecx = new Vector<>();
        for (int i = 0; i < points.length; i++) {
            if (points[i][0] == points[idxx][0]) {
                vecx.add(i);
            }
        }

        // 按照y
        HashMap<Integer, Integer> hashMapy = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            int val = 1;
            if (hashMapy.containsKey(points[i][1])) {
                val = hashMapy.get(points[i][1]) + 1;
            }
            hashMapy.put(points[i][1], val);
        }
        int maxy = -1, idxy = -1;
        for (int i : hashMapy.keySet()) {
            if (maxy < hashMapy.get(i)) {
                maxy = hashMapy.get(i);
                idxy = getIndex(points, i, false);
            } else if (maxy == hashMapy.get(i) && idxy > getIndex(points, i, false)) {
                idxy = getIndex(points, i, false);
            }
        }
        Vector<Integer> vecy = new Vector<>();
        for (int i = 0; i < points.length; i++) {
            if (points[i][1] == points[idxy][1]) {
                vecy.add(i);
            }
        }

        Vector<Integer> vec;
        if (maxx > maxy) {
            vec = vecx;
        } else if (maxx < maxy) {
            vec = vecy;
        } else {
            if (vecx.get(0) <= vecy.get(0))
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


    public int search(int[] nums, int target) {
        int tol = 0;
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 二分法找到该数 再统计
        int left = 0, right = nums.length - 1;
        int mid = (left + right) / 2;
        int idx = -1;
        while (left <= right) {
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                idx = mid;
                break;
            }
            mid = (left + right) / 2;
        }
        if (idx == -1) {
            return 0;
        }
        int i = idx;
        while (i < nums.length && nums[i++] == target) tol++;
        i = idx - 1;
        while (i >= 0 && nums[i--] == target) tol++;
        return tol;
    }

    /**
     * 78. 子集
     * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        List<Integer> curList = new ArrayList<>();
        subsetsHandle(nums, 0, res, curList);
        return res;
    }

    private void subsetsHandle(int[] nums, int pos, List<List<Integer>> res, List<Integer> curList) {
        if (pos == nums.length) {
            res.add(new ArrayList<>(curList));
            return;
        }
        // 对于每个元素都有 选择 和 不选择 两种操作
        curList.add(nums[pos]);
        subsetsHandle(nums, pos + 1, res, curList);
        curList.remove(curList.size() - 1);
        subsetsHandle(nums, pos + 1, res, curList);

    }

    // 采用 RML  右中左的遍历方式，依次加上所有
    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return root;
        }
        int count = 0;
        travelTree(root, count);
        return root;
    }

    private int travelTree(TreeNode root, int count) {
        if (root == null) {
            return count;
        }
        if (root.right != null) {
            count = travelTree(root.right, count);
        }
        root.val += count;
        count = root.val;
        if (root.left != null) {
            count = travelTree(root.left, count);
        }
        return count;
    }

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        t1.val += t1.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }

    /**
     * 106 根据一棵树的中序遍历与后序遍历构造二叉树。
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> idxMap = new HashMap<>(inorder.length);

        if (inorder == null || postorder == null || inorder.length != postorder.length || inorder.length < 1) {
            return null;
        }
        for (int i = 0; i < inorder.length; i++) {
            idxMap.put(inorder[i], i);
        }
        return buildTree(idxMap, inorder, postorder);


    }

    public TreeNode buildTree(Map<Integer, Integer> idxMap, int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length != postorder.length || inorder.length < 1) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postorder.length - 1]);
        if (inorder.length == 1) {
            return root;
        }
        int rootIdxOfInorder = idxMap.get(root.val);

        int[] leftTreeInorder = Arrays.copyOfRange(inorder, 0, rootIdxOfInorder);
        int[] rightTreeInorder = Arrays.copyOfRange(inorder, rootIdxOfInorder + 1, inorder.length);

        int[] leftTreePostorder = Arrays.copyOfRange(postorder, 0, rootIdxOfInorder);
        int[] rightTreePostorder = Arrays.copyOfRange(postorder, rootIdxOfInorder, postorder.length - 1);
        root.left = buildTree(leftTreeInorder, leftTreePostorder);
        root.right = buildTree(rightTreeInorder, rightTreePostorder);
        return root;
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        int count = root.val;
        List<Integer> list = new ArrayList<>();
        list.add(root.val);
        if (root.left == null && root.right == null && count == sum) {
            res.add(list);
            return res;
        }
        if (root.left != null) {
            pathSum(root.left, sum, count, list, res);
        }
        if (root.right != null) {
            pathSum(root.right, sum, count, list, res);
        }
        return res;
    }

    private void pathSum(TreeNode root, int sum, int count, List<Integer> list, List<List<Integer>> res) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            if (root.val + count == sum) {
                list.add(root.val);
                res.add(new ArrayList<>(list));
                list.remove(list.size() - 1);
            }
            return;
        }
        list.add(root.val);
        if (root.left != null) {
            pathSum(root.left, sum, count + root.val, list, res);
        }
        if (root.right != null) {
            pathSum(root.right, sum, count + root.val, list, res);
        }
        list.remove(list.size() - 1);
    }


    /**
     * 117. 填充每个节点的下一个右侧节点指针 II
     *
     * @param root
     * @return
     */
    // bfs
    Node last = null, nextStart = null;

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Node start = root;
        while (start != null) {
            last = null;
            nextStart = null;
            for (Node p = start; p != null; p = p.next) {
                if (p.left != null) {
                    handleConnect(p.left);
                }
                if (p.right != null) {
                    handleConnect(p.right);
                }
            }
            start = nextStart;
        }

        return root;
    }

    void handleConnect(Node p) {
        if (last != null) {
            last.next = p;
        }
        if (nextStart == null) {
            nextStart = p;
        }
        last = p;
    }

    public int numJewelsInStones(String J, String S) {
        Set set = new HashSet<Character>();
        J.chars().mapToObj(i -> (char) i).forEach(i -> set.add(i));
        return (int) S.chars().mapToObj(i -> (char) i).filter(set::contains).count();
    }

    /**
     * 18. 四数之和
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 1) {
            return res;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int tar = target - nums[i];
            List<List<Integer>> lists = threeSum(remove(nums, i), tar, nums[i]);
            for (List l : lists) {
                l.add(nums[i]);
                res.add(l);
            }
        }
        return res;
    }

    private int[] remove(int[] nums, int idx) {
        int[] newNums = new int[nums.length - 1];

        for (int i = 0, j = 0; i < nums.length; i++) {
            if (i != idx) {
                newNums[j++] = nums[i];
            }
        }
        return newNums;
    }

    /**
     * 三数之和
     *
     * @param nums
     * @param target
     * @return
     */
    private List<List<Integer>> threeSum(int[] nums, int target, int min) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 1) {
            return res;
        }
        Arrays.sort(nums);
        int i = 0;
        while (nums[i] < min) i++;
        for (; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                if (nums[l] + nums[r] < target - nums[i]) {
                    l++;
                    while (l + 1 < r && nums[l] == nums[l - 1]) l++;
                } else if (nums[l] + nums[r] > target - nums[i]) {
                    r--;
                    while (l + 1 < r && nums[r] == nums[r + 1]) r--;
                } else {
                    List<Integer> list = new ArrayList<>(3);
                    list.add(nums[i]);
                    list.add(nums[l]);
                    list.add(nums[r]);
                    res.add(list);
                    l++;
                    while (l + 1 < r && nums[l] == nums[l - 1]) l++;
                    r--;
                    while (l + 1 < r && nums[r] == nums[r + 1]) r--;
                }

            }
        }
        return res;
    }

    /**
     * 416. 分割等和子集
     *
     * @param nums
     * @return 就是判断是否一组元素之和等于总和的一半
     */
    int[][] f; // -1表示未搜索过 1 表示成功 0表示失败  f[i][n] 就表示在[0,i]区间内找到和为n的集合的结果

    public boolean canPartition(int[] nums) {
        int sum = 0;
        if (nums == null || nums.length < 1) {
            return false;
        }
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;

        }
        int target = sum / 2;
        // 求是否有子集之和等于sum的一半
        Arrays.sort(nums);
        f = new int[nums.length][target + 1];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < target + 1; j++) {
                f[i][j] = -1;
            }
        }
        // 暴搜
        return dfs(nums, nums.length - 1, target);
    }

    private boolean dfs(int[] nums, int pos, int target) {
        if (pos == -1) {
            return false;
        }
        if (target < 0) {
            return false;
        }
        if (target == 0) {
            return true;
        }
        if (f[pos][target] == 0) {
//            System.out.println(1);
            return false;
        } else if (f[pos][target] == 1) {
//            System.out.println(1);
            return true;
        }
        for (int i = pos; i >= 0; i--) {
            if (target > nums[i]) {
                if (i > 0 && f[i - 1][target - nums[i]] == 0) {
                    continue;
                }
                if (dfs(nums, i - 1, target - nums[i])) {
                    return true;
                }
            } else if (target == nums[i]) {
                return true;
            }

        }
        f[pos][target] = 0;
        return false;
    }

    public boolean isLongPressedName(String name, String typed) {
        if (name == null || typed == null) {
            return false;
        }
        int lengthOfName = name.length();
        int lengthOfTyped = typed.length();
        if (lengthOfName == 0) {
            return true;
        }
        if (lengthOfTyped == 0) {
            return false;
        }
        int i = 0, j = 0;
        while (j < lengthOfTyped) {
            if (i == lengthOfName) {
                if (typed.charAt(j) == name.charAt(i - 1)) {
                    j++;
                } else {
                    return false;
                }
            } else if (typed.charAt(j) == name.charAt(i)) {
                j++;
                i++;
            } else {
                if (i > 0 && typed.charAt(j) == name.charAt(i - 1)) {
                    j++;
                } else {
                    return false;
                }
            }
        }
        if (i == lengthOfName) {
            return true;
        } else {
            return false;
        }
    }

    public int islandPerimeter(int[][] grid) {
        int ans = 0;
        int m = grid.length;
        if (m < 1) {
            return ans;
        }
        int[][] steps = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
        int n = grid[0].length;
        int[][] flg = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int t = 0;
                    for (int k = 0; k < 4; k++) {
                        int y = i + steps[k][0];
                        int x = j + steps[k][1];
                        if (i == 1 && j == 2) {
                            System.out.println();
                        }
                        if (y >= 0 && y < m && x >= 0 && x < n) {
                            if (grid[y][x] == 0) {
                                ans++;
                                t++;
                            }
                        } else {
                            ans++;
                            t++;
                        }
                    }
                    flg[i][j] = t;

                }
            }

        }

        return ans;

    }

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set2 = new HashSet<Integer>();
        Arrays.stream(nums2).forEach(set2::add);
        return Arrays.stream(nums1).parallel().filter(set2::contains).distinct().toArray();
    }

    public boolean validMountainArray(int[] A) {
        if (A == null || A.length < 1) {
            return false;
        }
        int len = A.length;
        int count = 0;
        for (int i = 1; i < len - 1; i++) {
            if (count == 0) {
                if (A[i] <= A[i - 1]) {
                    return false;
                }
                if (A[i] > A[i - 1] && A[i] > A[i + 1]) {
                    count++;
                }
            }
            if (count > 0 && A[i] <= A[i + 1]) {
                return false;
            }
        }
        if (count == 1) {
            return true;
        } else {
            return false;
        }
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        int m = intervals.length;
        if (m < 1) {
            int[][] res = new int[1][2];
            res[0][0] = newInterval[0];
            res[0][1] = newInterval[1];
            return res;
        }
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int i = 0; i < intervals.length; i++) {
            min = min > intervals[i][0] ? intervals[i][0] : min;
            max = max < intervals[i][1] ? intervals[i][1] : max;
        }
        min = min > newInterval[0] ? newInterval[0] : min;
        max = max < newInterval[1] ? newInterval[1] : max;
        int[] flg = new int[max - min + 1];
        for (int i = 0; i < intervals.length; i++) {
            if(intervals[i][0] == intervals[i][1]){
                flg[intervals[i][0] - min] = -1;
                continue;
            }
            for (int j = intervals[i][0] - min; j < intervals[i][1] - min; j++) {
                flg[j] = 1;
            }
        }
        if(newInterval[0] == newInterval[1] && flg[newInterval[0] - min] == 0){
            if(newInterval[0] - min - 1 < 0 || flg[newInterval[0] - min - 1] != 1 ){
                flg[newInterval[0] - min] = -1;
            }
        }
        else{
            for (int j = newInterval[0] - min; j < newInterval[1] - min; j++) {
                flg[j] = 1;
            }
        }
        Vector<Integer> vec = new Vector<>();
        int last = 0;
        for (int i = 0; i < max - min + 1; i++) {
            if(last == 0 && flg[i] == -1){
                vec.add(i + min);
                vec.add(i + min);
                continue;
            }
            if (last == 0 && flg[i] == 1) {
                vec.add(i + min);
                last = 1;
            } else if (last == 1 && flg[i] != 1) {
                vec.add(i + min);
                last = 0;
            }
        }
        if (vec.size() % 2 != 0) {
            vec.add(max);
        }
//        System.out.println(vec);
        int[][] res = new int[vec.size() / 2][2];
        for (int i = 0; i < vec.size() / 2; i++) {
            res[i][0] = vec.get(2 * i);
            res[i][1] = vec.get(2 * i + 1);
//            System.out.println(res[i][0] + " " + res[i][1]);
        }
        return res;
    }


    /*
     *     1
     *   0    4
     * -2   3
     * */

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {1, 2, 2, 1}, nums2 = {2, 2};
        int[][] intervals = {{1,2},{3,5},{6,7},{8,10},{12,16}};
        int[] newInterval = {4, 8};
        System.out.println(solution.insert(intervals, newInterval));

    }
}
