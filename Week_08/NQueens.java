package Week_08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <h1>51. N 皇后</h1>
 * <a>https://leetcode-cn.com/problems/n-queens/</a>
 * <h3>解法: 递归回溯， 使用位运算标记各层可配置位置 </h3>
 * @author WENJIE
 */
public class NQueens {
    int size;
    List<List<String>> ret = new ArrayList<>();
    List<Integer> queenList = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        size = (1 << n) - 1;
        for (int i = 0; i < n; i++) {
            queenList.add(-1);
        }
        solve(0, 0, 0, 0);
        return ret;
    }

    private void solve(int row, int column, int ld, int rd) {
        if (column == size) {
            addSolution();
            return;
        }
        int pos = size & (~(column | ld | rd));
        while (pos != 0) {
            int p = pos & (-pos);
            pos -= p;
            queenList.set(row, Integer.bitCount(p - 1));
            solve(row + 1,column | p, (ld | p) << 1, (rd | p) >> 1);
            queenList.set(row, -1);
        }
    }

    private void addSolution() {
        List<String> solution = new ArrayList<>();
        for (int i = 0; i < queenList.size(); i++) {
            char[] row = new char[queenList.size()];
            Arrays.fill(row, '.');
            row[queenList.get(i)] = 'Q';
            solution.add(new String(row));
        }
        ret.add(solution);
    }
}
