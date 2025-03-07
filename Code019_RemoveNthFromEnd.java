/**
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * <p>
 * <p>
 * 进阶：你能尝试使用一趟扫描实现吗？
 * <p>
 * <p>
 * 提示：
 * <p>
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 */
public class Code019_RemoveNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return head;
        }
        if (head.next == null) {
            return null;
        }
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }

        if (n == len) {
            return head.next;
        }
        // 如果len的长度是5 n==2 那么要删除的就是5-2+1个节点
        cur = head;
        int index = len - n;
        ListNode pre;
        ListNode next;
        len = 0;
        while (cur != null) {
            next = cur.next;
            pre = cur;
            len++;
            if (len == index) {
                // 当前节点就是要删除的节点
                pre.next = next == null ? null : next.next;
                break;
            }
            cur = next;
        }
        return head;
    }
}
