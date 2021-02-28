package Week_04;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * <h1>55. 跳跃游戏</h1>
 * <a>https://leetcode-cn.com/problems/jump-game/</a>
 * <h3>解法：贪心算法,单次扫描，更新可达最大下标</h3>
 * <p>时间复杂度O(n) 空间复杂度O(1)</p>
 *
 * @author WENJIE
 */
public class JumpGame {
    public boolean canJump(int[] nums) {
        int maxArrive = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > maxArrive) return false;
            maxArrive = Math.max(i + nums[i], maxArrive);
        }
        return maxArrive >= nums.length - 1;
    }
}
