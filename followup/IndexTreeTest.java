package followup;

import java.util.IllegalFormatCodePointException;

/**
 * indextree解决问题是前缀和问题
 * <p>
 * 在O（logn）的时间复杂度上完成这项功能
 * <p>
 * 注意：indextree数组下标都是从1开始的
 * <p>
 * 怎么计算当前位置应该负责的区间
 * <p>
 * 将下标转化成二进制 减掉最后一位1的数之后再或1 到当前位置的区间
 */

public class IndexTreeTest {

    public static class IndexTree {

        int[] indexTree;
        int N;

        IndexTree(int size) {
            this.N = size;
            indexTree = new int[N + 1];
        }

        public int getSum(int index) {
            int sum = 0;
            while (index > 0) {
                sum += indexTree[index];
                index -= (index & -index);
            }
            return sum;
        }

        public void add(int index, int value) {
            while (index <= N) {
                indexTree[index] += value;
                index += (index & -index);
            }
        }
    }

    public static class Right {
        int[] nums;
        int N;

        Right(int size) {
            N = size + 1;
            nums = new int[N];
        }

        public int getSum(int index) {
            int res = 0;
            for (int i = 1; i <= index; i++) {
                res += nums[i];
            }
            return res;
        }

        public void add(int index, int value) {
            nums[index] += value;
        }
    }

    public static void main(String[] args) {
        int N = 100;
        int v = 100;
        int testTimes = 10000000;
        Right right = new Right(N);
        IndexTree indexTree = new IndexTree(N);
        for (int i = 0; i < testTimes; i++) {
            // 随机一个index位置
            int index = (int) (Math.random() * N) + 1;
            if (Math.random() < 0.5) {
                int add = (int) (Math.random() * v);
                right.add(index, add);
                indexTree.add(index, add);
            } else {
                if (right.getSum(index) != indexTree.getSum(index)) {
                    System.out.println("Oops");
                    System.out.println("index:" + index);
                    System.out.println("right:" + right.getSum(index));
                    System.out.println("indexTree:" + indexTree.getSum(index));
                }
            }
        }
        System.out.println("test finish");
    }
}
