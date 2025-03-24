public class Code104_MaXDepth {
    public int maxDepth(TreeNode root) {
        return process(root).maxDepth;
    }

    public Info process(TreeNode root) {
        if (root == null) {
            return new Info(0);
        }
        if (root.left == null && root.right == null) {
            return new Info(1);
        }
        Info left = process(root.left);
        Info right = process(root.right);
        return new Info(Math.max(left.maxDepth, right.maxDepth) + 1);
    }

    class Info {
        int maxDepth;

        Info(int maxDepth) {
            this.maxDepth = maxDepth;
        }
    }
}
