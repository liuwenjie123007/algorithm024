package Week_04;

/**
 * <h1>153. 寻找旋转排序数组中的最小值</h1>
 * <a>https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/</a>
 * <h3>二分查找</h3>
 * <p>时间复杂度O(LogM) 空间复杂度O(1)</p>
 *
 * @author WENJIE
 */
public class FindMinimumInRotatedSortedArray {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[left];
    }
}
