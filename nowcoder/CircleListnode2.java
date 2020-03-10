import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author happyzhao
 * @data 2020/3/5 20:55
 * @type leetcode
 * @question 环形链表 II 找入口
 * https://blog.csdn.net/sinat_35261315/article/details/79205157
 */

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class CircleListnode2 {
    public ListNode detectCycle(ListNode head) {
        ListNode faster = head, slower = head;
        ListNode p1 = head,p2 = null;
        boolean hasCircle = false;
        while(faster != null && faster.next != null){
            faster = faster.next.next;
            slower = slower.next;
            if(faster == slower)
            {
                hasCircle = true;
                p2 = faster;
                break;
            }
        }
        if(!hasCircle)
            return null;
        while(p1 != p2) // 有环，所以不用考虑越界
        {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }
}
