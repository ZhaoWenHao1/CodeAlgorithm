package leetcode;

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
        return dfsOfLongestSubstring(0, s.length() - 1,s,k);
    }

    private int dfsOfLongestSubstring(int l, int r, String s, int k) {
        int[] count = new int[26];
        for(int i = l;i <= r;i++){
            count[s.charAt(i) - 'a']++;
        }
        int split = -1;
        for(int i = 0;i < 26;i++){
            if(count[i] > 0 && count[i] < k){
                split = i;
                break;
            }
        }
        if(split == -1){
            return r - l + 1;
        }
        int i = l;
        int res = 0;
        while(i < r){
            while(i < r && s.charAt(i) - 'a' == split){
                i++;
            }
            int start = i;
            while(i <= r && s.charAt(i) - 'a' != split){
                i++;
            }
            int ans = dfsOfLongestSubstring(start, i-1, s, k);
            res = Math.max(res, ans);
        }
        return res;
    }


    public static void main(String[] args) {
        Main main = new Main();

        System.out.println(main.longestSubstring("aaabbb", 3));
    }
}
