package Week_01;

import java.util.Deque;
import java.util.LinkedList;

/**
 * <h1>42. 接雨水</h1>
 * <a>https://leetcode-cn.com/problems/trapping-rain-water/</a>
 * <h3>解法1：暴力</h3>
 * <p>时间复杂度O(n^2),空间复杂度O(1)</p>
 * <h3>解法2：单调栈</h3>
 * <p>时间复杂度O(n),空间复杂度O(n)</p>
 * @author WENJIE
 */
public class TrappingRainWater {
    public int trap(int[] height) {
        int sum = 0;
        Deque<Integer> stack = new LinkedList<>();
        int current = 0;
        while (current < height.length) {
            while (!stack.isEmpty() && height[current] > height[stack.peek()]) {
                int h = height[stack.peek()];
                stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int distance = current - stack.peek() - 1;
                int min = Math.min(height[stack.peek()], height[current]);
                sum = sum + distance * (min - h);
            }
            stack.push(current);
            current++;
        }
        return sum;
    }
}
