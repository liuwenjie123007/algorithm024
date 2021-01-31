package Week_01;

/**
 * <h1>283. 移动零</h1>
 * <a>https://leetcode-cn.com/problems/move-zeroes/</a>
 * <h3>解法：双指针,右指针非零元素逐个交换到左指针</h3>
 * <p>时间复杂度O(n), 空间复杂度O(1)</p>
 * @author WENJIE
 */
public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        for (int left = 0, right = 0; right < nums.length; right++) {
            if (nums[right] != 0) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
            }
        }
    }
}
