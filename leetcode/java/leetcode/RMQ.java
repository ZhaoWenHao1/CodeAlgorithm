package leetcode;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author happyzhao
 * @data 2020/11/12 19:11
 * @type leetcode
 * @question RMQ问题  Range Maximum Query 求区间[i,j]的最大值
 */
public class RMQ {
    static int[][] f;

    public static void monotonicStack(int[] nums) {
        int len = nums.length;
        int[] flg = new int[len];
        /*
        单调栈求flg数组
         */
        Stack<Integer> s = new Stack<>(); // 存放数组下标  维护一个单调递减栈
        for (int i = 0; i < len; i++) {
            if (s.isEmpty() || nums[s.peek()] > nums[i]) {
                s.push(i);
            } else {
                int t = s.pop();
                flg[t] = i - t - 1;
                i--;
            }
        }
        while (!s.isEmpty()) {
            int t = s.pop();
            flg[t] = len - t - 1;
        }/*单调栈求flg数组 end*/

        f = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                f[i][j] = i == j ? nums[i] : -1;
            }
        }
        for (int i = 0; i < len; i++) {
            int n = flg[i];
            for (int j = i; j <= n + i; j++) {
                f[i][j] = nums[i];
            }
            if (n + i + 1 < len) {
                f[i][n + i + 1] = nums[n + i + 1];
            }
        }
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (f[i][j] == -1) {
                    f[i][j] = getMax(i, j, flg, len);
                }
            }
        }

    }

    // 获得数组在区间[i,j]的最大值
    static int getMax(int i, int j, int[] flg, int len) {
        if (f[i][j] != -1) {
            return f[i][j];
        }
        return f[i][j] = getMax(i + flg[i] + 1, j, flg, len);
    }

    static int getMax(int[] nums, int i, int j) {
        int max = -1;
        for (int n = i; n <= j; n++) {
            max = Math.max(max, nums[n]);
        }
        return max;
    }

    // 随机生成一个[a,b]区间内的数
    public static int nextRandomInt(int a, int b) {
        return (int) (Math.random() * (b - a + 1) + a);
    }

    // ST算法求RMQ
    static int[][] sf;

    public static void STHandle(int[] nums) {
        int len = nums.length;
        int maxPower = (int) (Math.log(len) / Math.log(2));
        sf = new int[len][maxPower + 1];
        // 初始化状态
        for (int i = 0; i < len; i++) {
            sf[i][0] = nums[i];
        }
        // 状态转移
        for (int j = 1; j <= maxPower; j++) {
            for (int i = 0; i < len; i++) {
                // 求sf[i][j]
                if (i + (1 << j) - 1 < len) {
                    sf[i][j] = Math.max(sf[i][j - 1], sf[i + (1 << j - 1)][j - 1]);
                }
            }
        }

    }

    public static int STQuery(int a, int b) {
        int k = (int) (Math.log(b - a + 1) / Math.log(2));
        return Math.max(sf[a][k], sf[b - (1 << k) + 1][k]);
    }

    public static void test() {
        // 数组总长度
        int len = 10000;
        int[] nums = new int[len];
        for (int i = 0; i < len; i++) {
            nums[i] = nextRandomInt(0, len - 1);
        }
        System.out.println();

        int queryNum = 1000000;
        int[][] queryItems = new int[queryNum][2];
        for (int i = 0; i < queryNum; i++) {
            int a = nextRandomInt(0, len - 1), b = nextRandomInt(0, len - 1);
            queryItems[i][0] = Math.min(a, b);
            queryItems[i][1] = Math.max(a, b);
        }
        /*
        ST test
         */
        long start = System.currentTimeMillis();
        STHandle(nums);
        for (int i = 0; i < queryNum; i++) {
            if (STQuery(queryItems[i][0], queryItems[i][1]) < 0) {
                System.out.println("error");
                return;
            }
        }
        long end = System.currentTimeMillis();
        System.out.printf("%-15s： %6d ms \n", "ST",end-start);
//        System.out.println("ST: " + (end - start));

        /*
        monotonicStack test
         */
        start = System.currentTimeMillis();
        monotonicStack(nums);
        for (int i = 0; i < queryNum; i++) {
            if (f[queryItems[i][0]][queryItems[i][1]] < 0) {
                System.out.println("error");
                return;
            }
        }
        end = System.currentTimeMillis();
//        System.out.println("monotonicStack: \t" + (end - start));
        System.out.printf("%-15s： %6d ms \n", "monotonicStack",end-start);
        /*
        SegmentTree test
         */
        start = System.currentTimeMillis();
//        int[] newNums = {5,9,7,4,6,1};
        SegmentTreeMax segmentTreeMax = new SegmentTreeMax(nums);
        segmentTreeMax.build(0);
        for (int i = 0; i < queryNum; i++) {
            if (segmentTreeMax.query(0,queryItems[i][0], queryItems[i][1]) < 0) {
                System.out.println("error");
                return;
            }
        }
        end = System.currentTimeMillis();
        System.out.printf("%-15s： %6d ms \n", "segmentTree",end-start);

//        System.out.println("segmentTree: \t" + (end - start));

        /*
        normal test
         */
        start = System.currentTimeMillis();
        for (int i = 0; i < queryNum; i++) {
            if (getMax(nums, queryItems[i][0], queryItems[i][1]) < 0) {
                System.out.println("error");
                return;
            }
        }
        end = System.currentTimeMillis();
        System.out.printf("%-15s： %6d ms \n", "normal",end-start);

//        System.out.println("normal: \t" + (end - start));
    }



    public static void main(String[] args) {

        test();
//        Scanner sc = new Scanner(System.in);
//        int t = sc.nextInt();
//        while (t-- > 0) {
//            int len = sc.nextInt();
//            int[] nums = new int[len];
//            for (int i = 0; i < len; i++) {
//                nums[i] = sc.nextInt();
//            }
//            STHandle(nums);
//            int q = sc.nextInt();
//            for (int n = 0; n < q; n++) {
//                int i = sc.nextInt(), j = sc.nextInt();
////                System.out.println(f[i-1][j-1]);
//                System.out.println(STQuery(i - 1, j - 1));
//            }
//        }
    }
}
