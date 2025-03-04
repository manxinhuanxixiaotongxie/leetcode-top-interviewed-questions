import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

/**
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t
 * 所有字符的子串，则返回空字符串 "" 。
 * <p>
 * <p>
 * <p>
 * 注意：
 * <p>
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
 * 示例 2：
 * <p>
 * 输入：s = "a", t = "a"
 * 输出："a"
 * 解释：整个字符串 s 是最小覆盖子串。
 * 示例 3:
 * <p>
 * 输入: s = "a", t = "aa"
 * 输出: ""
 * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
 * 因此没有符合条件的子字符串，返回空字符串。
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == s.length
 * n == t.length
 * 1 <= m, n <= 10^5
 * s 和 t 由英文字母组成
 * <p>
 * <p>
 * 进阶：你能设计一个在 o(m+n) 时间内解决此问题的算法吗？
 */
public class Code076_MinWindow {
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        int[] tCount = new int[128];
        for (char c : t.toCharArray()) {
            tCount[c]++;
        }
        int[] sCount = new int[128];

        String ans = "";
        int pre = s.length();
        int end = 0;
        // 以i开头能搞定的最小子串
        for (int i = 0; i < s.length(); i++) {
            // 从i位置一直向右推直到能找到一个包含t的子串
            while (!isEqual(sCount, tCount) && end < s.length()) {
                sCount[s.charAt(end)]++;
                end++;
            }
            // 结算
            if (end <= s.length() && end - i <= pre && isEqual(sCount, tCount)) {
                ans = s.substring(i, end);
                pre = end - i;
            }
            sCount[s.charAt(i)]--;
        }
        return ans;
    }


    public boolean isEqual(int[] sCount, int[] tCount) {
        for (int i = 0; i < 128; i++) {
            if (sCount[i] < tCount[i]) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        Code076_MinWindow obj = new Code076_MinWindow();
        String s = "a";
        String t = "a";
        System.out.println(obj.minWindow(s, t));
    }

}
