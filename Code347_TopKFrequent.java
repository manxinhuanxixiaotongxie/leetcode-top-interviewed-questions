import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 * <p>
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10^5
 * k 的取值范围是 [1, 数组中不相同的元素的个数]
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
 * <p>
 * <p>
 * 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
 */
public class Code347_TopKFrequent {
    public int[] topKFrequent(int[] nums, int k) {
        MyQueue myQueue = new MyQueue(nums, k);
        for (int i = 0; i < nums.length; i++) {
            myQueue.add(nums[i]);
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = myQueue.poll().val;
        }
        return res;
    }

    class MyQueue {
        // 数组所处的位置
        Map<Integer, Integer> indexMap;

        // 堆的大小
        private int index;

        private Info[] heap;

        MyQueue(int[] nums, int k) {
            index = 0;
            indexMap = new HashMap<>();
            heap = new Info[nums.length];
        }

        public void add(int num) {
            if (indexMap.get(num) == null) {
                // 说明之前没有加入过当前堆中
                Info info = new Info(num, 1);
                indexMap.put(num, index);
                heap[index] = info;
                heapInsert(index++);
            } else {
                int numIndex = indexMap.get(num);
                heap[numIndex].count++;
                heapify(numIndex);
                heapInsert(numIndex);
            }
        }

        public Info poll() {
            if (indexMap == null) {
                return null;
            } else {
                // 说明堆内存在这个元素
                Info res = heap[0];
                swap(0, --index);
                heapify(0);
                return res;
            }
        }

        private void heapify(int i) {
            int leftIndex = 2 * i + 1;
            while (leftIndex < index) {
                int largeIndex = leftIndex + 1 < index && heap[leftIndex + 1].count > heap[leftIndex].count ? leftIndex + 1 : leftIndex;
                largeIndex = heap[largeIndex].count > heap[i].count ? largeIndex : i;
                if (largeIndex == i) {
                    break;
                }
                swap(i, largeIndex);
                i = largeIndex;
                leftIndex = 2 * i + 1;
            }
        }

        private void heapInsert(int i) {
            int fatherIndex = (i - 1) / 2;
            while (heap[fatherIndex].count < heap[i].count) {
                swap(fatherIndex, i);
                i = fatherIndex;
                fatherIndex = (i - 1) / 2;
            }
        }

        private void swap(int i, int j) {
            Info infoI = heap[i];
            Info infoJ = heap[j];
            heap[i] = infoJ;
            heap[j] = infoI;
            indexMap.put(infoI.val, j);
            indexMap.put(infoJ.val, i);
        }
    }

    class Info implements Comparator<Info> {
        int val;
        int count;

        public Info(int val, int count) {
            this.val = val;
            this.count = count;
        }

        @Override
        public int compare(Info o1, Info o2) {
            return o2.count - o1.count;
        }
    }

    public static void main(String[] args) {
        int[] nums = {3,0,1,0};
        int k = 1;
        Code347_TopKFrequent code347_topKFrequent = new Code347_TopKFrequent();
        int[] res = code347_topKFrequent.topKFrequent(nums, k);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }

}
