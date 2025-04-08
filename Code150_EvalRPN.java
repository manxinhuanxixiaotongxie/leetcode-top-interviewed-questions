import java.util.Stack;

public class Code150_EvalRPN {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        for (String str : tokens) {
            if ("+".equals(str) || "-".equals(str) || "/".equals(str) || "*".equals(str)) {
                Integer i1 = stack.pop();
                Integer i2 = stack.pop();
                switch (str) {
                    case "+":
                        ans = i2 + i1;
                        break;
                    case "-":
                        ans = i2 - i1;
                        break;
                    case "*":
                        ans = i2 * i1;
                        break;
                    case "/":
                        ans = i2 / i1;
                        break;
                }
                stack.push(ans);
            } else {
                stack.push(Integer.valueOf(str));
            }
        }
        ans = stack.pop();
        return ans;
    }

    public static void main(String[] args) {
        Code150_EvalRPN code150_evalRPN = new Code150_EvalRPN();
        String[] tokens = {"2", "1", "+", "3", "*"};
        int result = code150_evalRPN.evalRPN(tokens);
        System.out.println(result); // Output: 9
    }
}
