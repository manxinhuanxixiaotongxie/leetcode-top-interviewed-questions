import java.util.Stack;

/**
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * <p>
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * <p>
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * <p>
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 * <p>
 * <p>
 * 1 <= s.length <= 30
 * s 由小写英文字母、数字和方括号 '[]' 组成
 * s 保证是一个 有效 的输入。
 * s 中所有整数的取值范围为 [1, 300]
 */
public class Code394_DecodeString {

    /**
     * 解码字符串
     *
     * @param s
     * @return
     */
    public String decodeString(String s) {
        return process(s.toCharArray(), 0).ans;
    }

    /**
     * 递归解码
     * <p>
     * 采用这种结构可以解决大部分字符换层次的问题
     * 函数的含义是：
     * <p>
     * 处理从index位置开始一一直到]的范围的字符串 返回已经处理完的下标
     *
     * @param chars
     * @param index
     * @return
     */
    private Info process(char[] chars, int index) {
        StringBuilder sb = new StringBuilder();
        while (index < chars.length && chars[index] != ']') {
            if (chars[index] >= '0' && chars[index] <= '9') {
                int times = 0;
                while (chars[index] >= '0' && chars[index] <= '9') {
                    times = times * 10 + chars[index] - '0';
                    index++;
                }
                // 数字后面一定是'['
                Info next = process(chars, index + 1);
                index = next.index + 1;
                sb.append(String.valueOf(next.ans).repeat(Math.max(0, times)));
            } else {
                // 左括号或者字母
                sb.append(chars[index++]);
            }
        }
        return new Info(sb.toString(), index);
    }

    static class Info {
        String ans;
        int index;

        Info(String ans, int index) {
            this.ans = ans;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        Code394_DecodeString code394_decodeString = new Code394_DecodeString();
        String s = "3[a2[c]]";
        System.out.println(code394_decodeString.decodeString(s));
    }
}
