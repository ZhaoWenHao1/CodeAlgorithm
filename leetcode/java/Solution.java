import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

class ListNode {
          int val;
          ListNode next;
          ListNode(int x) { val = x; }
      }
class Solution {
    int reverse(int x) {
        int x1 = Math.abs(x);

        try {
//            String s = Integer.toString(x1);
            String s = String.valueOf(x1);

            StringBuffer stringBuffer = new StringBuffer(s);
            int ans;
            String anss;
            if(x < 0)
            {
                x = -x;
                anss = "-"+ stringBuffer.reverse().toString();
            }
            else
            {
                anss = stringBuffer.reverse().toString();
            }
            ans = Integer.parseInt(anss);
            return ans;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }
    public int numJewelsInStones(String J, String S) {
        int ans = 0;
        int l1 = J.length(),l2 = S.length();
        for(int i = 0;i < l2;i++)
        {
            for(int  j = 0;j < l1;j++)
            {
                if(S.charAt(i)==J.charAt(j))
                {
                    ans++;
                    break;
                }
            }
        }
        return ans;
    }

    public int findNumbers(int[] nums) {
        int len = nums.length;
        int ans = 0;
        for(int i = 0;i < len;i++)
        {
            int pos = nums[i];
            int count = Integer.toString(nums[i]).length();
            if(count % 2 == 0)  ans++;
        }
        return ans;
    }

    public String defangIPaddr(String address) {
        address = address.replaceAll("[.]","[.]");
        return address;
    }

    public int minTimeToVisitAllPoints(int[][] points) {
        int ans = 0;
        int posx = points[0][0], posy = points[0][1];
        for(int i = 1;i < points.length;i++)
        {

            int dx = Math.abs(points[i][0]-posx),dy = Math.abs(points[i][1]-posy);
            int steps = dx > dy ? dx : dy;
            ans += steps;
            posx = points[i][0];
            posy = points[i][1];



        }
        return ans;
    }
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */
    public int getDecimalValue(ListNode head) {
        int ans = head.val;
        ListNode p = head.next;

        while(p != null)
        {
            ans *= 2;
            ans += p.val;
            p = p.next;
        }
        return  ans;
    }

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /*
    3. 无重复字符的最长子串
    给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     */
    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        int ans = 0,pans = 0;
        HashMap<Character,Integer> hashMap = new HashMap<>();
        for(int i = 0;i < len;i++)
        {
            if(hashMap.containsKey(s.charAt(i)))
            {
                int lastx = hashMap.get(s.charAt(i));
                hashMap.clear();
                ans = Math.max(ans,pans);
                pans = 0;
                for(int j = lastx+1;j <= i;j++)
                {
                    hashMap.put(s.charAt(j),j);
                    pans++;
                }
            }
            else {
                hashMap.put(s.charAt(i),i);
                pans++;
            }
        }
        ans = Math.max(ans,pans);
        return ans;
    }


/*
6. ZigZag Conversion
 */
    public String convert(String s, int numRows) {
        if(numRows==1)
            return s;
        //将一个V看做是新的数组的一行
        int col = 2*numRows-2;//新的数组的列数
        int row = s.length()%col == 0? s.length()/col : s.length()/col+1;
        char[][] ans = new char[row][col];
        int cont = 0;
        for(int i = 0;i < row && cont < s.length();i++)
        {
            for(int j = 0;j < col && cont < s.length();j++)
            {
                //System.out.println(j);
                ans[i][j] = s.charAt(cont++);
                //System.out.print(ans[j][i]);
            }
        }
        /*for(int i = 0;i < row;i++)
        {
            for(int j = 0;j < col && i*col+j < s.length();j++)
            {
                System.out.print(ans[i][j]);
            }
            System.out.println();
        }*/
        cont = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for(int k = 0;k < numRows;k++)
        {
            for(int i = 0;i < row && cont < s.length();i++)
            {
                int j = k;
                if(i*col+j < s.length())
                    stringBuilder.append(ans[i][j]);
                if(k != 0 && k != numRows-1)
                {
                    j = col-k;
                    if(i*col+j < s.length())
                        stringBuilder.append(ans[i][j]);
                }
            }
        }

        return stringBuilder.toString();
    }
    public String convert1(String s, int numRows) {
        int n1 = 2*numRows-2;//每n1个占用numRows-1列
        int col = s.length()/n1*(numRows-1);
        int last = s.length()-col/(numRows-1)*n1;
        if(last > 0 && last <= numRows)
            col +=1;
        else
        {
            if(last > numRows)
                col = col + last-numRows+1;
        }
        System.out.println("col: " + col);
        char[][] ans = new char[numRows][col];
        int cont = 0,px = 0,py = 0,flag = 0;//flag表示是否转折
        while(cont < s.length())
        {
            if(px < numRows && py < col)
            {
                ans[px][py] = s.charAt(cont++);
            }
            if(flag == 0)
            {
                if(px < numRows-1)//转折前
                {
                    px++;
                }
                else if(px == numRows-1)//转折
                {
                    flag = 1;
                    px--;
                    py++;
                }
            }
            else {
                if(px == 0)//回到第一行
                {
                    flag = 0;
                    px++;
                }
                else//转折后
                {
                    px--;
                    py++;
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        //char undefined;
        for(int i = 0;i < numRows;i++)
        {
            for(int j = 0;j < col;j++)
            {
                //if(ans[i][j] <= 'Z' && ans[i][j] >= 'A')
                if(ans[i][j] != '\u0000')
                {
                    stringBuilder.append(ans[i][j]);
                }
            }
        }
        return stringBuilder.toString();
    }

    /*
    8. String to Integer (atoi)
     */
    public int myAtoi(String str) {
        int start = 0,end = 0;
        int op = 1;
        while(start < str.length() && str.charAt(start) == ' ' )  start++;
        if(start == str.length())
            return 0;

        if(str.charAt(start) == '+' || str.charAt(start) == '-')
        {
            op = str.charAt(start) == '+' ? 1 : -1;
            start++;
            if(start >= str.length() || str.charAt(start)  < '0' || str.charAt(start) > '9')
                return 0;
        }
        else if(str.charAt(start) < '0' || str.charAt(start) > '9')
            return 0;
        end = start + 1;
        while (end < str.length() && str.charAt(end) >= '0' && str.charAt(end) <= '9')  end++;
        if(end >= str.length())
            end--;
        else
            end--;
        long ret = 0;

        for(int i = start;i <= end;i++)
        {
            ret = ret*10 + str.charAt(i)-'0';
            if(ret > Integer.MAX_VALUE)
            {
                if(op == -1)
                    return Integer.MIN_VALUE;
                else
                    return Integer.MAX_VALUE;
            }
        }
        ret = op * ret;

        return (int)ret;
    }
    /*
    11. 盛最多水的容器
    单调栈(错误）
    双指针法
     */
    public int maxArea(int[] height) {
        int ret = 0;
        int l = 0, r = height.length-1;
        while (l < r)
        {
            ret = Math.max(Math.min(height[l],height[r])*(r-l),ret);
            if(height[l] < height[r])
                l++;
            else
                r--;
        }
        return ret;

    }

    /*
    15. 三数之和
    给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
注意：答案中不可以包含重复的三元组。
     */
    void thrSumDfs(List<List<Integer>> ret,List<Integer> ans ,int[] nums, int pos,int n,int sum)//pos 表示当前在nums的下标，n表示在寻找3个数中的第几个数
    {
        if(n == 3)
        {
            if(sum == 0)
            {
                //查找结果集里是否有当前的结果
                int find = 0;
                if(ans.size() != 3)
                    return;
                for(int j = 0;j < ret.size();j++)
                {
                    int isEqual = 1;
                    for(int k = 0;k < 3;k++)//检查ret[j]是否为ans相同
                    {
                        if(ret.get(j).size() < 3)
                            return ;
                        if(ans.get(k) != ret.get(j).get(k))
                        {
                            isEqual = 0;
                            break;
                        }
                    }
                    if(isEqual == 1)//找到有相等的
                    {
                        find = 1;
                        break;
                    }

                }
                if(find == 0)//没找到相等的
                {
                    List<Integer> tmp1 = new ArrayList<>();
                    tmp1.addAll(ans);
                    ret.add(tmp1);
                }
            }
            return;
        }
        if(pos >= nums.length )
            return ;
        for(int i = pos;i < nums.length-2+n;i++)
        {
            ans.add(nums[i]);
            thrSumDfs(ret,ans,nums,i+1,n+1,sum+nums[i]);
            ans.remove(n);
        }
    }
    public List<List<Integer>> threeSum1(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ret = new ArrayList<List<Integer>>();

        for(int i = 0;i < nums.length-2;i++)
        {
            List<Integer> tmp = new ArrayList<>();
            tmp.add(nums[i]);
            thrSumDfs(ret,tmp,nums,i+1,1,nums[i]);
            tmp.remove(0);
        }
        return ret;
    }

    /*
        1.两数之和
     */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> hashMap = new HashMap<>();//key nums[i], value i
        int[] ans = new int[2];
        for(int i = 0;i < nums.length;i++)
        {
            if(hashMap.containsKey(nums[i]))
            {
                if(nums[i]*2 == target)
                {
                    ans[0] = hashMap.get(nums[i]);
                    ans[1] = i;
                    return ans;
                }
                //否则肯定不是答案
            }
            else if(hashMap.containsKey(target-nums[i]))
            {
                ans[0] = hashMap.get(target-nums[i]);
                ans[1] = i;
                return ans;
            }
            else
            {
                hashMap.put(nums[i],i);
            }
        }
        return ans;

    }
    /*
    排序+双层循环
    O(n^2logn)  458ms
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        HashSet<String> hashSet = new HashSet<>();
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        for(int i = 0;i < nums.length-2;i++)
        {
            if(nums[i] > 0) return  ret;
            for(int j = i+1;j < nums.length-1;j++)
            {
                int idx = Arrays.binarySearch(nums,j+1, nums.length,-1*(nums[i]+nums[j]));
                if(idx > j)
                {
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(nums[i]);
                    tmp.add(nums[j]);
                    tmp.add(nums[idx]);
                    if(hashSet.add(nums[i]+" "+nums[j])) // O(logN) 再加构造字符串的时间
                        ret.add(tmp);
                }
            }
        }
        return ret;
    }

    /*
    排序+双指针
    O(n^2)  34ms
    1. 特判，对于数组长度 n，如果数组为 null 或者数组长度小于 3，返回 []。
    2. 对数组进行排序。
    3. 遍历排序后数组：
        - 若 nums[i]>0：因为已经排序好，所以后面不可能有三个数加和等于 0，直接返回结果。
        - 对于重复元素：跳过，避免出现重复解
        - 令左指针 L=i+1，右指针 R=n-1，当 L<R 时，执行循环：
            - 当 nums[i]+nums[L]+nums[R]==0，执行循环，判断左界和右界
              是否和下一位置重复，去除重复解。并同时将 L,R 移到下一位置，寻找新的解
            - 若和大于 0，说明 nums[R] 太大，R 左移
            - 若和小于 0，说明 nums[L] 太小，L 右移
     */
    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if(nums == null || nums.length < 3)
        {
            return ret;
        }
        Arrays.sort(nums);
        for(int i = 0; i < nums.length-2;i++)
        {
            if(nums[i] > 0) return ret;
            if(i > 0 && nums[i] == nums[i-1])   continue;//这里正确是由于如果有一个答案包含num[i-1] 和num[i] 则在上一轮中该答案已经给出
            int L = i+1,R = nums.length-1;//第二个和第三个数
            while(L < R)
            {
                if(nums[i]+nums[L]+nums[R] == 0)
                {
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(nums[i]);
                    tmp.add(nums[L]);
                    tmp.add(nums[R]);
                    ret.add(tmp);
                    while(L < R && nums[L] == nums[++L]) ;
                    while(L < R && nums[R] == nums[--R]) ;
                }
                else if(nums[i]+nums[L]+nums[R] < 0)
                {
                    L++;
                }
                else {
                    R--;
                }
            }
        }
        return ret;
    }


    public int threeSumClosest(int[] nums, int target) {
            Arrays.sort(nums);
            if(nums.length < 3)
                return 0;
            int ret = nums[0]+nums[1]+nums[2];
            for(int i = 0;i < nums.length-2;i++)
            {
                int L = i+1, R = nums.length-1;
                while( L < R)
                {
                    int dif = nums[i]+nums[L]+nums[R]-target;
                    if(dif == 0)
                        return target;
                    else if(dif < 0)
                    {
                        L++;
                    }
                    else
                        R--;
                    if(Math.abs(ret-target) > Math.abs(dif))
                        ret = dif+target;
                }
            }
            return ret;
        }
        /*
        给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。

        不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
         */
    public int removeDuplicates(int[] nums) {
            if(nums.length <= 1) return nums.length;
            int vaild = 0, pos = 1;//vaild表示最终数组已确定的下标，pos表示nums的下标
            for(;pos < nums.length;pos++)
            {
                if(nums[pos] != nums[vaild])
                    nums[++vaild] = nums[pos];

            }
            return vaild+1;
        }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
            int[] tmp = new int[m];
            for(int i = 0;i < m;i++)
                tmp[i] = nums1[i];
            int i = 0, j = 0,cn = 0;
            while(i < m && j < n)
            {
                if(tmp[i] <= nums2[j])
                {
                    nums1[cn++] = tmp[i++];
                }
                else
                {
                    nums1[cn++] = nums2[j++];
                }
            }
            while(i < m) nums1[cn++] = tmp[i++];
            while(j < n) nums1[cn++] = nums2[j++];
        }

