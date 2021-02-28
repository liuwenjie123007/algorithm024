package Week_04;

import com.sun.tools.javac.util.Pair;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <h1>使用二分查找，寻找一个半有序数组 [4, 5, 6, 7, 0, 1, 2] 中间无序的地方</h1>
 * <h3>解法：二分查找</h3>
 * <p>时间复杂度O(LonN), 空间复杂度O(1)</p>
 *
 * @author WENJIE
 */
public class BinarySearch {

    // 寻找最小元素
    public static int findDeviceIndex(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            if (nums[left] <= nums[right])
                return left;
            int mid = left + (right - left) / 2;
            if (nums[mid] >= nums[left]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
        int deviceIndex = findDeviceIndex(nums1);
        System.out.println(deviceIndex);

        int[] nums2 = {5, 6, 7, 0, 1, 2, 4};
        deviceIndex = findDeviceIndex(nums2);
        System.out.println(deviceIndex);

        int[] nums3 = {6, 7, 0, 1, 2, 4, 5};
        deviceIndex = findDeviceIndex(nums3);
        System.out.println(deviceIndex);

        int[] nums4 = {0, 1, 2, 4, 5, 6, 7};
        deviceIndex = findDeviceIndex(nums4);
        System.out.println(deviceIndex);

        int[] nums5 = {1, 2, 4, 5, 6, 7, 0};
        deviceIndex = findDeviceIndex(nums5);
        System.out.println(deviceIndex);
    }

}
