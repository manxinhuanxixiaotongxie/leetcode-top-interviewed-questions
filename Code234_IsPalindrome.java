import java.util.Stack;

/**
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 */
public class Code234_IsPalindrome {
    /**
     * 是
     *
     * @param head
     * @return
     */
    public boolean isPalindrome1(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        cur = head;
        while (!stack.isEmpty()) {
            if (stack.pop().val != cur.val) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }

    /**
     * 进阶写法
     * 时间复杂度O(n) 空间复杂度O(1)
     *
     * @param head
     * @return
     */
    public boolean isPalindrome2(ListNode head) {
        ListNode cur = head;
        int len = 0;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        // 拆分链表
        // 找到上中点
        len = len >> 1;
        cur = head;
        while (len > 0) {
            len--;
            cur = cur.next;
        }
        // 翻转后面部分的链表
        ListNode pre = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        // 此时pre来到了最后一个节点
        cur = head;
        ListNode cur2 = pre;
        while (cur.next != null) {
            ListNode next = cur.next;
            ListNode next2 = pre.next;
            if (cur.val != pre.val) {
                return false;
            }
            cur = next;
            pre = next2;
        }
        // 恢复链表
        while (cur2 != null) {
            ListNode next = cur2.next;
            cur2.next = pre;
            pre = cur2;
            cur2 = next;
        }

        return true;
    }
}