        /*
        给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
        不使用额外空间来实现

        1. 哈希
        2. 2∗(a+b+c)−(a+a+b+b+c)=c
        3. 异或
         */
    public int singleNumber(int[] nums) {

        /*Hash
            HashSet<Integer> hashSet = new HashSet<>();
            int ans = 0;
            for(int i = 0;i < nums.length;i++)
            {
                if(hashSet.contains(nums[i]))
                {
                    hashSet.remove(nums[i]);
                    ans -=nums[i];
                }

                else
                {
                    hashSet.add(nums[i]);
                    ans += nums[i];
                }
            }
            return ans;
          */
        /*
        2∗(a+b+c)−(a+a+b+b+c)=c
         */
            HashSet<Integer> hashSet = new HashSet<>();
            int ans = 0, sum = 0;
            for(int i = 0;i < nums.length;i++)
            {
                hashSet.add(nums[i]);
                sum +=nums[i];
            }
            for(Integer it : hashSet)
            {
                ans += it*2;
            }
            return ans-sum;

            /*
            异或
             */
            /*int ans = 0;
            for(int i = 0;i < nums.length;i++)
            {
                ans ^= nums[i];
            }
            return ans;*/
        }

    public int maxDepth(TreeNode root) {
         if(root == null)
             return 0;
         int leftDepth = maxDepth(root.left);
         int rightDepth = maxDepth(root.right);
         return Math.max(leftDepth,rightDepth)+1;
        }

