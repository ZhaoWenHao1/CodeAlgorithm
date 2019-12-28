import java.util.HashMap;

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
            //System.out.println(s.charAt(i) + " " + pans);
        }
        ans = Math.max(ans,pans);
        return ans;
    }

    public static void main(String[] args)
    {
        Solution solution = new Solution();
        System.out.println(solution.lengthOfLongestSubstring("abcabcbb"));
    }
};