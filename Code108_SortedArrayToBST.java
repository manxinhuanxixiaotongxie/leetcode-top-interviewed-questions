import sun.reflect.generics.tree.Tree;

/**
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 平衡 二叉搜索树。
 */
public class Code108_SortedArrayToBST {
    public TreeNode sortedArrayToBST(int[] nums) {
        return process(nums, 0, nums.length - 1);
    }

    public TreeNode process(int[] nums, int l, int r) {
        if (l > r) {
            return null;
        }
        if (l == r) {
            return new TreeNode(nums[l]);
        }
        int mid = (l + r) >> 1;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = process(nums, l, mid - 1);
        root.right = process(nums, mid + 1, r);
        return root;
    }
}

