package Week_01;

/**
 * <h1>26. 删除排序数组中的重复项</h1>
 * <a>https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/</a>
 * <h3>方法：快慢指针</h3>
 * <p>时间复杂度O(n),空间复杂度O(1)</p>
 * @author WENJIE
 */
public class RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] nums) {
        int resolved = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[resolved] != nums[i]) {
                nums[++resolved] = nums[i];
            }
        }
        return resolved + 1;
    }
}
