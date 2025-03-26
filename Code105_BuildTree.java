import java.util.HashMap;
import java.util.Map;

public class Code105_BuildTree {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int len1 = preorder.length;
        int len2 = inorder.length;
        return process(preorder, 0, len1 - 1, inorder, 0, len2 - 1);
    }


    public TreeNode process(int[] pre, int l1, int r1, int[] inorder, int l2, int r2) {
        if (l1 > r1) {
            return null;
        }
        if (l1 == r1) {
            return new TreeNode(pre[l1]);
        }
        // 在右侧中找打l1所在的位置
        int index = l2;
        for (int i = l2; i <= r2; i++) {
            if (inorder[i] == pre[l1]) {
                index = i;
            }
        }
        // l2 到index就是左树的范围
        TreeNode cur = new TreeNode(pre[l1]);
        // 此时左树的范围就是 l1+1 到 l1+index-l2
        cur.left = process(pre, l1 + 1, l1 + index - l2, inorder, l2, index - 1);
        cur.right = process(pre, l1 + index - l2 + 1, r1, inorder, index + 1, r2);
        return cur;
    }

    /**
     * 优化版本
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        int len1 = preorder.length;
        int len2 = inorder.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return process2(preorder, 0, len1 - 1, inorder, 0, len2 - 1, map);
    }


    public TreeNode process2(int[] pre, int l1, int r1, int[] inorder, int l2, int r2, Map<Integer, Integer> map) {
        if (l1 > r1) {
            return null;
        }
        if (l1 == r1) {
            return new TreeNode(pre[l1]);
        }
        // 在右侧中找打l1所在的位置
        int index = map.get(pre[l1]);
        // l2 到index就是左树的范围
        TreeNode cur = new TreeNode(pre[l1]);
        // 此时左树的范围就是 l1+1 到 l1+index-l2
        cur.left = process2(pre, l1 + 1, l1 + index - l2, inorder, l2, index - 1, map);
        cur.right = process2(pre, l1 + index - l2 + 1, r1, inorder, index + 1, r2, map);
        return cur;
    }
}
