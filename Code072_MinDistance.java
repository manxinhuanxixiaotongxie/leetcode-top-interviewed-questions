import java.util.Arrays;

/**
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
 * <p>
 * 你可以对一个单词进行如下三种操作：
 * <p>
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * <p>
 * 提示：
 * <p>
 * 0 <= word1.length, word2.length <= 500
 * word1 和 word2 由小写英文字母组成
 */
public class Code072_MinDistance {

    public int minDistance(String text1, String text2) {
        char[] str1 = text1.toCharArray();
        char[] str2 = text2.toCharArray();
        if (str1.length == 0) {
            return str2.length;
        }
        if (str2.length == 0) {
            return str1.length;
        }

        return process(str1, str2, str1.length - 1, str2.length - 1);
    }

    /**
     * 这个函数的含义是
     * st1从0-i1位置变成str2从0-i2位置最小的编辑距离
     *
     * @param str1
     * @param str2
     * @param i1
     * @param i2
     * @return
     */
    public int process(char[] str1, char[] str2, int i1, int i2) {
        if (i1 < 0 && i2 < 0) {
            return 0;
        }
        if (i1 < 0) {
            return i2 + 1;
        }

        if (i2 < 0) {
            return i1 + 1;
        }
        // 普遍位置讨论可能性
        int ans = Integer.MAX_VALUE;
        if (str1[i1] == str2[i2]) {
            // 当前位置相等
            return process(str1, str2, i1 - 1, i2 - 1);
        }
        // 当前位置不相等
        ans = Math.min(ans, process(str1, str2, i1 - 1, i2) + 1); // 删除

        ans = Math.min(ans, process(str1, str2, i1, i2 - 1) + 1); // 插入

        ans = Math.min(ans, process(str1, str2, i1 - 1, i2 - 1) + 1); // 替换

        return ans;
    }

    public int minDistance2(String text1, String text2) {
        char[] str1 = text1.toCharArray();
        char[] str2 = text2.toCharArray();
        if (str1.length == 0) {
            return str2.length;
        }
        if (str2.length == 0) {
            return str1.length;
        }

        int[][] dp = new int[str1.length + 1][str2.length + 1];

        for (int i1 = 0; i1 <= str1.length; i1++) {
            for (int i2 = 0; i2 <= str2.length; i2++) {
                if (i1 == 0 && i2 == 0) {
                    dp[i1][i2] = 0;
                } else if (i1 == 0) {
                    dp[i1][i2] = i2 + 1;
                } else if (i2 == 0) {
                    dp[i1][i2] = i1 + 1;
                } else {
                    // 普遍位置讨论可能性
                    if (str1[i1 - 1] == str2[i2 - 1]) {
                        // 当前位置相等
                        dp[i1][i2] = dp[i1 - 1][i2 - 1];
                    } else {
                        // 当前位置不相等
                        dp[i1][i2] = Math.min(dp[i1 - 1][i2] + 1, Math.min(dp[i1][i2 - 1] + 1, dp[i1 - 1][i2 - 1] + 1));
                    }
                }
            }
        }

        return dp[str1.length][str2.length];
    }


}
