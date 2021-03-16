package leetcode;

import sun.security.util.PendingException;

import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author happyzhao
 * @data 2020/3/15 10:22
 * @type PACKAGE_NAME
 * @question
 */
class Node {
    int n;
    Node next;
    Node left;
    Node right;

    Node(int v) {
        this.n = v;
    }
}

public class Main {
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        class Point implements Comparable<Point> {
            int x;
            int y;
            int dis;

            public Point(int x, int y, int dis) {
                this.x = x;
                this.y = y;
                this.dis = dis;
            }

            @Override
            public int compareTo(Point o) {
//                if(this.dis < o.dis){
////                    return -1;
////                }
////                else if(this.dis > o.dis){
////                    return 1;
////                }
////                else{
////                    return 0;
////                }
                return this.dis < o.dis ? -1 : this.dis - o.dis;
            }
        }
        List<Point> list = new ArrayList<>(R * C);
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                list.add(new Point(i, j, Math.abs(i - r0) + Math.abs(j - c0)));
            }
        }
        Collections.sort(list);
        int[][] res = new int[R * C][2];
        for (int i = 0; i < list.size(); i++) {
            res[i][0] = list.get(i).x;
            res[i][1] = list.get(i).y;
            System.out.println(res[i][0] + " " + res[i][1]);
        }
        return res;
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        for (int i = 0; i < gas.length; ) {
            int sum = gas[i % gas.length];
            for (int j = 0; j < gas.length; j++) {
                // 出发
                sum -= cost[(i + j) % gas.length];
                if (sum < 0) {
                    i = i + j + 1;
                    break;
                }
                // 加油
                sum += gas[(i + j + 1) % gas.length];
            }
            if (sum > 0) {
                return i;
            }
        }
        return -1;
    }

    public int pivotIndex(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        if (sum[nums.length - 1] - sum[0] == 0) {
            return 0;
        }
        for (int i = 1; i < nums.length - 1; i++) {
            if (sum[i - 1] == sum[nums.length - 1] - sum[i]) {
                return i;
            }
        }
        if (nums.length - 2 >= 0 && sum[nums.length - 2] == 0) {
            return nums.length - 1;
        }
        return -1;
    }

    public int[][] reconstructQueue2(int[][] people) {

        Arrays.sort(people, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);
        for (int i = 0; i < people.length; i++) {
            System.out.println(people[i][0] + " " + people[i][1]);
        }
        System.out.println();
        List<int[]> list = new LinkedList<>();
        for (int[] p : people) list.add(p[1], p);
        return list.toArray(new int[0][2]);
    }

    public int[][] reconstructQueue(int[][] people) {
        class Person {
            int height;
            int num;

            public Person(int height, int num) {
                this.height = height;
                this.num = num;
            }

            @Override
            public String toString() {
                return height + " " + num + " ";
            }
        }
        class PersonComparator implements Comparator {
            @Override
            public int compare(Object o1, Object o2) {
                Person p1 = (Person) o1;
                Person p2 = (Person) o2;
                if (p1.height < p2.height) {
                    return -1;
                } else if (p1.height > p2.height) {
                    return 1;
                } else {
                    if (p1.num < p2.num) {
                        return -1;
                    }
                    return p1.num > p2.num ? 1 : 0;
                }
            }
        }
        Vector<Person> vector = new Vector<>(people.length);
        for (int i = 0; i < people.length; i++) {
            vector.add(new Person(people[i][0], people[i][1]));
        }
        Collections.sort(vector, new PersonComparator());
        vector.stream().forEach(System.out::println);
        int[][] res = new int[people.length][2];
        for (int i = 0; i < people.length; i++) {
            res[i][0] = -1;
            res[i][1] = -1;
        }
        for (int i = 0; i < vector.size(); i++) {
            Person person = vector.get(i);
            for (int j = 0, k = 0; j < res.length; j++) {
                if (res[j][1] == -1) {
                    k++;
                } else {
                    if (res[j][0] >= person.height) {
                        k++;
                    }
                }
                if (k == person.num + 1) {
                    res[j][0] = person.height;
                    res[j][1] = person.num;
                    break;
                }
            }
        }
        return res;
    }

    int[][] flg;

    public int numRollsToTarget(int d, int f, int target) {
        flg = new int[d + 1][target + 1];
        for (int i = 0; i < d + 1; i++) {
            for (int j = 0; j < target + 1; j++) {
                flg[i][j] = -1;
            }
        }
        return numRollsToTargetPlus(d, f, target);
    }

    public int numRollsToTargetPlus(int d, int f, int target) {
        int dividor = 1000000007;
        if (d == 1) {
            if (target > 0 && f >= target) {
                return 1;
            } else {
                return 0;
            }
        }
        long sum = 0;
        for (int i = 1; i <= f && i < target; i++) {
            if (flg[d - 1][target - i] == -1) {
                flg[d - 1][target - i] = numRollsToTargetPlus(d - 1, f, target - i);
            }
            sum += flg[d - 1][target - i];
        }
        return (int) (sum % dividor);
    }

    String[] strings;

    public int[] printNumbers(int n) {
        List<String> list;
        strings = new String[10];
        for (int i = 0; i < 10; i++) {
            strings[i] = String.valueOf(i);
        }
        StringBuilder sb = new StringBuilder("");
        list = printNumbersDfs(sb, n);
        return list.stream().mapToInt(Integer::parseInt).filter(num -> num > 0).toArray();

    }

    private List<String> printNumbersDfs(StringBuilder sb, int n) {
        List<String> list = new ArrayList<>();
        if (n == 1) {
            for (int i = 0; i < strings.length; i++) {
                list.add(sb.append(strings[i]).toString());
                sb.deleteCharAt(sb.length() - 1);

            }
        } else {
            for (int i = 0; i < strings.length; i++) {
                list.addAll(printNumbersDfs(sb.append(strings[i]), n - 1));
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        return list;

    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode p1 = l1, p2 = l2, p = head;
        while (p1 != null && p2 != null) {
            if (p1.val <= p2.val) {
                p.next = p1;
                p1 = p1.next;
            } else {
                p.next = p2;
                p2 = p2.next;
            }
            p = p.next;
        }
        if (p1 != null) {
            p.next = p1;
        }
        if (p2 != null) {
            p.next = p2;
        }
        return head.next;

    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode quick = head, slow = null;
        while (quick != null) {
            if (quick.next != null) {
                quick = quick.next.next;
            } else {
                break;
            }
            if (slow == null) {
                slow = head;
            } else {
                slow = slow.next;
            }
        }
        ListNode l1 = head, l2 = slow.next;
        slow.next = null;
        l1 = sortList(l1);
        l2 = sortList(l2);
        return mergeTwoLists(l1, l2);
    }

    private void printListNode(ListNode head) {
        ListNode p = head;
        while (p != null) {
            System.out.print(p.val);
            if (p.next != null) {
                System.out.print("->");
            }
            p = p.next;
        }
        System.out.println();
    }

    public int numMagicSquaresInside(int[][] grid) {
        if (grid == null || grid.length < 1) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        if (m < 3 || n < 3) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < m - 2; i++) {
            for (int j = 0; j < n - 2; j++) {
                if (checkSquaresInside(grid, i, j)) {
                    sum++;
                }
            }
        }
        return sum;
    }

    private boolean checkSquaresInside(int[][] grid, int m, int n) {
        int[] flg = new int[10];
        for (int i = 0; i < 10; i++) {
            flg[i] = -1;
        }
        for (int i = m; i <= m + 2; i++) {
            for (int j = n; j <= n + 2; j++) {
                if (grid[i][j] < 1 || grid[i][j] > 9) {
                    return false;
                }
//                System.out.println(grid[i][j] + " " + flg[grid[i][j]]);
                if (flg[grid[i][j]] == -1) {
                    flg[grid[i][j]] = 1;
                } else {
                    return false;
                }
            }
        }
        for (int i = 1; i < 10; i++) {
            if (flg[i] != 1) {
                return false;
            }
        }
        int sum = grid[m][n] + grid[m][n + 1] + grid[m][n + 2];
        for (int i = 1; i <= 2; i++) {
            if (sum != grid[m + i][n] + grid[m + i][n + 1] + grid[m + i][n + 2]) {
                return false;
            }
        }
        for (int i = 0; i <= 2; i++) {
            if (sum != grid[m][n + i] + grid[m + 1][n + i] + grid[m + 2][n + i]) {
                return false;
            }
        }
        if (sum != grid[m][n] + grid[m + 1][n + 1] + grid[m + 2][n + 2]) {
            return false;
        }
        if (sum != grid[m][n + 2] + grid[m + 1][n + 1] + grid[m + 2][n]) {
            return false;
        }
        return true;
    }

    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int sum = 0;
        for (int i = 0; i < arr.length - 2; i++) {
            for (int j = i + 1; j < arr.length - 1; j++) {
                for (int k = j + 1; k < arr.length; k++) {
                    if (Math.abs(arr[i] - arr[j]) <= a && Math.abs(arr[j] - arr[k]) <= b && Math.abs(arr[i] - arr[k]) <= c) {
                        sum++;
                    }
                }
            }
        }
        return sum;
    }

    public int longestSubstring(String s, int k) {
        return dfsOfLongestSubstring(0, s.length() - 1, s, k);
    }

    private int dfsOfLongestSubstring(int l, int r, String s, int k) {
        int[] count = new int[26];
        for (int i = l; i <= r; i++) {
            count[s.charAt(i) - 'a']++;
        }
        int split = -1;
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0 && count[i] < k) {
                split = i;
                break;
            }
        }
        if (split == -1) {
            return r - l + 1;
        }
        int i = l;
        int res = 0;
        while (i < r) {
            while (i < r && s.charAt(i) - 'a' == split) {
                i++;
            }
            int start = i;
            while (i <= r && s.charAt(i) - 'a' != split) {
                i++;
            }
            int ans = dfsOfLongestSubstring(start, i - 1, s, k);
            res = Math.max(res, ans);
        }
        return res;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        ListNode p = headA;
        while (p != null) {
            set.add(p);
            p = p.next;
        }
        p = headB;
        while (p != null) {
            if (set.contains(p)) {
                return p;
            }
            p = p.next;
        }
        return null;
    }

    public int hammingWeight(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & 1) == 1) {
                count++;
            }
            n = (n >> 1);
        }
        return count;
    }

    public int[] countBits(int num) {
        int[] f = new int[num + 1];
        f[0] = 0;
        int i = 0, b = 1;
        while (b <= num) {
            while (i < b && i + b <= num) {
                f[i + b] = f[i] + 1;
                i++;
            }
            i = 0;
            b = b << 1;
        }
        return f;
    }

    private int popCount(int x) {
        int count = 0;
        for (; x != 0; count++) {
            x = x & (x - 1); // 将二进制最后一个1变为0
        }
        return count;
    }

    // 子集
    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < (1 << n); i++) {
            List<Integer> res = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    res.add(nums[j]);
                }
            }
            ans.add(res);
        }
        return ans;
    }

    public int numTrees(int n) {
        int[] f = new int[Math.max(n + 1, 3)];
        f[0] = 1;
        f[1] = 1;
        f[2] = 2;
        for (int i = 3; i <= n; i++) {
            int sum = 0;
            for (int j = 0; j <= (i - 1) / 2; j++) {
                sum += 2 * f[j] * f[i - 1 - j];
            }
            if ((i & 1) == 1) {
                sum -= f[(i - 1) / 2] * f[(i - 1) / 2];
            }
            f[i] = sum;
        }
        return f[n];
    }

    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length <= 0) {
            return 0;
        }
        int[][] cost = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int min = Integer.MAX_VALUE;
                if (j > 0) {
                    min = Math.min(min, cost[i][j - 1] + grid[i][j]);
                }
                if (i > 0) {
                    min = Math.min(min, cost[i - 1][j] + grid[i][j]);
                }
                if (i == 0 && j == 0) {
                    min = grid[i][j];
                }
                cost[i][j] = min;
            }
        }

        return cost[grid.length - 1][grid[0].length - 1];
    }

    public int countSubstrings(String s) {
        int ans = 0;
        boolean[][] f = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            f[i][i] = true;
            if (i < s.length() - 1 && s.charAt(i) == s.charAt(i + 1)) {
                f[i][i + 1] = true;
                ans++;
            }
        }
        for (int len = 3; len <= s.length(); len++) {
            for (int i = 0; i + len - 1 < s.length(); i++) {
                int j = i + len - 1;
                f[i][j] = f[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
                if (f[i][j]) ans++;
            }
        }
        return ans + s.length();
    }

    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        int[] f = new int[amount + 1];
        Arrays.fill(f, amount + 1);
        f[0] = 0;
        for (int i = 0; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    f[i] = Math.min(f[i], f[i - coins[j]] + 1);
                }
            }
        }
        return f[amount] <= amount ? f[amount] : -1;
    }

    public int numSquares(int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            list.add(i * i);
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 0; i <= n; i++) {
            dp[i] = i;
            for (int m : list) {
                if (m <= i) {
                    dp[i] = Math.min(dp[i], dp[i - m] + 1);
                } else {
                    break;
                }
            }
        }
        return dp[n];
    }

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        long[][] dp = new long[3][prices.length]; // 0 冷静期 1 买入  2 卖出  dp[i][j] 第[j]天结束后状态为i时的最大收益
        dp[0][0] = 0;
        dp[1][0] = -prices[0];
        dp[2][0] = Integer.MIN_VALUE;
        for (int i = 1; i < prices.length; i++) {
            dp[0][i] = Math.max(dp[2][i - 1], dp[0][i - 1]);
            dp[1][i] = Math.max(dp[0][i - 1] - prices[i], dp[1][i - 1]);
            dp[2][i] = dp[1][i - 1] + prices[i];
        }
        return (int) Math.max(dp[0][prices.length - 1], dp[2][prices.length - 1]);

    }

    private int dfsOfCoinChange(int[] coins, int target) {
        if (target == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int i = coins.length - 1; i >= 0; i--) {

            if (coins[i] > target) {
                continue;
            }
            int ans = dfsOfCoinChange(coins, target - coins[i]);
            if (ans >= 0) {
                return ans + 1;
            }
        }
        return min != Integer.MAX_VALUE ? min + 1 : -1;
    }

    class DicTreeNode {
        DicTreeNode[] child;
        boolean endOfWord; // 上面的字符是一个单词，不包括当前节点

        public DicTreeNode() {
            endOfWord = false;
            child = new DicTreeNode[26];
        }

    }

    // 字典树
    public boolean wordBreakDic(String s, List<String> wordDict) {
        DicTreeNode root = new DicTreeNode();
        wordDict.forEach(word -> addWord(word, root));
        return wordBreak(s, 0, root);
    }


    // 判断s[pos, n]是都为若干个单词
    private boolean wordBreak(String s, int pos, DicTreeNode root) {
        DicTreeNode p = root;
        for (int i = pos; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            if (s.charAt(i) == 'd') {
//                System.out.println(1);
            }
            if (p.endOfWord) {
                if (wordBreak(s, i, root)) {
                    return true;
                }
            }
            if (p.child[idx] == null) {
                return false;
            } else {
                p = p.child[idx];
            }
        }
        return p.endOfWord;
    }

    private void addWord(String word, DicTreeNode root) {
        DicTreeNode p = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (p.child[idx] == null) {
                p.child[idx] = new DicTreeNode();
            }
            p = p.child[idx];
        }
        p.endOfWord = true;
    }

    Set<String> setSucc, setFail;

    public boolean wordBreakDivide(String s, List<String> wordDict) {
        setSucc = new HashSet<>(wordDict);
        setFail = new HashSet<>();
        return isWord(s);


    }

    private boolean isWord(String str) {
        if (str.length() == 1) {
            return setSucc.contains(str);
        }
        if (setSucc.contains(str)) {
            return true;
        }
        if (setFail.contains(str)) {
            return false;
        }
        boolean ans = false;
        for (int i = 1; i < str.length(); i++) {
            if (setSucc.contains(str.substring(0, i))) {
                ans = isWord(str.substring(i));
                if (ans) {
                    break;
                }
            }
        }
        if (ans) {
            setSucc.add(str);
        } else {
            setFail.add(str);
        }
        return ans;
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length()]; // dp[i] 表示 [0,i]是否合法
        dp[0] = set.contains(s.substring(0, 1));
        for (int i = 1; i < s.length(); i++) {
            if (set.contains(s.substring(0, i + 1))) {
                dp[i] = true;
                continue;
            }
            for (int j = 1; j <= i; j++) {
                if (dp[j - 1] && set.contains(s.substring(j, i + 1))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length() - 1];
    }

    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null) {
            return 0;
        }
        if (envelopes.length < 2) {
            return envelopes.length;
        }
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            } else {
                return b[1] - a[1];
            }
        });
        int[] dp = new int[envelopes.length]; // dp[i]表示第i个信封最多能有几层套娃
        dp[0] = 1;
        for (int i = 1; i < envelopes.length; i++) {
            int max = 1;
            for (int j = 0; j < i; j++) {
                if (envelopes[j][0] < envelopes[i][0]) {
                    max = Math.max(max, dp[j] + 1);
                }
            }
            dp[i] = max;
        }
        int ans = 0;
        for (int n : dp) {
            ans = Math.max(ans, n);
        }
        return ans;
    }

    public int[] nextGreaterElements(int[] nums) {
        int[] ans = new int[nums.length];

        int n = nums.length;
        Stack<Integer> stack = new Stack<>(); // 维护一个单调递减栈  （非增）
        for (int i = 0; i < n * 2; i++) {

            if (stack.isEmpty() || nums[i % n] <= nums[stack.peek() % n]) {
                stack.push(i);
            } else {
                int idx = stack.pop();
                if (idx < nums.length) {
                    ans[idx] = nums[i % n];
                }
                i--;
            }
        }
        while (!stack.isEmpty()) {
            int idx = stack.pop();
            if (idx < nums.length) {
                ans[idx] = -1;
            }
        }
        return ans;
    }

    public int partition(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()]; // dp[i][j]表示[i，j]是否为回文子串
        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = true;
            if (i < dp.length - 1 && s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
            }
        }
        for (int len = 3; len <= dp.length; len++) {
            for (int i = 0; i + len <= dp.length; i++) {
                int j = i + len - 1;
                if (dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = true;
                }
            }
        }
        memDfs = new int[s.length()][s.length()];
        for (int[] mem : memDfs) {
            Arrays.fill(mem, -1);
        }
        return partition(s, dp, 0, s.length() - 1);
