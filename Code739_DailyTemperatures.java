import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i]
 * 是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 *
 *
 * 1 <= temperatures.length <= 10^5
 * 30 <= temperatures[i] <= 100
 *
 */
public class Code739_DailyTemperatures {
    public int[] dailyTemperatures(int[] temperatures) {
        // 使用单调栈
        // 单调栈的顺序是
        // 从栈顶到栈低是由大到小
        Stack<List<Integer>> stack = new Stack<>();
        int[] ans = new int[temperatures.length];
        for (int i = 0; i < temperatures.length;i++) {
            while (!stack.isEmpty() && temperatures[stack.peek().get(0)]  < temperatures[i]) {
                List<Integer> pop = stack.pop();
                for (int j = 0; j < pop.size(); j++) {
                    ans[pop.get(j)] = i - pop.get(j);
                }
            }
            if (stack.isEmpty() || temperatures[stack.peek().get(0)] > temperatures[i]) {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            } else {
                stack.peek().add(i);
            }
        }
        return ans;
    }
}
