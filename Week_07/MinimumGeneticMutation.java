package Week_07;

import java.util.*;

/**
 * <h1>433. 最小基因变化</h1>
 * <a>https://leetcode-cn.com/problems/minimum-genetic-mutation/</a>
 * <h3>解法1: 双向BFS</h3>
 * @author WENJIE
 */
public class MinimumGeneticMutation {
    Set<String> startQueue = new HashSet<>();
    Set<String> endQueue = new HashSet<>();
    Set<String> memo = new HashSet<>();
    List<Character> mutationList = new ArrayList<>();
    Set<String> bankSet = new HashSet<>();

    // 双向BFS
    public int minMutation(String start, String end, String[] bank) {
        init(bank);
        if (!bankSet.contains(end))
            return -1;
        startQueue.add(start);
        endQueue.add(end);
        int step = 0;
        while (!startQueue.isEmpty() && !endQueue.isEmpty()) {
            step++;
            if (startQueue.size() > endQueue.size()) {
                Set<String> temp = endQueue;
                endQueue = startQueue;
                startQueue = temp;
            }

            Set<String> nextQueue = new HashSet<>();
            for (String s : startQueue) {
                char[] chars = s.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    char current = chars[i];
                    for (char c : mutationList) {
                        chars[i] = c;
                        String next = String.valueOf(chars);
                        if (bankSet.contains(next)) {
                            if (endQueue.contains(next))
                                return step;
                            if (!memo.contains(next)) {
                                memo.add(next);
                                nextQueue.add(next);
                            }
                        }
                        chars[i] = current;
                    }
                }
            }
            startQueue = nextQueue;
        }
        return -1;
    }

    private void init(String[] bank) {
        bankSet.addAll(Arrays.asList(bank));
        mutationList.add('A');
        mutationList.add('C');
        mutationList.add('G');
        mutationList.add('T');
    }
}
