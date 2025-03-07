/**
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 * <p>
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * <p>
 * 提示：
 * 链表中的节点数目为 n
 * 1 <= k <= n <= 5000
 * 0 <= Node.val <= 1000
 * <p>
 * 进阶：你可以设计一个只用 O(1) 额外内存空间的算法解决此问题吗？
 */
public class Code025_ReverseKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        // 使用O（1）的算法解决这个问题
        if (k == 1) {
            return head;
        }
        // k哥一组翻转链表
        ListNode newHead = null;
        ListNode cur = head;
        // 定义一个一个影子节点
        // 用于标记上一个位置的结尾
        ListNode lastPre = new ListNode(-1);
        int index = 0;
        while (cur != null) {
            ListNode pre = cur;
            ListNode tempCur = cur;
            while ((++index) <= k) {
                if (cur == null) {
                    return newHead;
                }
                cur = cur.next;
            }
            // 此时cur来到了要翻转链表的下一个位置
            // 从pre开始翻转链表
            ListNode tempPre = null;
            while ((--index) > 0) {
                ListNode tempNext = pre.next;
                pre.next = tempPre;
                tempPre = pre;
                pre = tempNext;
            }
            // 上次一次的尾巴连接下一次的头
            lastPre.next = tempPre;
            // 这一次的头连接下一次的头
            tempCur.next = cur;
            if (newHead == null) {
                newHead = tempPre;
            }
            lastPre = tempCur;

        }
        return newHead;
    }

    public static void main(String[] args) {
        Code025_ReverseKGroup reverseKGroup = new Code025_ReverseKGroup();
        ListNode head = new ListNode(1);
        ListNode cur = head;
        for (int i = 2; i <= 5; i++) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }
        ListNode listNode = reverseKGroup.reverseKGroup(head, 2);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
