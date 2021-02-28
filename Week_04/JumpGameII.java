package Week_04;

/**
 * <h1>45. 跳跃游戏 II</h1>
 * <a>https://leetcode-cn.com/problems/jump-game-ii/</a>
 * <h3>解法：贪心算法,单次扫描，更新可达最大下标时计数</h3>
 * <p>时间复杂度O(n) 空间复杂度O(1)</p>
 *
 * @author WENJIE
 */
public class JumpGameII {
    public int jump(int[] nums) {
        int maxArrive = 0;
        int count = 0;
        int endIndex = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            maxArrive = Math.max(maxArrive, i + nums[i]);
            if (i == endIndex) {
                endIndex = maxArrive;
                count++;
            }
        }
        return count;
    }
}
