/**
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 有效 二叉搜索树定义如下：
 * <p>
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 */
public class Code098_IsValidBST {
    public boolean isValidBST(TreeNode root) {
        return process(root).isBST;
    }

    public Info process(TreeNode root) {
        if (root == null) {
            return null;
        }
        Info left = process(root.left);
        Info right = process(root.right);
        int min = root.val;
        int max = root.val;
        boolean isBST = true;
        if (left != null) {
            min = Math.min(min, left.min);
            max = Math.max(max, left.max);
            // 必须要判断左右树是否是BST 有可能出现左右树不是BST 但是满足大小是符合要求的
            /**
             *
             * 比如例子的这棵树
             *
             */
            if (left.max >= root.val || !left.isBST) {
                isBST = false;
            }
        }
        if (right != null) {
            min = Math.min(min, right.min);
            max = Math.max(max, right.max);
            if (right.min <= root.val || !right.isBST) {
                isBST = false;
            }
        }
        return new Info(max, min, isBST);
    }


    class Info {
        int max;
        int min;
        boolean isBST;

        Info(int max, int min, boolean isBST) {
            this.max = max;
            this.min = min;
            this.isBST = isBST;
        }
    }

    public static void main(String[] args) {
        // [32,26,47,19,null,null,56,null,27]
        TreeNode root = new TreeNode(32);
        root.left = new TreeNode(26);
        root.right = new TreeNode(47);
        root.left.left = new TreeNode(19);
        root.right.right = new TreeNode(56);
        root.left.left.right = new TreeNode(27);
        System.out.println(new Code098_IsValidBST().isValidBST(root));
    }
}
