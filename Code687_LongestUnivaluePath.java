import com.sun.org.apache.bcel.internal.classfile.Code;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

public class Code687_LongestUnivaluePath {
    public int longestUnivaluePath(TreeNode root) {
        /**
         *
         *
         *
         * 分情况讨论：
         * 1.第一种情况与当前节点无关
         *
         * 那么就是左树的最长同值路径 与右树的最长同值同值路径求最大值
         *
         *
         *
         * 2.第二种情况 与当前节点有关
         *
         * （1）左树的头结点与当前节点的值相等
         * 左树一个路径扎到底的最长路径
         *
         * （2）右树的头结点与当前节点的值相等
         *
         * 右树一个路径扎到底的最长路径
         *
         *
         *
         *
         */
        return process(root).max - 1;
    }

    public Code687_Info process(TreeNode root) {
        if (root == null) {
            return new Code687_Info(0, 0);
        }
        Code687_Info left = process(root.left);
        Code687_Info right = process(root.right);
        // 第一种情况 与X有关
        int len = 1;
//        if (root.left != null && root.val == root.left.val) {
//            len += left.len;
//        }
//        if (root.right != null && root.val == root.right.val) {
//            len += right.len;
//        }
        if (root.left != null && root.val == root.left.val) {
            len = left.len + 1;
        }
        if (root.right != null && root.val == root.right.val) {
            len = Math.max(len, right.len + 1);
        }
        int max = Math.max(len, Math.max(left.max, right.max));
        if (root.left != null && root.right != null && root.val == root.left.val && root.val == root.right.val) {
            max = Math.max(max, left.len + right.len + 1);
        }
        return new Code687_Info(max, len);
    }

    class Code687_Info {
        // 左树或者右树的最长路径
        int max;
        // 当前树的最长路径
        int len;

        Code687_Info(int max, int len) {
            this.max = max;
            this.len = len;
        }
    }
}
