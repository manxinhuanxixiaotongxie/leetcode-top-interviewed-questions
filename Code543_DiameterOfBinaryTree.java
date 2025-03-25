/**
 * 给你一棵二叉树的根节点，返回该树的 直径 。
 * <p>
 * 二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。
 * <p>
 * 两节点之间路径的 长度 由它们之间边数表示。
 */
public class Code543_DiameterOfBinaryTree {
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process(root).maxLen - 1;
    }

    public Info process(TreeNode root) {
        if (root == null) {
            return new Info(0, 0);
        }
        if (root.left == null && root.right == null) {
            return new Info(1, 1);
        }
        Info left = process(root.left);
        Info right = process(root.right);
        int height = Math.max(left.height, right.height) + 1;
        int maxLen = 0;
        // 第一种情况不过当前节点
        maxLen = Math.max(left.maxLen, right.maxLen);
        // 过当前节点
        maxLen = Math.max(maxLen, left.height + right.height + 1);
        return new Info(height, maxLen);
    }

    class Info {
        int height;
        int maxLen;

        Info(int height, int maxLen) {
            this.height = height;
            this.maxLen = maxLen;
        }
    }
}
