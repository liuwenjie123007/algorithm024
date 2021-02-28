package Week_04;

import Week_03.TreeNode;

/**
 * <h1>860. 柠檬水找零</h1>
 * <a>https://leetcode-cn.com/problems/lemonade-change/description/</a>
 * <h3>解法：贪心算法,单次扫描</h3>
 * <p>时间复杂度O(n), 空间复杂度O(1)</p>
 * @author WENJIE
 */
public class LemonadeChange {
    public boolean lemonadeChange(int[] bills) {
        int count5 = 0;
        int count10 = 0;
        for (int bill : bills) {
            if (bill == 5) {
                count5++;
            } else if (bill == 10) {
                if (count5 == 0) return false;
                count5--;
                count10++;
            } else {
                if (count10 > 0) {
                    count10--;
                    if (count5 == 0) return false;
                    count5--;
                } else {
                    if (count5 <= 2) return false;
                    count5 -= 3;
                }
            }
        }
        return true;
    }
}
