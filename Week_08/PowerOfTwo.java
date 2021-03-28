package Week_08;

/**
 * <h1>231. 2的幂</h1>
 * <a>https://leetcode-cn.com/problems/power-of-two/</a>
 * <h3>解法: n & -n 得到最低位1 , 若为2的幂且大于0 则 最低位1 等于自身</h3>
 * @author WENJIE
 */
public class PowerOfTwo {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & -n) == n;
    }
}
