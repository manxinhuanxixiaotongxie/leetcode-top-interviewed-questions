import java.util.Stack;

public class Code020_IsValid {
    // 测试的用例"([)]"这种过不了  还是老老实实用栈吧
    public boolean isValid(String s) {
        char[] str = s.toCharArray();
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        for (int i = 0; i < str.length;i++) {
            char cur = str[i];
            switch (cur) {
                case '(':
                    count1++;
                    break;
                case ')':
                    count1--;
                    break;
                case '[':
                    count2++;
                    break;
                case ']':
                    count2--;
                    break;
                case '{':
                    count3++;
                    break;
                case '}':
                    count3--;
                    break;
            }

        }
        return count1 == 0 && count2 == 0 && count3 == 0;
    }

    public boolean isValid2(String s) {
        char[] str = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : str) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                if (stack.pop() != c) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
