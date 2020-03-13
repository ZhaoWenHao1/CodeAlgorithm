package leetcode;

/**
 * @author happyzhao
 * @data 2020/3/13 11:49
 * @type leetcode
 * @question 删除链表的倒数第N个节点
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class RemoveNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode p1 = head,p2 = head; // p1前面的节点，p2后面的结点
        int len = 0;
        while(p1 != null) {
            len++;
            p1 = p1.next;
        }
        if(n > len) return head; // 输入错误
        p1 = head;
        // 先让后面的节点走n步，然后前后两个节点一起往后移动，直到p2到达尾结点后，p1就是所求节点的前一个节点，方便删除
        for(int i = 0;i < n && p2!=null;i++){
            p2 = p2.next;
        }
        if(p2 == null) // 删除第一个，即删除头结点
        {
            head = head.next;
            p1 = null;
            return head;
        }
        while(p2.next != null){
            p1 = p1.next;
            p2 = p2.next;
        }
        p1.next = p1.next.next;
        return head;
    }
}