//        return partition.stream().map(List::size).min((a, b) -> a-b).get() - 1;
    }

    int[][] memDfs;

    // s[start,end]得到的所有回文子串
    private int partition(String s, boolean[][] dp, int start, int end) {
        if (memDfs[start][end] != -1) {
            return memDfs[start][end];
        }
        int ans = Integer.MAX_VALUE;
        if (dp[start][end]) {
//            ans = 0;
            return 0;
        }
        if (start != end) {
            for (int i = start; i < end; i++) {
                if (dp[start][i]) {
                    int rightRes = partition(s, dp, i + 1, end);
                    ans = Math.min(ans, 1 + rightRes);
                }
            }
        }
        memDfs[start][end] = ans;
        return ans;
    }

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length]; // dp[i] 偷[0,i]的最大收益  
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
        }
        return dp[nums.length - 1];
    }

    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }
        int[][] g = new int[routes.length][routes.length];
        for (int[] gn : g) {
            Arrays.fill(gn, Integer.MAX_VALUE);
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                if (!map.containsKey(routes[i][j])) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    map.put(routes[i][j], list);
                } else {
                    for (Integer n : map.get(routes[i][j])) {
                        g[n][i] = 1;
                        g[i][n] = 1;
                    }
                    final int ii = i;
                    map.compute(routes[i][j], (k, v) -> {
                        v.add(ii);
                        return v;
                    });
                }
            }
        }
        int min = Integer.MAX_VALUE;
        List<Integer> sourceList = map.get(source);
        List<Integer> targetList = map.get(target);
        for (Integer sourceNode : sourceList) {
            long[] dist = new long[routes.length];
            boolean[] visit = new boolean[routes.length];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[sourceNode] = 0;
            int n = routes.length;
            while (n-- > 0) {
                long minDist = Integer.MAX_VALUE;
                int minNode = -1;
                for (int i = 0; i < dist.length; i++) {
                    if (!visit[i] && dist[i] < minDist) {
                        minDist = dist[i];
                        minNode = i;
                    }
                }
                if (minNode == -1) {
                    break;
                }
                visit[minNode] = true;
                for (int j = 0; j < g[minNode].length; j++) {
                    if (g[minNode][j] == 1) {
                        dist[j] = Math.min(dist[j], dist[minNode] + 1);
                    }
                }
            }
            for (int i = 0; i < dist.length; i++) {
                if (targetList.contains(i)) {
                    min = Math.min(min, (int) dist[i]);
                }
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min + 1;
    }

    Map[] maps;

    public int numOfWays(int n) {
        int[][] table = new int[n][3];
        Map<String, List<String>> map = new HashMap<>();
        List<String> ans = new ArrayList<>();
        dfsOfColor("", ans);
        map.put("", ans);
        int count = 0;
        for (String s : ans) {
            List<String> list = new ArrayList<>();
            dfsOfColor("", s, list);
            map.put(s, list);
            count += list.size();
        }
//        System.out.println(count);
        maps = new Map[n + 1];
        for (int i = 0; i < n; i++) {
            maps[i] = new HashMap<String, Long>();
        }
        long sum = 0;
        for (String s : ans) {
            sum = sum % 1000000007 + numOfWayDfs(s, n - 1, map);
        }

        return (int) (sum % 1000000007);
    }

    private void dfsOfColor(String s, List<String> list) {
        if (s.length() == 3) {
            list.add(new String(s));
            return;
        }
        if (s.length() == 0) {
            for (int i = 0; i < 3; i++) {
                dfsOfColor(String.valueOf(i), list);
            }
        } else {
            for (int i = 0; i < 3; i++) {
                int n = s.length();
                if (!String.valueOf(i).equals(s.substring(n - 1))) {
                    dfsOfColor(s + i, list);
                }
            }
        }
    }

    private void dfsOfColor(String s, String last, List<String> list) {
        if (s.length() == 3) {
            list.add(new String(s));
            return;
        }
        if (s.length() == 0) {
            for (int i = 0; i < 3; i++) {
                if (!String.valueOf(i).equals(last.substring(0, 1))) {
                    dfsOfColor(String.valueOf(i), last, list);
                }
            }
        } else {
            int n = s.length();
            for (int i = 0; i < 3; i++) {
                if (!String.valueOf(i).equals(s.substring(n - 1))
                        && !String.valueOf(i).equals(last.substring(n, n + 1))) {
                    dfsOfColor(s + i, last, list);
                }
            }
        }
    }

    private long numOfWayDfs(String s, int n, Map<String, List<String>> map) {
        if (n == 1) {
            return map.get(s).size();
        }
        if (n == 0) {
            return 1;
        }
        if (maps[n].containsKey(s)) {
            return (long) maps[n].get(s);
        }
        long sum = 0;
        for (String s1 : map.get(s)) {
            sum = sum % 1000000007 + numOfWayDfs(s1, n - 1, map);
        }
        sum = sum % 1000000007;
        maps[n].put(s, sum);
        return sum;
    }


    public int minElements(int[] nums, int limit, int goal) {
        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        long last = Math.abs(sum - goal);
        if (last == 0) {
            return 0;
        }
        int ans = (int) (last / Math.abs(limit));
        if (last % Math.abs(limit) != 0) {
            ans++;
        }
        return ans;
    }

    public int minCut(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()]; // dp[i][j]表示[i，j]是否为回文子串
        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = true;
            if (i < dp.length - 1 && s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
            }
        }
        for (int len = 3; len <= dp.length; len++) {
            for (int i = 0; i + len <= dp.length; i++) {
                int j = i + len - 1;
                if (dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = true;
                }
            }
        }
        int[] f = new int[s.length()];
        Arrays.fill(f, Integer.MAX_VALUE);
        for (int i = 0; i < s.length(); i++) {
            if (dp[0][i]) {
                f[i] = 0;
            } else {
                for (int j = i; j > 0; j--) {
                    if (dp[j][i]) {
                        f[i] = Math.min(f[i], f[j - 1] + 1);
                    }
                }
            }
        }
        return f[s.length() - 1];
    }

    public String removeDuplicates(String S) {
        char[] s = S.toCharArray();
        int top = -1;
        for (int i = 0; i < s.length; i++) {
            if (top == -1 || s[top] != s[i]) {
                s[++top] = s[i];
            } else {
                --top;
            }
        }
        return String.valueOf(s, 0, top + 1);
    }

    public int findKthPositive(int[] arr, int k) {
        int pos = 0;
        int cur = 1;
        for (int i = 0; i < k; ) {
            if (pos < arr.length && arr[pos] == cur) {
                pos++;
            } else {
                i++;
            }
            cur++;
        }
        return cur - 1;
    }

    int mask = 1000000007;
    int[][][] dp;
    int minPro;

    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        dp = new int[group.length + 1][n + 1][minProfit + 1];
        minPro = minProfit;
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        return dfsOfProfitable(n, 0, group, profit, 0);
    }

    private int dfsOfProfitable(int last, int pro, int[] group, int[] profit, int pos) {
        if (dp[pos][last][pro] != -1) {
            return dp[pos][last][pro];
        }
        long ans = 0;
        if (pos == group.length) {
            if (pro <= 0) {
                return 1;
            } else {
                return 0;
            }
        }
        if (group[pos] <= last) {
            ans = dfsOfProfitable(last - group[pos], Math.min(pro + profit[pos], minPro), group, profit, pos + 1);
        }
        ans = ans % mask + dfsOfProfitable(last, pro, group, profit, pos + 1);
        return (int) (ans % mask);
    }

    public int rob1(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums[0];
        }
        int[] dp = new int[nums.length]; // dp[i]偷前i家的最大收益
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[nums.length - 1];
    }

    public int rob2(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums[0];
        }
        int[] dp = new int[nums.length]; // dp[i]偷前i家的最大收益
        // 偷第一家
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length - 1; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        int ans = dp[nums.length - 2];
        // 不偷第一家
        dp[0] = 0;
        dp[1] = nums[1];
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return Math.max(ans, dp[nums.length - 1]);
    }

    public int calculate(String s) {
        String[] strings = s.split("[()+-]");
        Stack<Integer> numStk = new Stack<>();
        Stack<Character> opStk = new Stack<>();
        int sum = -1;
        char[] sc = s.toCharArray();
        for (int i = 0; i < sc.length; i++) {
            char ch = sc[i];
            if (ch >= '0' && ch <= '9') {
                if (sum == -1) {
                    sum = ch - '0';
                } else {
                    sum = sum * 10 + ch - '0';
                }
            } else {
                if (sum != -1) {
                    numStk.push(sum);
                    sum = -1;
                }
            }
            if (ch == '(') {
                opStk.push(ch);
            } else if (ch == ')') {
                while (opStk.peek() != '(') {
                    int n1 = numStk.pop();
                    int n2 = numStk.pop();
                    char op = opStk.pop();
                    int res = op(n2, n1, op);
                    numStk.push(res);
                }
                opStk.pop();
            } else if (ch == '-' || ch == '+' || ch == '*' || ch == '/') {
                if (ch == '-' && (i == 0 || sc[i - 1] == '(')) {
                    numStk.push(0);
                }
                if (ch == '-' || ch == '+') {
                    while (!opStk.isEmpty() && opStk.peek() != '(') {
                        int n1 = numStk.pop();
                        int n2 = numStk.pop();
                        char op = opStk.pop();
                        int res = op(n2, n1, op);
                        numStk.push(res);
                    }
                }
                if (ch == '*' || ch == '/') {
                    while (!opStk.isEmpty() && (opStk.peek() == '*' || opStk.peek() == '/')) {
                        int n1 = numStk.pop();
                        int n2 = numStk.pop();
                        char op = opStk.pop();
                        int res = op(n2, n1, op);
                        numStk.push(res);
                    }
                }

                opStk.push(ch);
            }
        }
        if (sum != -1) {
            numStk.push(sum);
        }
        while (!opStk.isEmpty()) {
            int n1 = numStk.pop();
            int n2 = numStk.pop();
            char op = opStk.pop();
            int res = op(n2, n1, op);
            numStk.push(res);
        }
        return numStk.pop();

    }

    private int op(int n1, int n2, char op) {
        if (op == '+') {
            return n1 + n2;
        }
        if (op == '-') {
            return n1 - n2;
        }
        if (op == '*') {
            return n1 * n2;
        }
        if (op == '/') {
            return n1 / n2;
        }
        return 0;
    }

    public int maxSizeSlices(int[] slices) {
        int n = slices.length;
        int[][] dp = new int[n][n / 3 + 1];
        dp[0][1] = slices[0];
        dp[1][1] = Math.max(slices[0], slices[1]);
        for (int i = 2; i < n - 1; i++) {
            for (int j = 1; j <= n / 3; j++) {
                dp[i][j] = Math.max(dp[i - 2][j - 1] + slices[i], dp[i - 1][j]);
            }
        }
        int ans = dp[n - 2][n / 3];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], 0);
        }
        dp[0][1] = Integer.MIN_VALUE;
        dp[1][1] = slices[1];
        for (int i = 2; i < n; i++) {
            for (int j = 1; j <= n / 3; j++) {
                dp[i][j] = Math.max(dp[i - 2][j - 1] + slices[i], dp[i - 1][j]);
            }
        }
        int ans2 = dp[n - 1][n / 3];
        return Math.max(ans, ans2);
    }


    public boolean isValidSerialization(String preorder) {
        Stack<Integer> stk = new Stack<>();
        stk.push(1);
        int i = 0;
        while (i < preorder.length()) {
            if (stk.isEmpty()) {
                return false;
            }
            if (preorder.charAt(i) == ',') {
                i++;
            } else if (preorder.charAt(i) == '#') {
                int top = stk.pop();
                if (--top > 0) {
                    stk.push(top);
                }
                i++;
            } else {
                int num = 0;
                while (i < preorder.length() && Character.isDigit(preorder.charAt(i))) {
                    i++;
                }
                int top = stk.pop();
                if (--top > 0) {
                    stk.push(top);
                }
                stk.push(2);
            }
        }
        return stk.isEmpty();
    }

    Map<TreeNode, Integer>[] mapTrees;

    public int rob(TreeNode root) {
        if(root == null){
            return 0;
        }
        // 选当前节点
        int ans = root.val;
        if(root.left!=null){
            if(root.left.left != null){
                ans += rob(root.left.left);
            }
            if(root.left.right != null){
                ans += rob(root.left.right);
            }
        }
        if(root.right != null){
            if(root.right.left != null){
                ans += rob(root.right.left);
            }
            if(root.right.right != null){
                ans += rob(root.right.right);
            }
        }
        int max = ans;
        // 不选当前节点
        ans = 0;
        if(root.left != null){
            ans += rob(root.left);
        }
        if(root.right != null){
            ans += rob(root.right);
        }
        return Math.max(max, ans);
    }

    public int findTargetSumWays(int[] nums, int S) {
        int max = 0;
        for(int i = 0;i < nums.length;i++){
            max += nums[i] > 0 ? nums[i] : -nums[i];
        }
        if(S > max || S < -max){
            return 0;
        }
        int[][] dp = new int[nums.length][2 * max + 1];
        dp[0][max + nums[0]] = 1;
        dp[0][max - nums[0]]++;
        for(int i = 1;i < nums.length;i++){
            for(int j = 0;j <= 2 * max;j++){
                int sum = 0;
                if(j - nums[i] >= 0){
                    sum += dp[i-1][j-nums[i]];
                }
                if(j + nums[i] <= 2 * max){
                    sum += dp[i-1][j+nums[i]];
                }
                dp[i][j] = sum;
            }
        }
        return dp[nums.length-1][max + S];
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        int m = matrix.length;
        if(m <= 0){
            return ans;
        }
        int n = matrix[0].length;
        int[][] steps = {{0,1},{1,0},{0,-1},{-1,0}};
        int sum = m * n;
        int flg = 0;
        int idx = 0, idy = 0;
//        ans.add(matrix[idx][idy]);
        int start = 0;
        int time = m%2 == 0 ? (m+1)/2 : m/2;
        while(idx < time){
            // 右
            for(int i = idx,j = idy;j < n - idy;j++){
                ans.add(matrix[i][j]);
            }
            // 下
            for(int i = idx+1,j = n - idy - 1;i < m - idx;i++){
                ans.add(matrix[i][j]);
            }
            if(idx != m - idx - 1){
                // 左
                for(int i = m - idx - 1, j = n - idy - 2;j >= idy;j--){
                    ans.add(matrix[i][j]);
                }
            }
            if(idy != n - idy - 1){
                // 上
                for(int i = m - idx - 2, j = idy;i > idx;i--){
                    ans.add(matrix[i][j]);
                }
            }
            
            idx++;
            idy++;
        }
        
      
        
        
//        
//        while(sum-- > 0){
//            if(idx + steps[flg][0] >= 0 && idx + steps[flg][0] < m && idy + steps[flg][1] >= 0 && idy + steps[flg][1] < n){
//                idx += steps[flg][0];
//                idy += steps[flg][1];
//            }else{
//                flg = (flg + 1) % 4;
//                idx += steps[flg][0];
//                idy += steps[flg][1];
//            }
//            ans.add(matrix[idx][idy]);
//        }
        return ans;
    }

    public int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];
        boolean[][] vis = new boolean[n][n];
        int[][] steps = {{0,1},{1,0},{0,-1},{-1,0}};
        int total = n * n;
        int flag = 0;
        int idx = 0, idy = -1;
        for(int i = 1;i <= total;i++){
            if(idx + steps[flag][0] >=0 && idx + steps[flag][0] < n 
                    && idy + steps[flag][1] >= 0 && idy + steps[flag][1] < n
                    && !vis[idx+steps[flag][0]][idy+steps[flag][1]]){
                idx += steps[flag][0];
                idy += steps[flag][1];
                ans[idx][idy] = i;
                vis[idx][idy] = true;
            }else{
                flag = (flag + 1)%4;
                idx += steps[flag][0];
                idy += steps[flag][1];
                ans[idx][idy] = i;
                vis[idx][idy] = true;
            }
        }
        return ans;
    }



    public static void main(String[] args) {
        Main main = new Main();
        int[][] matrix = {{7},{9},{6}};
        int[][] ans = main.generateMatrix(4);
        for(int i = 0;i < ans.length;i++){
            for(int j = 0;j < ans[i].length;j++){
                System.out.print(ans[i][j] + " \t");
            }
            System.out.println();
        }
//        System.out.println(main.spiralOrder(matrix));
//        System.out.println(main.minElements(nums, 3, -4));
//        System.out.println(main.numBusesToDestination(routes, 0, 10000));
//        System.out.println(main.profitableSchemes(10, 5, group, profit));
    }
}
