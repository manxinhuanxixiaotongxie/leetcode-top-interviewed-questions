/**
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * <p>
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 每个链表中的节点数在范围 [1, 100] 内
 * 0 <= Node.val <= 9
 * 题目数据保证列表表示的数字不含前导零
 */
public class Code002_AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 翻转两个链表
        int add = 0;
        ListNode cur = new ListNode(0);
        ListNode head = cur;
        while (l1 != null || l2 != null) {
            int vurValue = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + add;
            add = vurValue / 10;
            vurValue = vurValue % 10;
            ListNode node = new ListNode(vurValue);
            cur.next = node;
            cur = node;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (add > 0) {
            cur.next = new ListNode(add);
        }
        return head.next;
    }

    public ListNode revserse(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