    public String countAndSay(int n) {
               String s = "1";
               for(int i = 0;i < n;i++)
               {
                   StringBuilder ss = new StringBuilder();
                   int len = s.length();
                   int j = 0;
                   while (j < len) {
                       int k = j;
                       while( k+1 < len && s.charAt(k) == s.charAt(k+1)) k++;
                       int tot = k-j+1;
                       ss.append(tot);
                       ss.append(s.charAt(k));
                       j = k+1;
                   }
                   s = ss.toString();
                   System.out.println(s);
               }
               return s;
           }
    public int[] decompressRLElist(int[] nums) {
            int sum = 0;
            for(int i = 0;i < nums.length;i +=2)
            {
                sum +=nums[i];
            }
            int[] res = new int[sum];
            int pos = 0;
            for(int i = 0;i < nums.length;i +=2)
            {
                for(int j = 0;j < nums[i];j++)
                {
                    res[pos++] = nums[i+1];
                }
            }
            return res;
        }

    public int balancedStringSplit(String s) {
            int flag = 0,res = 0;
            for(int i = 0;i < s.length();i++)
            {
                if(s.charAt(i)=='L')
                {
                    flag--;
                }
                else
                    flag++;
                if(flag == 0)
                    res++;
            }
            return res;
        }

    public int maximum69Number (int num) {
            StringBuilder s = new StringBuilder(String.valueOf(num));
            for(int i = 0;i < s.length();i++)
            {
                if(s.charAt(i) == '6')
                {
                    s.replace(i,i+1,"9");
                    break;
                }
            }
            return Integer.valueOf(s.toString());
        }

