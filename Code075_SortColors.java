public class Code075_SortColors {
    public void sortColors(int[] nums) {
        // 0红色  1白色 2 蓝色
        // 定义两个指针
        int l = 0;
        int r = nums.length;
        for (int i = 0; i < r;) {
            if (nums[i] == 0) {
                swap(nums,i++,l++);
            }else if(nums[i] == 1) {
                i++;
            }else {
                // 等于2
                swap(nums,i,--r);
            }
        }
    }

    private void swap(int[] nums,int l,int r) {
        // 交换
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }
}
