/**
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 * <p>
 * 提示：
 * <p>
 * 链表中节点的数目在范围 [0, 100] 内
 * 0 <= Node.val <= 100
 */
public class Code024_SwapPairs {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 原地修改当前链表
        ListNode cur = head;
        // 上一组的结束位置
        ListNode pre = new ListNode(-1);
        ListNode newHead = null;

        while (cur != null && cur.next != null) {
            ListNode next = cur.next.next;
            ListNode tempNext = cur.next;
            pre.next = tempNext;
            tempNext.next = cur;
            cur.next = next;
            if (newHead == null) {
                newHead = tempNext;
            }
            pre = cur;
            cur = next;
        }
        return newHead;
    }
}