    public String removeOuterParentheses(String S) {
            char[] sc = S.toCharArray();
            int start = 0, end = 1, flag = 1,sum = 0;
            while(end < sc.length)
            {
                if(sc[end] == '(')
                {
                    flag++;
                }
                else
                {
                    flag--;
                }
                if(flag == 0)
                {
                    sc[start] = '-';
                    sc[end] = '-';
                    sum++;
                    start = end+1;
                    end +=2;
                    flag=1;
                }
                else
                    end++;
            }
            char[] res = new char[sc.length-2*sum];
            int j = 0;
            for(int i = 0;i < sc.length && j < res.length;i++)
            {
                if(sc[i]!='-')
                    res[j++] = sc[i];
            }
            return new String(res);
        }
        public int getOpti(int[][] matrix,int[][] res, int x, int y)//求得res[x][y],并返回res[x][y]
        {
            int[][] steps = {{-1,0},{1,0},{0,-1},{0,1}};
            if(res[x][y] >= 0 ) return res[x][y];
            if(matrix[x][y] == 0)
            {
                res[x][y] = 0;
                return 0;
            }
            else
                res[x][y] = -2;//待求
            int m = matrix.length,n = matrix[0].length;
            int ans = m + n;
            for(int k = 0;k < 4;k++)
            {
                int dx = steps[k][0],dy = steps[k][1];
                int cx = x+dx, cy = y+dy;
                if(cx >= 0 && cx < m && cy >= 0 && cy < n)
                {
                    int tmp;
                    if(res[cx][cy] >= 0)
                        tmp = res[cx][cy];
                    if(res[cx][cy] == -2)
                        continue;
                    else
                    {
                        tmp = getOpti(matrix,res,cx,cy);
                    }
                    ans = Math.min(ans, tmp+1);
                }
            }
            res[x][y] = ans;
            return ans;
        }

