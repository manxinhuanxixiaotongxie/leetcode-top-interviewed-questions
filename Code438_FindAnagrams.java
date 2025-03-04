import java.util.ArrayList;
import java.util.List;

/**
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 * 示例 2:
 * <p>
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= s.length, p.length <= 3 * 10^4
 * s 和 p 仅包含小写字母
 */
public class Code438_FindAnagrams {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        int s1 = s.length();
        int s2 = p.length();
        int[] window = new int[26];
        for (int i = 0; i < s2 - 1; i++) {
            window[p.charAt(i) - 'a']++;
        }
        int index = 0;
        for (int i = s2 - 1; i < s1; i++) {
            window[s.charAt(i) - 'a']++;
            if (isSame(s.toCharArray(), index, i, p.toCharArray())) {
                ans.add(index);
            }
            window[s.charAt(index) - 'a']--;
            index++;
        }
        return ans;
    }

    private boolean isSame(char[] s1, int l, int r, char[] s2) {
        int[] count = new int[26];
        for (int i = l; i <= r; i++) {
            count[s1[i] - 'a']++;
        }
        for (int i = 0; i < s2.length; i++) {
            count[s2[i] - 'a']--;
            if (count[s2[i] - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}
