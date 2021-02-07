package Week_02;

import java.util.HashMap;
import java.util.Map;

/**
 * <h1>1. 两数之和</h1>
 * <a>https://leetcode-cn.com/problems/two-sum/</a>
 * <h3>解法1：排序 双指针 求和</h3>
 * <p>时间复杂度O(nLogN), 空间复杂度O(1)</p>
 * <h3>解法2：利用HashMap, 缓存值与下标， 击中与目标值得差，返回两个下标。</h3>
 * <p>时间复杂度O(n), 空间复杂都O(n)</p>
 * @author WENJIE
 */
public class TwoSum {
    //解法2
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        return null;
    }
}