        //第二种方法： 将0作为第一层节点 进行bfs搜索
        /*public int[][] updateMatrix(int[][] matrix) {
                if(matrix.length < 1) return new int[0][0];
                int m = matrix.length,n = matrix[0].length;
                int[][] res = new int[m][n];
                for(int i = 0;i < m;i++)
                {
                    for(int j = 0;j < n;j++)
                    {
                        res[i][j] = -1;
                    }
                }
                int[][] steps = {{-1,0},{1,0},{0,-1},{0,1}};
                for(int i = 0;i < m;i++)
                {
                    for(int j = 0;j < n;j++)
                    {
                        if(res[i][j] >= 0 ) continue;
                        if(matrix[i][j] == 0)
                        {
                            res[i][j] = 0;
                        }
                        else
                        {
                            res[i][j] = -2;//待求
                            getOpti(matrix,res,i,j);
                        }

                    }

                }
                return res;
            }*/
        //Map.Entry 实现
       /* public int[][] updateMatrix(int[][] matrix) {
            if(matrix.length < 1) return new int[0][0];
            int m = matrix.length,n = matrix[0].length;
            int[][] stat = new int[m][n];
            for(int i = 0;i < m;i++)
            {
                for(int j = 0;j < n;j++)
                {
                    stat[i][j] = -1;//未访问过
                }
            }
            //Map.Entry<Integer,Integer> entry = new AbstractMap.SimpleEntry<Integer, Integer>(1,2);

            Queue<Map.Entry<Integer,Integer>> queue = new LinkedList<Map.Entry<Integer, Integer>>();


        }*/
    /**
     * 功能：Java读取txt文件的内容 步骤：1：先获得文件句柄 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
     * 3：读取到输入流后，需要读取生成字节流 4：一行一行的输出。readline()。 备注：需要考虑的是异常情况
     *
     * @param filePath
     *            文件路径[到达文件:如： D:\aa.txt]
     * @return 将这个文件按照每一行切割成数组存放到list中。
     */
    public static List<String> readTxtFileIntoStringArrList(String filePath)
    {
        List<String> list = new ArrayList<String>();
        try
        {
            String encoding = "GBK";
            File file = new File(filePath);
            if (file.isFile() && file.exists())
            { // 判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);// 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;

                while ((lineTxt = bufferedReader.readLine()) != null)
                {
                    list.add(lineTxt);
                }
                bufferedReader.close();
                read.close();
            }
            else
            {
                System.out.println("找不到指定的文件");
            }
        }
        catch (Exception e)
        {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }

        return list;
    }

