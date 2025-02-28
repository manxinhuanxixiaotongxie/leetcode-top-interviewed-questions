import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Code128_LongestConsecutive {
    /**
     * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
     * <p>
     * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [100,4,200,1,3,2]
     * 输出：4
     * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
     * 示例 2：
     * <p>
     * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
     * 输出：9
     * 示例 3：
     * <p>
     * 输入：nums = [1,0,1,2]
     * 输出：3
     * <p>
     * <p>
     * 提示：
     * <p>
     * 0 <= nums.length <= 10^5
     * -10^9 <= nums[i] <= 10^9
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        // 要实现时间复杂度O(N) 就不能排序 排序最好的时间复杂度是O(NlogN)
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        int ans = 0;
        // 遍历set
        for (Integer num : set) {
            if (set.contains(num - 1)) {
                continue;
            }
            int cur = 1;
            int temp = num;
            while (set.contains(temp + 1)) {
                cur++;
                temp++;
            }
            ans = Math.max(ans, cur);
        }
        return ans;
    }

    /**
     * 省掉一个变量
     *
     * @param nums
     * @return
     */
    public int longestConsecutive2(int[] nums) {
        // 要实现时间复杂度O(N) 就不能排序 排序最好的时间复杂度是O(NlogN)
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        int ans = 0;
        // 遍历set
        for (Integer num : set) {
            if (set.contains(num - 1)) {
                continue;
            }
            int cur = num;
            while (set.contains(num + 1)) {
                cur++;
            }
            ans = Math.max(ans, cur - num + 1);
        }
        return ans;
    }

    /**
     * 使用并查集实现
     *
     * 这个方法可能会过不了  数据量大的时候  会超时
     * 大概原因是并查集的时间复杂度比较大
     *
     * @param nums
     * @return
     */
    public int longestConsecutive3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        UnionSet unionSet = new UnionSet(nums);
        // 建立并查集之后
        // 遍历数组
        // 如果当前元素的前一个元素在数组中
        // 那么合并
        // 如果当前元素的后一个元素在数组中
        // 那么合并
        // 合并的时候更新最大长度
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                continue;
            }
            map.put(nums[i], i);
            if (map.containsKey(nums[i] - 1)) {
                unionSet.union(i, map.get(nums[i] - 1));
            }
            if (map.containsKey(nums[i] + 1)) {
                unionSet.union(i, map.get(nums[i] + 1));
            }
        }
        return unionSet.getMaxLength();
    }
}

class UnionSet {
    private int maxLength = 1;
    private int[] parent;
    private int[] size;

    UnionSet(int[] nums) {
        parent = new int[nums.length];
        size = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int getMaxLength() {
        return maxLength;
    }


    public void union(int i, int j) {
        int aFather = findFather(i);
        int bFather = findFather(j);
        if (aFather != bFather) {
            if (size[aFather] < size[bFather]) {
                parent[aFather] = bFather;
                parent[i] = bFather;
                size[bFather] += size[aFather];
                maxLength = Math.max(maxLength, size[bFather]);
            } else {
                parent[bFather] = aFather;
                parent[j] = aFather;
                size[aFather] += size[bFather];
                maxLength = Math.max(maxLength, size[aFather]);
            }
        }
    }

    private int findFather(int i) {
        // 一路向上找
        // 向上找的过程当中 进行合并
        int[] stack = new int[parent.length];
        int stackSize = 0;
        while (parent[i] != i) {
            stack[stackSize++] = i;
            i = parent[i];
        }
        while (stackSize > 0) {
            parent[stack[--stackSize]] = i;
        }
        return i;
    }
}



