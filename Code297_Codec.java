import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的序列化 反序列化
 * <p>
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * <p>
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * <p>
 * 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 */
public class Code297_Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return levelSerial(root);
    }


    private String levelSerial(TreeNode root) {
        // 按层序列化
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        LinkedList<String> ans = new LinkedList<>();
        if (root != null) {
            queue.add(root);
            ans.add(String.valueOf(root.val));
        }
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (poll.left != null) {
                ans.add(String.valueOf(poll.left.val));
                queue.add(poll.left);
            } else {
                ans.add(null);
            }
            if (poll.right != null) {
                ans.add(String.valueOf(poll.right.val));
                queue.add(poll.right);
            } else {
                ans.add(null);
            }
        }
        while (!ans.isEmpty() && ans.peekLast() == null) {
            ans.pollLast();
        }
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        String str = ans.pollFirst();
        builder.append(str == null ? "null" : str);
        while (!ans.isEmpty()) {
            String s = ans.pollFirst();
            builder.append(",");
            builder.append(s == null ? "null" : s);
        }
        builder.append("]");
        return builder.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        // 去除第0位置与data.length()-1位置的中括号
        String[] split = data.substring(1, data.length() - 1).split(",");
        int index = 0;
        TreeNode head = generateNode(split[index++]);
        Queue<TreeNode> queue = new LinkedList<>();
        if (head != null) {
            queue.add(head);
        }
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            poll.left = generateNode(index == split.length ? "null" : split[index++]);
            poll.right = generateNode(index == split.length ? "null" : split[index++]);
            if (poll.left != null) {
                queue.add(poll.left);
            }
            if (poll.right != null) {
                queue.add(poll.right);
            }
        }

        return head;
    }

    private TreeNode generateNode(String str) {
        if (str.equals("null")) {
            return null;
        } else {
            return new TreeNode(Integer.parseInt(str));
        }
    }
}
