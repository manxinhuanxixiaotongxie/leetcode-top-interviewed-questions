import java.util.ArrayList;
import java.util.List;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：["()"]
 */
public class Code022_GenerateParenthesis {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        char[] path = new char[2 * n];
        process(n, 0, ans, path, 0, 2 * n);
        return ans;
    }

    public void process(int leftNum, int rigjtNum, List<String> ans, char[] path, int index, int n) {
        if (index == path.length) {
            ans.add(String.valueOf(path));
            return;
        }

        if (leftNum > 0) {
            // 左边有多余的左括号
            path[index] = ')';
            process(leftNum - 1, rigjtNum + 1, ans, path, index + 1, n);
        }

        if (rigjtNum > 0) {
            // 右边有多余的右括号
            path[index] = '(';
            process(leftNum + 1, rigjtNum - 1, ans, path, index + 1, n);
        }
    }
}
