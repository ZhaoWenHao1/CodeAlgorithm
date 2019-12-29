import java.util.HashMap;
import java.util.Stack;
import java.util.Vector;

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

    public static void main(String[] args)
    {
        int[] n1 = {1,2},n2 = {1,8,6,2,5,4,8,3,7},n3 = {1,2,1};
        Solution solution = new Solution();
        System.out.println(solution.maxArea(n1));
        System.out.println(solution.maxArea(n2));
        System.out.println(solution.maxArea(n3));
        //System.out.println("PAHNAPLSIIGYIR");
    }
};