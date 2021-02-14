package Week_03;

/**
 * <h1>236. 二叉树的最近公共祖先</h1>
 * <a>https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/</a>
 * <h3>解法：后序遍历，分治</h3>
 * <p>时间复杂度O(n), 空间复杂度O(n)</p>
 * @author WENJIE
 */
public class LowestCommonAncestorOfABinaryTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return root;
        TreeNode leftAncestor = lowestCommonAncestor(root.left, p, q);
        TreeNode rightAncestor = lowestCommonAncestor(root.right, p, q);
        if (root == q || root == p) return root;
        if (leftAncestor == null) return rightAncestor;
        if (rightAncestor == null) return leftAncestor;
        return root;
    }
}
