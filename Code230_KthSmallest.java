import sun.java2d.loops.GraphicsPrimitive;

/**
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 小的元素（从 1 开始计数）。
 */
public class Code230_KthSmallest {
    public int kthSmallest(TreeNode root, int k) {
        // morris遍历
        int index = 0;
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
                if ((++index) == k) {
                    return cur.val;
                }
                cur = cur.right;
            } else {
                TreeNode right = cur.left;
                while (right.right != null && right.right != cur) {
                    right = right.right;
                }
                if (right.right == null) {
                    right.right = cur;
                    cur = cur.left;
                } else {
                    if ((++index) == k) {
                        return cur.val;
                    }
                    cur = cur.right;
                    right.right = null;
                }
            }
        }
        return 1;
    }

}
