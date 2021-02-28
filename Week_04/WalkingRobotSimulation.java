package Week_04;

import com.sun.tools.javac.util.Pair;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <h1>874. 模拟行走机器人</h1>
 * <a>https://leetcode-cn.com/problems/walking-robot-simulation/description/</a>
 * <h3>解法：单步行走模拟</h3>
 * <p>时间复杂度O(nm), 空间复杂度O(n)</p>
 * @author WENJIE
 */
public class WalkingRobotSimulation {
    public int robotSim(int[] commands, int[][] obstacles) {
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        Set<Pair<Integer, Integer>> obstacleSet = Arrays.stream(obstacles)
                .map(obstacle -> new Pair<>(obstacle[0], obstacle[1]))
                .collect(Collectors.toSet());
        int ans = 0;
        int direcState = 0;
        int x = 0;
        int y = 0;
        for (int cmd : commands) {
            if (cmd == -2) {
                direcState = (direcState + 3) % 4;
            } else if (cmd == -1) {
                direcState = (direcState + 1) % 4;
            } else {
                for (int i = 0; i < cmd; i++) {
                    int nx = x + dx[direcState];
                    int ny = y + dy[direcState];
                    if (!obstacleSet.contains(new Pair<>(nx, ny))) {
                        x = nx;
                        y = ny;
                        ans = Math.max(ans, x * x + y * y);
                    }
                }
            }
        }
        return ans;
    }

}
