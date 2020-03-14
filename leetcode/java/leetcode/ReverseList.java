package leetcode;

/**
 * @author happyzhao
 * @data 2020/3/14 12:07
 * @type leetcode
 * @question
 */
public class ReverseList {
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode pre = null,p = head,t;
        while(p != null){
            t = p.next;
            p.next = pre;
            pre = p;
            p = t;
        }
        return pre;
    }
}
