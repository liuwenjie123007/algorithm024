package Week_02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <h1>347. 前 K 个高频元素</h1>
 * <a>https://leetcode-cn.com/problems/top-k-frequent-elements/</a>
 * <h3>解法1：map计数-> 维护k个元素的小顶堆</h3>
 * <p>时间复杂度O(NlogK), 空间复杂度O(n)</p>
 * <h3>解法2：map计数-> 桶排序</h3>
 * <p>时间复杂度O(n), 空间复杂度O(n)</p>
 *
 * @author WENJIE
 */
public class TopKFrequentElements {
    // 解法2
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = IntStream.of(nums).boxed().collect(Collectors.toMap(e -> e, e -> 1, Integer::sum));
        List<Integer>[] bucket = new List[nums.length + 1];
        map.forEach((num, count) -> {
            if (bucket[count] == null) bucket[count] = new ArrayList<>();
            bucket[count].add(num);
        });
        int[] ret = new int[k];
        int count = 0;
        for (int i = bucket.length - 1; i >= 0; i--) {
            if (bucket[i] != null) {
                for (int num : bucket[i]) {
                    ret[count++] = num;
                    if (count == k) return ret;
                }
            }
        }
        return ret;
    }
}
