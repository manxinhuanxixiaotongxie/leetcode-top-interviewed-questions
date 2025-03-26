/**
 * 二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中 至多出现一次 。
 * 该路径 至少包含一个 节点，且不一定经过根节点。
 * <p>
 * 路径和 是路径中各节点值的总和。
 * <p>
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 */
public class Code124_MaxPathSum {
    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process(root).maxValue;
    }

    public Info process(TreeNode root) {
        if (root == null) {
            return null;
        }
        Info left = process(root.left);
        Info right = process(root.right);
        int maxValue;
        int maxSideValue;
        if (left == null && right == null) {
            maxValue = root.val;
            maxSideValue = root.val;
        } else if (right == null) {
            maxValue = Math.max(root.val, Math.max(left.maxValue, left.maxSideValue + root.val));
            maxSideValue = Math.max(root.val, left.maxSideValue + root.val);
        } else if (left == null) {
            maxValue = Math.max(root.val, Math.max(right.maxValue, right.maxSideValue + root.val));
            maxSideValue = Math.max(root.val, right.maxSideValue + root.val);
        } else {
            maxValue = Math.max(left.maxValue, right.maxValue);
            maxValue = Math.max(maxValue, left.maxSideValue + right.maxSideValue + root.val);
            maxValue = Math.max(maxValue, Math.max(left.maxSideValue + root.val, right.maxSideValue + root.val));
            maxValue = Math.max(maxValue, Math.max(left.maxSideValue, right.maxSideValue));
            maxValue = Math.max(root.val, maxValue);

            maxSideValue = Math.max(root.val, Math.max(left.maxSideValue + root.val, right.maxSideValue + root.val));
        }
        return new Info(maxValue, maxSideValue);
    }

    class Info {
        int maxValue;
        int maxSideValue;

        Info(int maxValue, int maxSideValue) {
            this.maxValue = maxValue;
            this.maxSideValue = maxSideValue;
        }
    }
}
