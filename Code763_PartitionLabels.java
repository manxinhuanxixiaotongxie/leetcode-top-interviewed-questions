import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。例如，字符串 "ababcc"
 * 能够被分为 ["abab", "cc"]，但类似 ["aba", "bcc"] 或 ["ab", "ab", "cc"] 的划分是非法的。
 * <p>
 * 注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。
 * <p>
 * 返回一个表示每个字符串片段的长度的列表。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 500
 * s 仅由小写英文字母组成
 */
public class Code763_PartitionLabels {
    public List<Integer> partitionLabels(String s) {
        char[] str = s.toCharArray();
        Map<Character, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < str.length; i++) {
            indexMap.put(str[i], i);
        }
        List<Integer> ans = new ArrayList<>();
        int lastIndex = 0;
        // 记录每个元素出现的最后一次的位置
        for (int i = 0; i < str.length && lastIndex < str.length; ) {
            // 获取当前字符出现的最后一次的位置
            lastIndex = indexMap.get(str[i]);
            if (lastIndex == i) {
                ans.add(1);
                i++;
            } else {
                // 不相等 向右扩大
                // 需要扩充的范围至少是当前字符的最后一次出现位置
                int temp = i;
                while (temp <= lastIndex && lastIndex < str.length) {
                    // 如果lastIndex能够扩大的话
                    if (lastIndex < indexMap.get(str[temp])) {
                        lastIndex = indexMap.get(str[temp]);
                    } else {
                        temp++;
                    }
                }
                ans.add(lastIndex - i + 1);
                i = lastIndex + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Code763_PartitionLabels code763_partitionLabels = new Code763_PartitionLabels();
        List<Integer> partitionLabels = code763_partitionLabels.partitionLabels("ababcbacadefegdehijhklij");
        System.out.println(partitionLabels);
    }
}
