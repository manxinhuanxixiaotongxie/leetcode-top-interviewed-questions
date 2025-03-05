/**
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
 * <p>
 * 图示两个链表在节点 c1 开始相交：
 */
public class Code160_GetIntersectionNode {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 题目保证是没有环的
        int l1 = 0;
        ListNode cur = headA;
        while (cur != null) {
            l1++;
            cur = cur.next;
        }
        cur = headB;
        while (cur != null) {
            l1--;
            cur = cur.next;
        }
        int abs = Math.abs(l1);
        cur = headA;
        ListNode cur2 = headB;
        if (l1 > 0) {
            // 说明是第一个链表长度较大
            // 让链表先走这么多个点
            while (abs > 0) {
                cur = cur.next;
                abs--;
            }
        } else {
            // 说明是第二个链表的长度较大
            while (abs > 0) {
                cur2 = cur2.next;
                abs--;
            }
        }
        // 同时向下移动
        while (cur != cur2) {
            cur = cur.next;
            cur2 = cur2.next;
            if (cur == null) {
                return null;
            }
        }

        return cur2;
    }

}
