import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串 的长度。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= s.length <= 5 * 10^4
 * s 由英文字母、数字、符号和空格组成
 */
public class Code003_LengthOfLongestSubstring {
    public static int lengthOfLongestSubstring(String s) {
        int ans = 0;
        // 标记字符上一次出现的位置
        Map<Character, Integer> indexMap = new HashMap<>();
        // 前一个位置的最大无重复子串长度
        int pre = 0;
        char[] str = s.toCharArray();
        int N = str.length;
        // 长度怎么进行计算的
        // 如果前面一个位置的最小长度是2 当前位置是4 那么当前位置可能的长度是2+1
        // 那么作为前一个字符来讲 出现pre的index就是i-pre
        // 当前字符前面出现的位置有两种情况
        // 第一种情况 当前位置的字符之前出现的位置是在pre之前 那么最长字符串的长度就是pre+1
        // 第二种情况 当前位置字符出现之前的位置在pre之后 那么当前子串的长度就是 i - indexMap.get(str[i])
        for (int i = 0; i < N; i++) {
            pre = Math.min(i - indexMap.getOrDefault(str[i], -1), pre + 1);
            ans = Math.max(ans, pre);
            indexMap.put(str[i], i);
        }
        return ans;
    }
}
