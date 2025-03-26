import java.util.ArrayList;
import java.util.List;

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
        List<TreeNode> list = new ArrayList<>();
        process(root,list);
        for (int i = 0; i < list.size()-1;i++) {
            list.get(i).left = null;
            list.get(i).right = list.get(i+1);
        }
    }

    public void process(TreeNode root,List<TreeNode> list) {
        if (root == null) {
            return;
        }else {
            list.add(root);
            process(root.left,list);
            process(root.right,list);
        }
    }



}