    public static void main(String[] args)
    {
//        int[] n1 = {-1, 0, 1, 2, -1, -4};
//        Vector<Integer> vec = new Vector<>();
//        Integer[] n2 = null;
//        //Integer[] n2 = vec.toArray(new Integer[vec.size()]);
//        Solution solution = new Solution();
//        List<String> list = Solution.readTxtFileIntoStringArrList("E:\\workspace\\CodeAlgorithm\\leetcode\\java\\data.txt");
//        if(list.size() >= 1)
//        {
//            int len = list.get(0).length();
//            String[] str = list.get(0).substring(1,len-1).split(",");
//            System.out.println(str.length);
//            for(int i = 0;i < str.length;i++)
//            {
//                vec.add(Integer.valueOf(str[i]));
//            }
//            n2 = vec.toArray(new Integer[vec.size()]);
//            for(int i = 0;i < n2.length;i++)
//            {
//                System.out.print(n2[i]+"\t");
//            }
//        }
//        System.out.println();
//        int[] n3 = new int[n2.length];
//        for(int i = 0;i < n2.length;i++)
//        {
//            n3[i] = n2[i].intValue();
//        }
//        System.out.println(solution.threeSum2(n1));
       int[][] nums =  {{1,0,1,1,0,0,1,0,0,1},{0,1,1,0,1,0,1,0,1,1},{0,0,1,0,1,0,0,1,0,0},{1,0,1,0,1,1,1,1,1,1},{0,1,0,1,1,0,0,0,0,1},{0,0,1,0,1,1,1,0,1,0},{0,1,0,1,0,1,0,0,1,1},{1,0,0,0,1,1,1,1,0,1},{1,1,1,1,1,1,1,0,1,0},{1,1,1,1,0,1,0,0,1,1}};
       for(int i = 0;i < nums.length;i++)
       {
           for(int j = 0;j < nums[0].length;j++)
           {
               System.out.print(nums[i][j]+" ");
           }
           System.out.println();
       }
        /*Solution solution = new Solution();
        int[] nums = {1,2,3,4};
        System.out.println(solution.maximum69Number(9669));*/
//        int[] nums1 = {1,2,3,0,0,0},nums2 = {2,5,6};
//        solution.merge(nums1,3,nums2,3);
//        for(int i = 0;i < nums1.length;i++)
//        {
//            System.out.print(nums1[i] + " ");
//        }
//        System.out.println();
        //System.out.println("PAHNAPLSIIGYIR");
    }
};