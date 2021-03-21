package Week_07;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <h1>37. 解数独</h1>
 * <a>https://leetcode-cn.com/problems/sudoku-solver/</a>
 * <h3>解法1: 递归回溯剪枝</h3>
 * @author WENJIE
 */
public class SudokuSolver {
    List<Set<Character>> rowSets = new ArrayList<>();
    List<Set<Character>> columnSets = new ArrayList<>();
    List<Set<Character>> boxSets = new ArrayList<>();

    // 解法1
    public void solveSudoku1(char[][] board) {
        init(board);
        solve(board);
    }

    private boolean solve(char[][] board) {
        int n = board.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                char num = board[i][j];
                if (num == '.') {
                    int box = j / 3 + (i / 3) * 3;
                    for (char k = '1'; k <= '9'; k++) {
                        boolean canContinue = !rowSets.get(i).contains(k)
                                && !columnSets.get(j).contains(k)
                                && !boxSets.get(box).contains(k);
                        if (!canContinue)
                            continue;
                        rowSets.get(i).add(k);
                        columnSets.get(j).add(k);
                        boxSets.get(box).add(k);
                        board[i][j] = k;
                        if (solve(board))
                            return true;
                        board[i][j] = num;
                        rowSets.get(i).remove(k);
                        columnSets.get(j).remove(k);
                        boxSets.get(box).remove(k);
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private void init(char[][] board) {
        int n = board.length;
        for (int i = 0; i < n; i++) {
            rowSets.add(new HashSet<>());
            columnSets.add(new HashSet<>());
            boxSets.add(new HashSet<>());
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                char num = board[i][j];
                if (num != '.') {
                    int box = j / 3 + (i / 3) * 3;
                    rowSets.get(i).add(num);
                    columnSets.get(j).add(num);
                    boxSets.get(box).add(num);
                }
            }
        }
    }
}
