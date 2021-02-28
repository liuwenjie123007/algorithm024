package Week_04;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * <h1>529. 扫雷游戏</h1>
 * <a>https://leetcode-cn.com/problems/minesweeper/</a>
 * <h3>解法：广度优先搜索</h3>
 * <p>时间复杂度O(NM) 空间复杂度O(NM)</p>
 *
 * @author WENJIE
 */
public class Minesweeper {
    public char[][] updateBoard(char[][] board, int[] click) {
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        if (board[click[0]][click[1]] != 'E') {
            return board;
        }
        boolean[][] vis = new boolean[board.length][board[0].length];
        int row = click[0];
        int column = click[1];
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.add(Arrays.asList(row, column));
        while (!queue.isEmpty()) {
            List<Integer> next = queue.poll();
            row = next.get(0);
            column = next.get(1);
            vis[row][column] = true;
            boolean nearEOrB = isNearEOrB(row, column, board);
            if (nearEOrB) {
                int nearNumber = nearNumber(row, column, board);
                if (nearNumber == 0) {
                    board[row][column] = 'B';
                    addAround(row, column, board, queue, vis);
                } else
                    board[row][column] = String.valueOf(nearNumber).toCharArray()[0];
            }
        }
        return board;
    }

    private void addAround(int row, int column, char[][] board, Queue<List<Integer>> queue, boolean[][] vis) {
        if (isValid(row, column + 1, board) && !vis[row][column + 1])
            queue.add(Arrays.asList(row, column + 1));
        if (isValid(row, column - 1, board) && !vis[row][column - 1])
            queue.add(Arrays.asList(row, column - 1));
        if (isValid(row + 1, column - 1, board) && !vis[row + 1][column - 1])
            queue.add(Arrays.asList(row + 1, column - 1));
        if (isValid(row + 1, column, board) && !vis[row + 1][column])
            queue.add(Arrays.asList(row + 1, column));
        if (isValid(row + 1, column + 1, board) && !vis[row + 1][column + 1])
            queue.add(Arrays.asList(row + 1, column + 1));
        if (isValid(row - 1, column - 1, board) && !vis[row - 1][column - 1])
            queue.add(Arrays.asList(row - 1, column - 1));
        if (isValid(row - 1, column, board) && !vis[row - 1][column])
            queue.add(Arrays.asList(row - 1, column));
        if (isValid(row - 1, column + 1, board) && !vis[row - 1][column + 1])
            queue.add(Arrays.asList(row - 1, column + 1));
    }

    private boolean isValid(int row, int i, char[][] board) {
        return row >= 0 && row < board.length && i >= 0 && i < board[0].length;
    }

    private int nearNumber(int row, int column, char[][] board) {
        int count = 0;
        if (isValid(row, column + 1, board) && board[row][column + 1] == 'M')
            count++;
        if (isValid(row, column - 1, board) && board[row][column - 1] == 'M')
            count++;
        if (isValid(row + 1, column - 1, board) && board[row + 1][column - 1] == 'M')
            count++;
        if (isValid(row + 1, column, board) && board[row + 1][column] == 'M')
            count++;
        if (isValid(row + 1, column + 1, board) && board[row + 1][column + 1] == 'M')
            count++;
        if (isValid(row - 1, column - 1, board) && board[row - 1][column - 1] == 'M')
            count++;
        if (isValid(row - 1, column, board) && board[row - 1][column] == 'M')
            count++;
        if (isValid(row - 1, column + 1, board) && board[row - 1][column + 1] == 'M')
            count++;
        return count;
    }

    private boolean isNearEOrB(int row, int column, char[][] board) {

        if (isValid(row, column + 1, board) && (board[row][column + 1] == 'B' || board[row][column + 1] == 'E'))
            return true;
        if (isValid(row, column - 1, board) && (board[row][column - 1] == 'B' || board[row][column - 1] == 'E'))
            return true;
        if (isValid(row + 1, column - 1, board) && (board[row + 1][column - 1] == 'B' || board[row + 1][column - 1] == 'E'))
            return true;
        if (isValid(row + 1, column, board) && (board[row + 1][column] == 'B' || board[row + 1][column] == 'E'))
            return true;
        if (isValid(row + 1, column + 1, board) && (board[row + 1][column + 1] == 'B' || board[row + 1][column + 1] == 'E'))
            return true;
        if (isValid(row - 1, column - 1, board) && (board[row - 1][column - 1] == 'B' || board[row - 1][column - 1] == 'E'))
            return true;
        if (isValid(row - 1, column, board) && (board[row - 1][column] == 'B' || board[row - 1][column] == 'E'))
            return true;
        if (isValid(row - 1, column + 1, board) && (board[row - 1][column + 1] == 'B' || board[row - 1][column + 1] == 'E'))
            return true;
        return false;
    }
}
