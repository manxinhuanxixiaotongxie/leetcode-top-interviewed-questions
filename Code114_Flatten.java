/**
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 *
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 *
 *
 * 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？
 *
 *
 */
public class Code114_Flatten {
    public void flatten(TreeNode root) {
        process(root);
    }

    public TreeNode process(TreeNode root) {
//        if (root == null) {
//            return null;
//        }
//        if (root.left == null && root.right == null) {
//            return root;
//        }
//       // 左树的左右侧节点
//        TreeNode mostRight = root.left;
//        while (mostRight != null && mostRight.right != null) {
//            mostRight = mostRight.right;
//        }
//        if (mostRight != null) {
//            mostRight.right = process(root.right);
//            root.right = process(root.left);
//        }else {
//            root.right = process(root.right);
//        }
        return root;
    }
}
