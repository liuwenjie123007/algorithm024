package Week_04;

/**
 * <h1>74. 搜索二维矩阵</h1>
 * <a>https://leetcode-cn.com/problems/search-a-2d-matrix/</a>
 * <h3>二分查找, 拉平数组</h3>
 * <p>时间复杂度O(LogNM) 空间复杂度O(1)</p>
 *
 * @author WENJIE
 */
public class SearchA2dMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int left = 0;
        int right = matrix.length * matrix[0].length - 1;
        int mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            int midRow = mid / matrix[0].length;
            int midColumn = mid % matrix[0].length;
            if (target == matrix[midRow][midColumn])
                return true;
            if (target > matrix[midRow][midColumn]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}
