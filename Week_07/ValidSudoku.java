package Week_07;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <h1>36. 有效的数独</h1>
 * <a>https://leetcode-cn.com/problems/valid-sudoku/description/</a>
 * <h3>解法1: 简单穷举验证</h3>
 * @author WENJIE
 */
public class ValidSudoku {
    List<Set<Character>> rowSets = new ArrayList<>();
    List<Set<Character>> columnSets = new ArrayList<>();
    List<Set<Character>> boxSets = new ArrayList<>();


    public boolean isValidSudoku(char[][] board) {
        int n = board.length;
        init(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                char num = board[i][j];
                if (num != '.') {
                    int box = j / 3 + (i / 3) * 3;
                    boolean canContinue = !rowSets.get(i).contains(num)
                            && !columnSets.get(j).contains(num)
                            && !boxSets.get(box).contains(num);
                    if (!canContinue)
                        return false;
                    rowSets.get(i).add(num);
                    columnSets.get(j).add(num);
                    boxSets.get(box).add(num);
                }
            }
        }
        return true;
    }

    private void init(int n) {
        for (int i = 0; i < n; i++) {
            rowSets.add(new HashSet<>());
            columnSets.add(new HashSet<>());
            boxSets.add(new HashSet<>());
        }
    }
}
