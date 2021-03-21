package Week_07;

import java.util.*;

/**
 * <h1>51. N 皇后</h1>
 * <a>https://leetcode-cn.com/problems/n-queens/</a>
 * <h3>解法1: 回溯剪枝</h3>
 * @author WENJIE
 */
public class NQueens {
    List<List<String>> ret = new ArrayList<>();
    Set<Integer> columnSet = new HashSet<>();
    Set<Integer> diagSet1 = new HashSet<>();
    Set<Integer> diagSet2 = new HashSet<>();
    List<Integer> queenList = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        init(n);
        backtrace(n, 0);
        return ret;
    }

    private void backtrace(int n, int row) {
        if (row == n) {
            addSolution(n);
            return;
        }
        for (int i = 0; i < n; i++) {
            boolean isEffectPoint = !columnSet.contains(i)
                    && !diagSet1.contains(row + i)
                    && !diagSet2.contains(row - i);
            if (!isEffectPoint)
                continue;
            preProcess(row, i);
            backtrace(n, row + 1);
            afterProcess(row, i);
        }
    }

    private void afterProcess(int row, int i) {
        columnSet.remove(i);
        diagSet1.remove(row + i);
        diagSet2.remove(row - i);
        queenList.set(row, -1);
    }

    private void preProcess(int row, int i) {
        columnSet.add(i);
        diagSet1.add(row + i);
        diagSet2.add(row - i);
        queenList.set(row, i);
    }

    private void addSolution(int n) {
        List<String> solution = new ArrayList<>();
        queenList.forEach(column -> {
            StringBuilder ret = new StringBuilder();
            for (int i = 0; i < n; i++) {
                ret.append(column == i ? "Q" : ".");
            }
            solution.add(ret.toString());
        });
        ret.add(solution);
    }

    private void init(int n) {
        for (int i = 0; i < n; i++) {
            queenList.add(-1);
        }
    }
}
