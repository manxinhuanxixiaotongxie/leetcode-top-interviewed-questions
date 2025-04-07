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
     * 范围上的尝试模型
     *
     * @param str1
     * @param str2
     * @param i1
     * @param i2
     * @return
     */
    public int process(char[] str1, char[] str2, int i1, int i2) {
        if (i1 == 0 && i2 == 0) {
            return str1[i1] == str2[i2] ? 0 : 1;
        }

        if (i2 == 0) {
            // 当0-i1不包含i2的时候是i1 其他情况是i1+1
            for (int i = 0; i <= i1; i++) {
                if (str1[i] == str2[i2]) {
                    return i1;
                }
            }
            return i1 + 1;
        }

        if (i1 == 0) {
            // 当0-i2不包含i1的时候是i2 其他情况是i2+1
            for (int i = 0; i <= i2; i++) {
                if (str2[i] == str1[i1]) {
                    return i2;
                }
            }
            return i2 + 1;
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

    /**
     * 改成动态规划
     *
     * @param text1
     * @param text2
     * @return
     */
    public int minDistance2(String text1, String text2) {
        char[] str1 = text1.toCharArray();
        char[] str2 = text2.toCharArray();

        if (str1.length == 0) {
            return str2.length;
        }
        if (str2.length == 0) {
            return str1.length;
        }

        int[][] dp = new int[str1.length][str2.length];
        dp[0][0] = str1[0] == str2[0] ? 0 : 1;
        // 第一行
        for (int col = 1; col < str2.length; col++) {
            // row == 0
            boolean has = false;
            for (int rest = 0; rest <= col; rest++) {
                if (str2[rest] == str1[0]) {
                    dp[0][col] = col;
                    has = true;
                }
            }
            if (!has) {
                dp[0][col] = col + 1;
            }
        }
        // 第一列
        for (int row = 1; row < str1.length; row++) {
            // col == 0
            boolean has = false;
            for (int rest = 0; rest <= row; rest++) {
                if (str1[rest] == str2[0]) {
                    dp[row][0] = row;
                    has = true;
                }
            }
            if (!has) {
                dp[row][0] = row + 1;
            }
        }

        // 普遍位置
        for (int i1 = 1; i1 < str1.length; i1++) {
            for (int i2 = 1; i2 < str2.length; i2++) {
                if (str1[i1] == str2[i2]) {
                    dp[i1][i2] = dp[i1 - 1][i2 - 1];
                } else {
                    dp[i1][i2] = Math.min(dp[i1 - 1][i2] + 1, Math.min(dp[i1][i2 - 1] + 1, dp[i1 - 1][i2 - 1] + 1));
                }
            }
        }
        return dp[str1.length - 1][str2.length - 1];
    }
}
