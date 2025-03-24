/**
 * 给你一个字符串 s，找到 s 中最长的 回文 子串。
 */
public class Code005_LongestPalindrome {
    public String longestPalindrome(String s) {
        // 改写manacher算法
        // 回文半径
        int c = 0;
        // 最远的边界
        // 当回文半径是c的时候 达到最远的右边界是r
        int r = -1;
        char[] str = getString(s);
        int[] pNext = new int[str.length];
        String ans = "";
        for (int i = 0; i < str.length; i++) {
            // 找到对称点 i关于c的对称点
            // 讨论情况
            // 第一种情况i在r的右边 这时候只能暴力扩大
            // 第二种情况i在r的左边
            // 这种情况下第一种情况 i1位置在r关于c对称点的里面
            // 那么这种情况下的i位置的回文半径就是i1位置回文半径
            // 这种情况的第二种情况
            // 刚好对称点刚好在r的边界上 这时候也是不能扩大 为什么不能扩大 如果能扩在前面的位置已经扩大了
            // 超出了l的范围 这种情况下 这种情况下能扩大吗 有可能扩大
            pNext[i] = r > i ? Math.min(r - i, pNext[2 * c - i]) : 1;
            // 暴力扩大
            while (i + pNext[i] < str.length && i - pNext[i] > -1) {
                if (str[i + pNext[i]] == str[i - pNext[i]]) {
                    pNext[i]++;
                } else {
                    break;
                }
            }
            // 更新c和r
            if (i + pNext[i] > r) {
                c = i;
                r = i + pNext[i];
            }
            if (pNext[i] > ans.length()) {
                ans = s.substring((i - pNext[i] + 1) / 2, (i + pNext[i] - 1) / 2);
            }
        }
        return ans;
    }

    private char[] getString(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char[] newStr = new char[str.length() * 2 + 1];
        for (int i = 0; i < newStr.length; i++) {
            newStr[i] = (i % 2 == 0) ? '#' : str.charAt(i / 2);
        }
        return newStr;
    }
}
