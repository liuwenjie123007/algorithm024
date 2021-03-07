package Week_05;

import java.util.HashMap;
import java.util.Map;

/**
 * <h1>621. 任务调度器</h1>
 * <a>https://leetcode-cn.com/problems/task-scheduler/</a>
 * <h3>解法：桶思想？ 这到题与动态规划关系？</h3>
 * <p>任务由于有最小间隔n, 因此任务时间是与最多个数的任务数强相关的</p>
 * <p>若最多个数的任务个数为A， 则整个任务时间不会低于A + (A - 1) * n</p>
 * <p>因此可以考虑在最多任务等待期间插入其他任务</p>
 * <p>而其他任务个数不多于最多任务个数，因此其他任务按相同顺序穿插到A任务执行期间是必然不会等待的</p>
 * <p>而最终有一个特殊情况，个数最多的任务数量有多个</p>
 * <p>而这种情况在只要顺序执行最多任务数的任务即可</p>
 * <p>因此最终答案：MAX{任务总数，A + (A - 1) * n + （最多任务种类数 - 1）}</p>
 * <p>时间复杂度O(n), 空间复杂度O(1)</p>
 * @author WENJIE
 */
public class TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
        // Map 计数
        Map<Character, Integer> map = new HashMap<>();
        int maxTaskNum = 0;
        for (char task : tasks) {
            map.put(task, map.getOrDefault(task, 0) + 1);
            maxTaskNum = Math.max(maxTaskNum, map.get(task));
        }
        int total = 0;
        int sameMaxNum = -1;
        for (Integer count : map.values()) {
            total += count;
            if (maxTaskNum == count)
                sameMaxNum++;
        }

        return Math.max(total, (maxTaskNum * (n + 1) - n + sameMaxNum));
    }
}
