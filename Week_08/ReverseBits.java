package Week_08;

/**
 * <h1>190. 颠倒二进制位</h1>
 * <a>https://leetcode-cn.com/problems/reverse-bits/</a>
 * <h3>解法: 逐位倒置, (n & 1) == 1 表示最低位为 1 </h3>
 * @author WENJIE
 */
public class ReverseBits {
    public int reverseBits(int n) {
        int ret = 0;
        for (int i = 0; i < 32; i++) {
            ret += (n & 1) == 1 ? 1 << (31 - i) : 0;
            n = n >> 1;
        }
        return ret;
    }
}
