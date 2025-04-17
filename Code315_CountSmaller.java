import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个整数数组 nums ，按要求返回一个新数组 counts 。数组 counts 有该性质： counts[i] 的值是  nums[i]
 * 右侧小于 nums[i] 的元素的数量。
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 */
public class Code315_CountSmaller {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) return ans;
        Node[] arr = new Node[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = new Node(i, nums[i]);
            ans.add(0);
        }
        if (ans.size() < 2) {
            return ans;
        }
        process(arr, 0, nums.length - 1, ans);
        return ans;
    }

    public void process(Node[] arr, int l, int r, List<Integer> ans) {
        if (l == r) {
            return;
        } else {
            int mid = (l + r) / 2;
            process(arr, l, mid, ans);
            process(arr, mid + 1, r, ans);
            merge(arr, l, mid, r, ans);
        }
    }

    public void merge(Node[] arr, int l, int mid, int r, List<Integer> ans) {
        Node[] help = new Node[r - l + 1];
        int leftIndex = mid;
        int rightIndex = r;
        int index = help.length - 1;
        while (leftIndex >= l && rightIndex >= mid + 1) {
            if (arr[leftIndex].val > arr[rightIndex].val) {
                ans.set(arr[leftIndex].index, ans.get(arr[leftIndex].index) + (rightIndex - mid));
            }
            help[index--] = arr[leftIndex].val > arr[rightIndex].val ? arr[leftIndex--] : arr[rightIndex--];
        }
        while (leftIndex >= l) {
            help[index--] = arr[leftIndex--];
        }
        while (rightIndex >= mid + 1) {
            help[index--] = arr[rightIndex--];
        }
        for (int i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
    }

    static class Node {
        int index;
        int val;

        Node(int index, int val) {
            this.index = index;
            this.val = val;
        }
    }
}
