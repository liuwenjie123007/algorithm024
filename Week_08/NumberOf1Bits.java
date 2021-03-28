package Week_08;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>191. 位1的个数</h1>
 * <a>https://leetcode-cn.com/problems/number-of-1-bits/</a>
 * <h3>解法: 计数 -> n & (n - 1) 清零最低位1 </h3>
 * @author WENJIE
 */
public class NumberOf1Bits {
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n &= n - 1;
        }
        return count;
    }
}
