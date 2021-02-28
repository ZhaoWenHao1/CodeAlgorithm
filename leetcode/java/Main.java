import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public int handle(int n) {
        int last = n;
        int sum = 0;
        while (last >= 2) {
            if (last == 2)//先借一瓶 喝完再还
            {
                sum++;
                return sum;
            } else {
                int cur = last / 3; // 换的汽水数
                sum += cur;
                last = cur + last - cur * 3;
            }
        }
        return sum;
    }

    public int maxProfit1(int[] prices) {
        if (prices.length <= 0) {
            return 0;
        }
        int minn = prices[0];
        int ans = 0;
        for (int price : prices) {
            minn = Math.min(minn, price);
            int profit = price - minn;
            ans = profit > ans ? profit : ans;
        }
        return ans;
    }

    public int maxProfit2(int[] prices) {
        if (prices.length <= 0) {
            return 0;
        }
        int[][] f = new int[2][prices.length]; // f[0][i] 表示第i天不持有该股票的最大收益  f[1][i] 表示第i天持有该股票的最大收益
        f[1][0] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            f[0][i] = Math.max(f[1][i - 1] + prices[i], f[0][i - 1]);
            f[1][i] = Math.max(f[1][i - 1], f[0][i - 1] - prices[i]);
        }
        return f[0][prices.length - 1];

    }

    public int maxProfit3(int[] prices) {
        if (prices == null || prices.length <= 0) return 0;
        long ans;
        long[][][] dp = new long[prices.length][2][3]; // [天数][当天收盘后是否持股][当天收盘后卖出股票次数]
        dp[0][0][0] = 0;
        dp[0][0][1] = Integer.MIN_VALUE;
        dp[0][0][2] = Integer.MIN_VALUE;
        dp[0][1][0] = -prices[0];
        dp[0][1][1] = Integer.MIN_VALUE;
        dp[0][1][2] = Integer.MIN_VALUE;
        for (int i = 1; i < prices.length; i++) {
            dp[i][0][0] = 0;
            dp[i][0][1] = Math.max(dp[i - 1][1][0] + prices[i], dp[i - 1][0][1]);
            dp[i][0][2] = Math.max(dp[i - 1][1][1] + prices[i], dp[i - 1][0][2]);
            dp[i][1][0] = Math.max(dp[i - 1][0][0] - prices[i], dp[i - 1][1][0]);
            dp[i][1][1] = Math.max(dp[i - 1][0][1] - prices[i], dp[i - 1][1][1]);
            dp[i][1][2] = Integer.MIN_VALUE;
        }
        ans = Math.max(dp[prices.length - 1][0][1], dp[prices.length - 1][0][2]);
        return (int) Math.max(ans, 0);
    }

    public int maxProfit4(int k, int[] prices) {
        long ans = 0;
        long[][][] dp = new long[prices.length][2][k + 1];
        for (int i = 0; i <= k; i++) {
            dp[0][0][i] = Integer.MIN_VALUE;
            dp[0][1][i] = Integer.MIN_VALUE;
        }
        dp[0][0][0] = 0;
        dp[0][1][0] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            for (int j = 0; j <= k; j++) {
                if (j == 0) {
                    dp[i][0][j] = 0;
                } else {
                    dp[i][0][j] = Math.max(dp[i - 1][1][j - 1] + prices[i], dp[i - 1][0][j]);
                }
                if (j == k) {
                    dp[i][1][j] = Integer.MIN_VALUE;
                } else {
                    dp[i][1][j] = Math.max(dp[i - 1][0][j] - prices[i], dp[i - 1][1][j]);
                }
            }
        }
        for (int i = 0; i <= k; i++) {
//            System.out.println(dp[prices.length - 1][0][i]);
            ans = Math.max(ans, dp[prices.length - 1][0][i]);
        }
        return (int) ans;
    }

    public int minPatches(int[] nums, int n) {
        long total = 0;
        int count = 0;
        int idx = 0;
        while (total < n) {
            if (idx < nums.length && nums[idx] <= total + 1) {
                total += nums[idx++];
            } else {
                total = total * 2 + 1;
                count++;
            }
        }
        return count;
    }

    public List<String> summaryRanges(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> list = new ArrayList<>();
        int i = 0;
        while (i < nums.length) {
            int j = i + 1;
            while (j < nums.length && nums[i] + j - i == nums[j]) j++;
            List<Integer> tem = new ArrayList<>();
            tem.add(nums[i]);
            tem.add(nums[j - 1]);
            list.add(tem);
            i = j;
        }
        List<String> ans = new ArrayList<>();
        list.forEach(integerList -> {
            if (integerList.get(0).equals(integerList.get(1))) {
                ans.add(String.valueOf(integerList.get(0)));
            } else {
                ans.add(integerList.get(0) + "->" + integerList.get(1));
            }
        });
        return ans;
    }

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        List<Integer> collect = s.chars().boxed().collect(Collectors.toList());
        char[] ss = s.toCharArray();
        int len = s.length();
        f = new int[len];
        Arrays.fill(f, -1);
        pairs.forEach(pair -> {
            int a = pair.get(0), b = pair.get(1);
            int f1 = find(a), f2 = find(b);
            if(f1 != f2){
                f[f1] = f2;
            }
        });
        HashMap<Integer, Set<Integer>> hashMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            Integer find = find(i);
            if (hashMap.containsKey(find)) {
                hashMap.get(find).add(i);
            } else {
                Set<Integer> set = new HashSet<>();
                set.add(i);
                hashMap.put(find, set);
            }
        }
        hashMap.forEach((key, set) -> {
//            List list = new ArrayList<Integer>();
            Integer[] nums = set.toArray(new Integer[0]);
            Arrays.sort(nums);
            List<Character> characterList = new ArrayList<>();
            for (Integer num : nums) {
                characterList.add(s.charAt(num));
            }
            Collections.sort(characterList);
            for (int i = 0; i < nums.length; i++) {
                ss[nums[i]] = characterList.get(i);
            }
        });
        return String.valueOf(ss);
    }

    int[] f;

    int find(int x) {
        if (f[x] == -1) {
            return x;
        } else {
            return f[x] = find(f[x]);
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
//        int[] nums = {3, 2, 6, 5, 0, 3};
        String s = "qdwyt";
        List<List<Integer>> pairs = new ArrayList<>();
        pairs.add(new ArrayList<Integer>() {{
            add(2);
            add(3);
        }});
        pairs.add(new ArrayList<Integer>() {{
            add(3);
            add(2);
        }});
        pairs.add(new ArrayList<Integer>() {{
            add(0);
            add(1);
        }});
        pairs.add(new ArrayList<Integer>() {{
            add(4);
            add(0);
        }});
        pairs.add(new ArrayList<Integer>() {{
            add(3);
            add(2);
        }});
        System.out.println(main.smallestStringWithSwaps(s, pairs));
//        System.out.println(main.maxProfit4(2, nums));

    }
}
