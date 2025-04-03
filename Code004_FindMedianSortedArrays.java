/**
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * <p>
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 * <p>
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -10^6 <= nums1[i], nums2[i] <= 10^6
 */
public class Code004_FindMedianSortedArrays {

    /**
     * 如果l1与l2的长度相加是奇数的话的 那么中间的数就是(l1+l2)/2
     * 如果l1与l2的长度相加是偶数的话 那么中间的数就是的 (l1+l2)/2-1 和 (l1+l2)/2
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int length = m + n;
        // 奇数
        if (length % 2 != 0) {
            int midIndex = length / 2;
            return getKthElement(nums1, nums2, midIndex);
        } else {
            // 偶数
            int midIndex1 = length / 2 - 1, midIndex2 = length / 2;
            return (getKthElement(nums1, nums2, midIndex1) + getKthElement(nums1, nums2, midIndex2)) / 2.0;
        }
    }

    /**
     * 这个函数的含义是 在nums1数组与nums2数组中找到第index号的数
     *
     * @param nums1
     * @param nums2
     * @param index
     * @return
     */
    private int getKthElement(int[] nums1, int[] nums2, int index) {
        int i = 0;
        int lIndex = 0;
        int rIndex = 0;
        int ans = 0;
        while (i++ <= index) {
            if (lIndex == nums1.length) {
                ans = nums2[rIndex++];
            } else if (rIndex == nums2.length) {
                ans = nums1[lIndex++];
            } else if (nums1[lIndex] < nums2[rIndex]) {
                ans = nums1[lIndex++];
            } else {
                ans = nums2[rIndex++];
            }
        }
        return ans;
    }
}
