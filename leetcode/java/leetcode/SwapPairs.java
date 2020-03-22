/**
 * @author happyzhao
 * @data 2020/3/21 22:15
 * @type PACKAGE_NAME
 * @question
 */
class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
public class SwapPairs {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode bbp = head,bp = head,p = head.next;
        head = p;
        ListNode t = bp.next;
        bp.next = p.next;
        p.next = bp;
        bbp = bp;
        bp = bbp.next;
        p = bp == null ? null : bp.next;
        while(bp != null && p != null){
            bp.next = p.next;
            p.next = bp;
            bbp.next = p;
            bbp = bp;
            bp = bbp.next;
            p = bp == null ? null : bp.next;
        }
        return head;
    }
}
