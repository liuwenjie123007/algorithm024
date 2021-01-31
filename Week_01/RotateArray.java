package Week_01;

/**
 * <h1>189. 旋转数组</h1>
 * <a>https://leetcode-cn.com/problems/rotate-array/</a>
 * <h3>方法1:暴力法</h3>
 * <p>时间复杂度O(n^2),空间复杂都O(1)</p>
 * <h3>方法2：使用队列</h3>
 * <p>时间复杂度O(n)</p>
 * <p>空间复杂度度O(n)</p>
 * <h3>方法3:翻转法</h3>
 * <p>时间复杂度O(n)</p>
 * <p>空间复杂度O(1)</p>
 * @author WENJIE
 */
public class RotateArray {
    public void rotate(int[] nums, int k) {
        // 翻转法
        k = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
