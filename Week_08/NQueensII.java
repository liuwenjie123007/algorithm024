package Week_08;

/**
 * <h1>52. N皇后 II</h1>
 * <a>https://leetcode-cn.com/problems/n-queens-ii/</a>
 * <h3>解法: 递归回溯， 使用位运算标记各层可配置位置 </h3>
 * @author WENJIE
 */
public class NQueensII {
    int size;
    int count;

    public int totalNQueens(int n) {
        size = (1 << n) - 1;
        solve(0, 0, 0);
        return count;
    }

    private void solve(int row, int ld, int rd) {
        if (row == size) {
            count++;
            return;
        }
        int pos = size & (~(row | ld | rd));
        while (pos != 0) {
            int p = pos & (-pos);
            pos -= p;
            solve(row | p, (ld | p) << 1, (rd | p) >> 1);
        }
    }
}
