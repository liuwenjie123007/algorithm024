package Week_01;

/**
 * <h1>66. 加一</h1>
 * <a>https://leetcode-cn.com/problems/plus-one/</a>
 * <h3>解法：后序遍历进位，单次遍历可解</h3>
 * <p>时间复杂度O(n), 空间复杂度O(1)</p>
 * @author WENJIE
 */
public class PlusOne {
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0 ; i--) {
            digits[i]++;
            if (digits[i] == 10) {
                digits[i] = 0;
            } else if (digits[i] < 10){
                return digits;
            }
        }
        int[] newDigits = new int[digits.length + 1];
        newDigits[0] = 1;
        return newDigits;
    }
}
