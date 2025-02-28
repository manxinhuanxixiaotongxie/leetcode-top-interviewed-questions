import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 * <p>
 * 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * 示例 2:
 * <p>
 * 输入: strs = [""]
 * 输出: [[""]]
 * 示例 3:
 * <p>
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 * <p>
 * 提示：
 * <p>
 * 1 <= strs.length <= 10^4
 * 0 <= strs[i].length <= 100
 * strs[i] 仅包含小写字母
 */
public class Code049_GroupAnagrams {
    /**
     * 暴力解法
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        boolean[] visited = new boolean[strs.length];
        for (int i = 0; i < strs.length; i++) {
            if(visited[i]) {
                continue;
            }
            List<String> list = new ArrayList<>();
            list.add(strs[i]);
            visited[i] = true;
            for (int j = i + 1; j < strs.length; j++) {
                String next = strs[j];
                // 判断next与strs[i]是否是字母异位词
                if (isSameWord(strs[i], next) && !visited[j]) {
                    list.add(next);
                    visited[j] = true;
                }
            }
            res.add(list);
        }

        return res;
    }


    private boolean isSameWord(String str1, String str2) {
        if (str1 == null && str2 == null) {
            return true;
        }
        if (str1 == null || str2 == null) {
            return false;
        }
        if (str1.length() != str2.length()) {
            return false;
        }
        // 拆分字符串
        int[] index = new int[26];
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        for (int i = 0; i < s1.length; i++) {
            index[s1[i] - 'a']++;
            index[s2[i] - 'a']--;
        }
        for (int j : index) {
            if (j != 0) {
                return false;
            }
        }
        return true;
    }
}
