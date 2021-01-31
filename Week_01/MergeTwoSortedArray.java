package Week_01;

/**
 * <h1>88. 合并两个有序数组</h1>
 * <a>https://leetcode-cn.com/problems/merge-sorted-array/</a>
 * <h3>解法1：添加到后面再排序</h3>
 * <p>时间复杂度O(nLogN), 空间复杂度O(1)</p>
 * <h3>解法2：倒序比较并插入到 大数组队尾</h3>
 * <p>时间复杂度O(n), 空间复杂都O(1)</p>
 * @author WENJIE
 */
public class MergeTwoSortedArray {
    //解法2
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int resoled = nums1.length - 1;
        int p1 = m - 1;
        int p2 = n - 1;
        while (p1 >= 0 && p2 >= 0) {
            nums1[resoled--] = nums1[p1] > nums2[p2] ? nums1[p1--] : nums2[p2--];
        }
        System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
    }
}
