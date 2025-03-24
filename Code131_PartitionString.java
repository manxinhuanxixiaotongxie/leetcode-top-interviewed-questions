import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个字符串 s，请你将 s 分割成一些 子串，
 * 使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 * 示例 2：
 * <p>
 * 输入：s = "a"
 * 输出：[["a"]]
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 16
 * s 仅由小写英文字母组成
 */
public class Code131_PartitionString {
    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        char[] str = s.toCharArray();
        for (int i = 0; i < str.length; i++) {
            List<String> cur = new ArrayList<>();
            for (int j = i; j < str.length; j++) {
                // 判断是否是回文串
                if (isPal(str, i, j)) {
                    cur.add(s.substring(i, j + 1));
                }
            }
            if (!cur.isEmpty()) {
                ans.add(cur);
            }
        }
        return ans;
    }

    public boolean isPal(char[] str, int i, int j) {
        boolean res = true;
        while (i <= j) {
            if (str[i++] != str[j--]) {
                res = false;
                break;
            }
        }
        return res;
    }

}
