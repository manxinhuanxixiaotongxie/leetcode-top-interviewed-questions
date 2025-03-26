/**
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 * <p>
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 */
public class Code437_PathSum {
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        return process(root, targetSum);
    }

    public int process(TreeNode root, int rest) {
        if (rest < 0) {
            return 0;
        }
        if (rest == 0) {
            return 1;
        }
        if (root == null) {
            return 0;
        }
        int p1 = process(root.left, rest);
        int p2 = process(root.right, rest);
        int p3 = process(root.left, rest - root.val);
        int p4 = process(root.right, rest - root.val);
        return p1 + p2 + p3 + p4;
    }
}
