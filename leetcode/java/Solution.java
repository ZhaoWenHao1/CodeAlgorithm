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


    public static void main(String[] args)
    {
        Solution solution = new Solution();
        System.out.println(solution.defangIPaddr("1.1.1.1"));
    }
};