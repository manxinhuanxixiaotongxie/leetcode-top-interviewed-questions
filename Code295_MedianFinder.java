import java.util.PriorityQueue;

/**
 * 中位数是有序整数列表中的中间值。如果列表的大小是偶数，则没有中间值，中位数是两个中间值的平均值。
 * <p>
 * 例如 arr = [2,3,4] 的中位数是 3 。
 * 例如 arr = [2,3] 的中位数是 (2 + 3) / 2 = 2.5 。
 * <p>
 * <p>
 * -10^5 <= num <= 10^5
 * 在调用 findMedian 之前，数据结构中至少有一个元素
 * 最多 5 * 10^4 次调用 addNum 和 findMedian
 */
public class Code295_MedianFinder {

//    PriorityQueue<Integer> dataQueue = new PriorityQueue<>();
//    PriorityQueue<Integer> helpQueue = new PriorityQueue<>();
//
//    public Code295_MedianFinder() {
//
//    }
//
//    public void addNum(int num) {
//        dataQueue.offer(num);
//    }
//
//    public double findMedian() {
//        int size = dataQueue.size();
//        double ans = 0;
//        if (size % 2 == 0) {
//            // 大小是偶数
//            // 要弹出的下标位置是size / 2 - 1  size / 2
//            int index = size / 2 - 1;
//            int temp = 0;
//            while (temp < index) {
//                helpQueue.offer(dataQueue.poll());
//                temp++;
//            }
//            Integer poll = dataQueue.poll();
//            helpQueue.offer(poll);
//            ans = (poll + dataQueue.peek()) / 2.0;
//            while (!helpQueue.isEmpty()) {
//                dataQueue.offer(helpQueue.poll());
//            }
//        } else {
//            // 大小是奇数
//            // 要弹出的下标位置是size / 2
//            int index = size / 2;
//            int temp = 0;
//            while (temp < index) {
//                helpQueue.offer(dataQueue.poll());
//                temp++;
//            }
//            ans = dataQueue.peek();
//            while (!helpQueue.isEmpty()) {
//                dataQueue.offer(helpQueue.poll());
//            }
//        }
//
//        return ans;
//    }

    // 官方题解
    // 维护两个队列
    // 小于等于中位数的数
    PriorityQueue<Integer> queMin;
    // 大于中位数的数
    PriorityQueue<Integer> queMax;

    /**
     * 当累计添加的数的数量是奇数时
     * min的数量比max数量多一个 中位数是min的头
     *
     *
     * 当累计添加的数为偶数时
     * 两个优先级队列的数量相等 中位数为这两个队列的头的平均值
     *
     *
     */

    public Code295_MedianFinder() {
        queMin = new PriorityQueue<Integer>((a, b) -> (b - a));
        queMax = new PriorityQueue<Integer>((a, b) -> (a - b));
    }

    public void addNum(int num) {
        if (queMin.isEmpty() || num <= queMin.peek()) {
            queMin.offer(num);
            if (queMax.size() + 1 < queMin.size()) {
                queMax.offer(queMin.poll());
            }
        } else {
            queMax.offer(num);
            if (queMax.size() > queMin.size()) {
                queMin.offer(queMax.poll());
            }
        }
    }

    public double findMedian() {
        if (queMin.size() > queMax.size()) {
            return queMin.peek();
        }
        return (queMin.peek() + queMax.peek()) / 2.0;
    }

}
