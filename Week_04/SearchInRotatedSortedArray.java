package Week_04;

/**
 * <h1>33. 搜索旋转排序数组</h1>
 * <a>https://leetcode-cn.com/problems/search-in-rotated-sorted-array/</a>
 * <h3>二分查找</h3>
 * <p>时间复杂度O(LogN) 空间复杂度O(1)</p>
 *
 * @author WENJIE
 */
public class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        int mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] == target)
                return mid;
            if (nums[left] < nums[mid]) {
                if (mid - 1 >= 0 && target <= nums[mid - 1] && target >= nums[left]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (mid + 1 < nums.length && target >= nums[mid + 1] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }
}
