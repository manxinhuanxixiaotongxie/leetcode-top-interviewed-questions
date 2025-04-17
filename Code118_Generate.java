import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
 * <p>
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 */
public class Code118_Generate {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();

        List<Integer> pre = new ArrayList<>();
        pre.add(1);
        res.add(pre);

        for (int i = 1; i < numRows; i++) {
            // 当前层
            List<Integer> cur = new ArrayList<>();
            cur.add(pre.get(0));
            for (int j = 1; j < pre.size(); j++) {
                cur.add(pre.get(j) + pre.get(j - 1));
            }
            cur.add(pre.get(pre.size() - 1));
            res.add(cur);
            pre = cur;
        }

        return res;
    }

    public static void main(String[] args) {
        Code118_Generate code118_generate = new Code118_Generate();
        List<List<Integer>> generate = code118_generate.generate(5);
        System.out.println(generate);
    }
}
