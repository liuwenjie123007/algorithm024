package Week_07;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <h1>70. 爬楼梯</h1>
 * <a>https://leetcode-cn.com/problems/climbing-stairs/</a>
 * <h3>解法1: DFS剪枝</h3>
 * @author WENJIE
 */
public class ClimbingStairs {
    List<Integer> memo = new ArrayList<>();
    // DFS剪枝
    public int climbStairs(int n) {
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;
        if (memo.size() < n) {
            for (int i = 0; i <= n; i++)
                memo.add(0);
        }
        if (memo.get(n) == 0) {
            int sum = climbStairs(n - 1) + climbStairs(n - 2);
            memo.set(n, sum);
        }
        return memo.get(n);
    }
}
